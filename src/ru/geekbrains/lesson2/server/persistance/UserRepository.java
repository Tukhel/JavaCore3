package ru.geekbrains.lesson2.server.persistance;

import ru.geekbrains.lesson2.server.User;

import java.sql.*;
import java.util.List;

public class UserRepository {

    private final Connection conn;

    User user = new User();

    public UserRepository(Connection conn) {
        this.conn = conn;

    }

    public void insert(User user) throws SQLException {
        PreparedStatement prepareStatement = conn.prepareStatement("insert into users(login, password) values (?, ?)");
        prepareStatement.setString(1, "ivan");
        prepareStatement.setString(2, "123");
        prepareStatement.execute();
    }

    public User findByLogin(String login) throws SQLException {
        Statement find = conn.createStatement();
        ResultSet resultFind = find.executeQuery("select * from users where login = 'ivan'");

        while (resultFind.next()) {
            user.setId(resultFind.getInt(1));
            user.setLogin(resultFind.getString(2));
            user.setPassword(resultFind.getString(3));

            System.out.println(user);
        }
        resultFind.close();

        return null;
    }

    public List<User> getAllUsers() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery("select * from users");

        System.out.println("Все пользователи:");
        while (resultSet.next()) {
            user.setId(resultSet.getInt(1));
            user.setLogin(resultSet.getString(2));
            user.setPassword(resultSet.getString(3));

            System.out.println(user);
        }
        resultSet.close();
        return null;
    }
}