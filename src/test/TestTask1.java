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
public class TestTask1 {

    private Main array;
    private int[] arr;
    private int[] res;

    @Parameterized.Parameters
    public static Collection params() {
        return Arrays.asList(
                new Object[][]{
                        {new int[] {1, 2, 4, 3, 4, 1, 7}, new int[] {1, 7}},
                        {new int[] {1, 2, 3, 4, 7, 8, 9}, new int[] {7, 8, 9}},
                        {new int[] {}, new int[] {1, 2, 3}},
                        {new int[] {4, 4, 2, 3}, new int[] {1, 2, 3}},
                        {new int[] {4, 1, 7}, new int[] {1, 7}}
                }
        );
    }

    public TestTask1(int[] arr, int[] res) {
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
        Assert.assertArrayEquals(res, array.Task1(arr));
    }
}
