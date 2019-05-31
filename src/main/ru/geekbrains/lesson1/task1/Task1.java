package ru.geekbrains.lesson1.task1;

import java.util.ArrayList;
import java.util.List;

public class Task1 {

    public static void main(String[] args) {
        List<String> str = new ArrayList<>();
        str.add("Apple");
        str.add("Orange");
        str.add("Banana");

        System.out.println(str);

        change(str, 0,1);

        System.out.println(str);

    }

    public static <T> void change(List<T> list, int elemnt1, int element2) {
        T e1 = list.get(elemnt1);
        T e2 = list.get(element2);
        list.set(elemnt1, e2);
        list.set(element2,e1);

    }

}
