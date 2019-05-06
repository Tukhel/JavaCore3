package ru.geekbrains.lesson1.Task3;

public class Task3 {

    public static void main(String[] args) {

        Apple apple = new Apple();

        Box<Apple> appleBox1 = new Box<>();
        appleBox1.add(apple, apple, apple, apple, apple, apple);

        Box<Apple> appleBox2 = new Box<>();
        appleBox2.add(apple,apple,apple);

        Orange orange = new Orange();

        Box<Orange> orangeBox1 = new Box<>();
        orangeBox1.add(orange, orange, orange);

        Box<Orange> orangeBox2 = new Box<>();
        orangeBox2.add(orange);

        System.out.printf("Вес первой коробки с яблоками: %s \n", appleBox1.getWeight());
        System.out.printf("Вес второй коробки с яблоками: %s \n", appleBox2.getWeight());
        System.out.printf("Вес первой коробки с апельсинами: %s \n", orangeBox1.getWeight());
        System.out.printf("Вес второй коробки с апельсинами: %s \n", orangeBox2.getWeight());

        boolean b = appleBox1.compare(orangeBox1);
        System.out.println("Коробки равны? " + b);

        Sort sort = new Sort();
        sort.shiftFruits(appleBox1,  appleBox2);
        sort.shiftFruits(orangeBox1, orangeBox2);

        System.out.printf("Вес коробок с яблоками после сортировки: %s, %s \n", appleBox2.getWeight(), appleBox1.getWeight());
        System.out.printf("Вес коробок с апельсинами после сортировки: %s,  %s \n", orangeBox2.getWeight(), orangeBox1.getWeight());
    }
}
