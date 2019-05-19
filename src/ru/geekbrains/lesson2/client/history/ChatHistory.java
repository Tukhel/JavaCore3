package ru.geekbrains.lesson2.client.history;

import ru.geekbrains.lesson2.client.TextMessage;

import java.util.List;

public interface ChatHistory {

    void addMessage(TextMessage message);

    List<TextMessage> getLastMessage(int count);

    void flush();
}
