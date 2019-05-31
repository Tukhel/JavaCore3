package ru.geekbrains.lesson2.server.persistance;

import ru.geekbrains.lesson2.server.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private final Connection conn;

    User user = new User();

    public UserRepository(Connection conn) throws SQLException {
        this.conn = conn;
        creatTableIfNotExists(conn);

    }

    public void insert(User user) throws SQLException {
        try (PreparedStatement prepareStatement = conn.prepareStatement(
                "insert into users(login, password) values (?, ?)")) {
            prepareStatement.setString(1, user.getLogin());
            prepareStatement.setString(2, user.getPassword());
            prepareStatement.execute();
        }
    }

    public User findByLogin(String login) throws SQLException {
        try (PreparedStatement find = conn.prepareStatement(
                "select id, login, password from users where login = ?")) {
            find.setString(1, login);
            ResultSet resultFind = find.executeQuery();

            if (resultFind.next()) {
                return new User(resultFind.getInt(1),
                        resultFind.getString(2),
                        resultFind.getString(3));
            }
        }
        return new User(-1, "", "");
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> res = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select id, login, password from users");

            while (rs.next()) {
                res.add(new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)));
            }
        }
        return res;
    }

    public void creatTableIfNotExists(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("create table if not exists users (\n" +
                    "\tid int auto_increment primary key,\n" +
                    "   login varchar(25),\n" +
                    "   password varchar(25),\n" +
                    "   unique index uq_login(login)\n" +
                    ");");
        }
    }
}