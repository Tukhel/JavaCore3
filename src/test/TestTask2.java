import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.geekbrains.lesson6.Main;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class TestTask2 {

    private Main array;
    private int[] arr;
    private boolean res;


    @Parameterized.Parameters
    public static Collection params() {
        return Arrays.asList(
                new Object[][]{
                        {new int[]{1, 1, 1, 4}, true},
                        {new int[]{4, 4, 4, 1}, true},
                        {new int[]{5, 6, 2, 2}, false},
                        {new int[]{1, 1, 1, 1}, false},
                        {new int[]{4, 4, 4, 4}, true}
                }
        );
    }

    public TestTask2(int[] arr, boolean res) {
        this.arr = arr;
        this.res = res;
    }

    @Before
    public void init() {
        array = new Main();
    }

    @After
    public void tearDown() throws Exception { array = null; }

    @Test
    public void testArrayCheck() {
        Assert.assertEquals(array.Task2(arr), res);
    }
}