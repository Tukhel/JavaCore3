package ru.geekbrains.lesson1.Task3;

public class Sort {
    public <T extends Fruit> void shiftFruits(Box<T> source, Box<T> destination) {
        while (source.getWeight() != 0) {
            T fruit;
            fruit = source.get();
            //System.out.println("Перекладываем фрукты " + fruit.getClass().getSimpleName() + " из одной коробки в другую");
            destination.add(fruit);
        }
    }
}