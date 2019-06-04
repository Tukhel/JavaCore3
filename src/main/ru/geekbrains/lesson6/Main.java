package ru.geekbrains.lesson6;

import java.util.Arrays;

public class Main {

    public static void main( String[] args ) {
        int[] arrOne = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        Task1(arrOne);
        System.out.println(Task2(arrOne));
    }

    public static int[] Task1(int[] array) {

        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == 4) {
                return Arrays.copyOfRange(array, i+1, array.length);
            }
        }

        throw new RuntimeException("Digit 4 is not found in the array");

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
