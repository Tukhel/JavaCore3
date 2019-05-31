package ru.geekbrains.lesson2.server.auth;

import ru.geekbrains.lesson2.server.User;

public interface AuthService {

    boolean authUser(User user);
}
