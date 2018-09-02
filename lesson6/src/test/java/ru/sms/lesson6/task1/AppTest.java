package ru.sms.lesson6.task1;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Enclosed.class)
public class AppTest {

    @RunWith(Parameterized.class)
    public static class Task1 {
        @Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {new int[]{1, 2, 4, 2, 1}, new int[]{2, 1}},
                    {new int[]{1, 2, 4, 2, 1, 4, 1, 3}, new int[]{1, 3}},
                    {new int[]{4, 15, 231}, new int[]{15, 231}},
                    {new int[]{1, 2, 4}, new int[0]}
            });
        }

        @Parameter
        public int[] ints;

        @Parameter(1)
        public int[] resultInts;

        @Test
        public void testCreateNewArrayAfterNum(){
            assertArrayEquals(resultInts, App.createNewArrayAfterNum(ints));
        }
    }

    public static class Task1TestException {
        @Test(expected = RuntimeException.class)
        public void testExcCreateNewArrayAfterNum() {
            final int[] ints = {1, 2, 3, 5};
            App.createNewArrayAfterNum(ints);
        }
    }

    @RunWith(Parameterized.class)
    public static class Task2 {
        @Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {new int[]{2, 2, 4}, true},
                    {new int[]{2, 4, 2}, true},
                    {new int[]{2, 1, 4}, false},
                    {new int[]{1, 1, 1}, false}
            });
        }

        @Parameter
        public int[] ints;

        @Parameter(1)
        public boolean result;

        @Test
        public void testCheckForSomeNumbers() {
            assertEquals(result, App.checkForSomeNumbers(ints));
        }
    }
}