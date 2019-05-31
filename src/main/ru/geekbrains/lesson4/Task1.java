package ru.geekbrains.lesson4;

public class Task1 {

    private static volatile char currentLetter = 'A';

    public static void main(String[] args) {
        new Thread(Task1::printA).start();
        new Thread(Task1::printB).start();
        new Thread(Task1::printC).start();
    }

    private synchronized static void printA() {
        for (int i = 0; i < 5; i++) {
            try {
                while (currentLetter != 'A') {
                    Task1.class.wait();
                }
                Thread.sleep(100);
                System.out.println("A");
                currentLetter = 'B';
                Task1.class.notifyAll();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private synchronized static void printB() {
        for (int i = 0; i < 5; i++) {
            try {
                while (currentLetter != 'B') {
                    Task1.class.wait();
                }
                Thread.sleep(1000);
                System.out.println("B");
                currentLetter = 'C';
                Task1.class.notifyAll();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private synchronized static void printC() {
        for (int i = 0; i < 5; i++) {
            try {
                while (currentLetter != 'C') {
                    Task1.class.wait();
                }
                Thread.sleep(2000);
                System.out.println("C");
                currentLetter = 'A';
                Task1.class.notifyAll();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

}