package ru.geekbrains.lesson2.server.auth;

import ru.geekbrains.lesson2.server.User;
import ru.geekbrains.lesson2.server.persistance.UserRepository;

import java.sql.SQLException;

public class AuthServiceJdbcImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceJdbcImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean authUser(User user) {
        try {
            User usr = userRepository.findByLogin(user.getLogin());
            return usr.getId() > 0 && usr.getPassword().equals(user.getPassword());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}