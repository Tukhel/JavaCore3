package ru.geekbrains.lesson5;

import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {

    private static CyclicBarrier cyclicBarrier;
    private static boolean winner;
    private static int CARS_COUNT;

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;

    public static void setCyclicBarrier(CyclicBarrier cyclicBarrier) {
        Car.cyclicBarrier = cyclicBarrier;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            cyclicBarrier.await();
            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }
            checkWinner(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static synchronized void checkWinner(Car car) {
        if (!winner) {
            System.out.println(car.name + " - WIN!!!");
            winner = true;
        }
    }
}