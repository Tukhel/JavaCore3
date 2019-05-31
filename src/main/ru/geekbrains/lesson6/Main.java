package ru.geekbrains.lesson6;

import java.util.Arrays;

public class Main {

    public static void main( String[] args ) {
        int[] arrOne = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        Task1(arrOne);
        System.out.println(Task2(arrOne));
    }

    public static int[] Task1(int[] array) {

        int index = 0;
        int[] arr;

        if(array.length == 0) {
            throw new NullPointerException();
        } else {
            for (int i = 0; i < array.length; ++i) {
                if (array[i] == 4) {
                    index = i + 1;
                }
            }
            if(index > 0) {
                arr = Arrays.copyOfRange(array, index, array.length);
                System.out.println("Result" + Arrays.toString(arr));
            } else {
                throw new RuntimeException();
            }
        }
        return arr;
    }

    public static boolean Task2(int[] array) {

        int checkOne = 0;
        int checkFour = 0;

        for (int anArray : array) {
            if (anArray == 1) { checkOne = checkOne + 1; }
            if (anArray == 4) { checkFour = checkFour + 1; }
        }
        return checkOne > 0 && checkFour > 0;
    }
}
