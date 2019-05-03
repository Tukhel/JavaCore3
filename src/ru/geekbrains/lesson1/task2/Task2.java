package ru.geekbrains.lesson1.task2;

import java.util.ArrayList;
import java.util.Arrays;

public class Task2 {

    public static void main(String[] args) {

        String[] str = {"Apple","Orange"};

        ArrayList<String> list = toArrayList(str);

        System.out.println(list);

    }

    public static <T> ArrayList<T> toArrayList(T[] arr) {
        return new ArrayList<T>(Arrays.asList(arr));
    }

}
