package ru.geekbrains.lesson2.server.auth;

import ru.geekbrains.lesson2.server.User;
import ru.geekbrains.lesson2.server.persistance.UserRepository;

public class AuthServiceJdbcImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceJdbcImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean authUser(User user) {
        // TODO авторизовать пользователя используя userRepository
        return false;
    }
}