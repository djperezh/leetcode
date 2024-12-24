package test.a_Intro;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.a_Intro.IntroToRecursion;

public class IntroToRecursionTest {

    @Test
    public void fibonacciTest() {
        // assertEquals(0, IntroToRecursion.fibonacci(0));
        // assertEquals(1, IntroToRecursion.fibonacci(1));
        // assertEquals(1, IntroToRecursion.fibonacci(2));
        // assertEquals(2, IntroToRecursion.fibonacci(3));
        // assertEquals(3, IntroToRecursion.fibonacci(4));
        // assertEquals(5, IntroToRecursion.fibonacci(5));
        // assertEquals(8, IntroToRecursion.fibonacci(6));
        assertEquals(Double.parseDouble("12586269025"), IntroToRecursion.fibonacci(50), 0);
    }

    @Test
    public void fibonacciIterativeTest() {
        assertEquals(0, IntroToRecursion.fibonacci(0), 0);
        assertEquals(1, IntroToRecursion.fibonacci(1), 0);
        assertEquals(1, IntroToRecursion.fibonacci(2), 0);
        assertEquals(2, IntroToRecursion.fibonacci(3), 0);
        assertEquals(3, IntroToRecursion.fibonacci(4), 0);
        assertEquals(5, IntroToRecursion.fibonacci(5), 0);
        assertEquals(8, IntroToRecursion.fibonacci(6), 0);
        assertEquals(Double.parseDouble("12586269025"), IntroToRecursion.fibonacciIterative(50), 0);
    }
}