package ru.geekbrains.lesson1.Task3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Box<T extends Fruit> {

    final List<T> fruits;

    public Box() {
        this.fruits = new LinkedList<>();
    }

    public void add(T ... fruits) {
        this.fruits.addAll(Arrays.asList(fruits));
    }

    public double getWeight() {
        if (fruits.size() > 0) {
            return fruits.get(0).getWeight() * fruits.size();
        }
        else return 0;
    }


    public boolean compare(Box<? extends Fruit> that) {
        return Math.abs(this.getWeight() - that.getWeight()) < 0.00001;
    }

    public <T extends Fruit> T get() {
        return ((LinkedList<T>)fruits).pollLast();
    }
}