package ru.geekbrains.lesson2.server;

import ru.geekbrains.lesson2.client.AuthException;
import ru.geekbrains.lesson2.client.TextMessage;
import ru.geekbrains.lesson2.server.auth.AuthService;
import ru.geekbrains.lesson2.server.auth.AuthServiceJdbcImpl;
import ru.geekbrains.lesson2.server.persistance.UserRepository;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ru.geekbrains.lesson2.client.MessagePatterns.AUTH_FAIL_RESPONSE;
import static ru.geekbrains.lesson2.client.MessagePatterns.AUTH_SUCCESS_RESPONSE;

public class ChatServer {

    private static Logger logger = Logger.getLogger(ChatServer.class.getName());

    private AuthService authService;
    private Map<String, ClientHandler> clientHandlerMap = Collections.synchronizedMap(new HashMap<>());
    private ExecutorService executorService = Executors.newFixedThreadPool(20, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thr = Executors.defaultThreadFactory().newThread(r);
            thr.setDaemon(true);
            return thr;
        }
    });

    public static void main(String[] args) {
        AuthService authService;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/network_chat?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Vladivostok",
                    "root", "root");
            UserRepository userRepository = new UserRepository(conn);
            if (userRepository.getAllUsers().size() == 0) {
                userRepository.insert(new User(-1, "ivan", "123"));
                userRepository.insert(new User(-1, "petr", "345"));
                userRepository.insert(new User(-1, "julia", "789"));
            }
            authService = new AuthServiceJdbcImpl(userRepository);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "", e);
            e.printStackTrace();
            return;
        }

        ChatServer chatServer = new ChatServer(authService);
        chatServer.start(7777);
    }

    public ChatServer(AuthService authService) {
        this.authService = authService;
    }

    private void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("Server started!");
            while (true) {
                Socket socket = serverSocket.accept();
                DataInputStream inp = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                logger.info("New client connected!");

                User user = null;
                try {
                    String authMessage = inp.readUTF();
                    user = checkAuthentication(authMessage);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (AuthException ex) {
                    out.writeUTF(AUTH_FAIL_RESPONSE);
                    out.flush();
                    socket.close();
                }
                if (user != null && authService.authUser(user)) {
                    logger.info(String.format("User %s authorized successful!%n", user.getLogin()));
                    subscribe(user.getLogin(), socket);
                    out.writeUTF(AUTH_SUCCESS_RESPONSE);
                    out.flush();
                } else {
                    if (user != null) {
                        logger.info(String.format("Wrong authorization for user %s%n", user.getLogin()));
                    }
                    out.writeUTF(AUTH_FAIL_RESPONSE);
                    out.flush();
                    socket.close();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private User checkAuthentication(String authMessage) throws AuthException {
        String[] authParts = authMessage.split(" ");
        if (authParts.length != 3 || !authParts[0].equals("/auth")) {
            logger.info(String.format("Incorrect authorization message %s%n", authMessage));
            throw new AuthException();
        }
        return new User(-1, authParts[1], authParts[2]);
    }

    private void sendUserConnectedMessage(String login) throws IOException {
        for (ClientHandler clientHandler : clientHandlerMap.values()) {
            if (!clientHandler.getLogin().equals(login)) {
                logger.info(String.format("Sending connect notification to %s about %s%n",
                        clientHandler.getLogin(), login));
                clientHandler.sendConnectedMessage(login);
            }
        }
    }

    private void sendUserDisconnectedMessage(String login) throws IOException {
        for (ClientHandler clientHandler : clientHandlerMap.values()) {
            if (!clientHandler.getLogin().equals(login)) {
                logger.info(String.format("Sending disconnect notification to %s about %s%n",
                        clientHandler.getLogin(), login));
                clientHandler.sendDisconnectedMessage(login);
            }
        }
    }

    public void sendMessage(TextMessage msg) throws IOException {
        ClientHandler userToClientHandler = clientHandlerMap.get(msg.getUserTo());
        if (userToClientHandler != null) {
            userToClientHandler.sendMessage(msg.getUserFrom(), msg.getText());
        } else {
            logger.info(String.format("User %s not connected%n", msg.getUserTo()));
        }
    }

    public Set<String> getUserList() {
        return Collections.unmodifiableSet(clientHandlerMap.keySet());
    }

    public void subscribe(String login, Socket socket) throws IOException {
        clientHandlerMap.put(login, new ClientHandler(login, socket, executorService, this));
        sendUserConnectedMessage(login);
    }

    public void unsubscribe(String login) {
        clientHandlerMap.remove(login);
        try {
            sendUserDisconnectedMessage(login);
        } catch (IOException e) {
            logger.warning("Error sending disconnect message");
            e.printStackTrace();
        }
    }
}