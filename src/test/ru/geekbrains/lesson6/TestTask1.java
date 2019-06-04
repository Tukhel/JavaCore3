package ru.geekbrains.lesson6;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class TestTask1 {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList( new Object[][]{
                {new int[] {5, 6, 7}, new int[] {1, 2, 3, 4, 5, 6, 7}},
                {new int[] {5, 6, 7}, new int[] {1, 1, 4, 2, 4, 5, 6, 7}},
                {new int[] {9}, new int[] {4, 5, 4, 9}},
                {new int[] {3, 7, 10}, new int[] {4, 3, 7, 10}}
        });
    }

    private int[] expected;
    private int[] actual;

    public TestTask1(int[] expected, int[] actual) {
        this.expected = expected;
        this.actual = actual;
    }

    private Main main;

    @Before
    public void init() {
        main = new Main();
    }

    @Test
    public void testAdd() {
        Assert.assertArrayEquals(expected, Main.Task1(actual));
    }
}
