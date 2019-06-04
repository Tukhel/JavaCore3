package ru.geekbrains.lesson7;

public class TestVer2 {

    @AfterSuite
    public static void taskAfter(){
        System.out.println(TestVer2.class.getSimpleName() + " after");
    }


    @Test(priority = 2)
    public static void task1() {
        System.out.println(TestVer2.class.getSimpleName() + " task1");
    }

    @Test(priority = 3)
    public static void task2() {
        System.out.println(TestVer2.class.getSimpleName() + " task2");
    }
}