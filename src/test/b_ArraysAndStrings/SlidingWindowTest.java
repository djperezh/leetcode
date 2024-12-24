package test.b_ArraysAndStrings;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.b_ArraysAndStrings.SlidingWindow;

public class SlidingWindowTest {
    
    @Test
    public void findMinSwapsTest() {
        // // int[] input = new int[] { 0,1,0,1,0,1,1,0}; // 1
        // int[] input = new int[] { 1,0,1,0,1,0,1,0,1,0,1}; // 3
        int[] input = new int[] { 1,0,0,1,0,0,1}; //2
        
        int ans = SlidingWindow.findMinSwaps(input);

        assertEquals(ans, 2);
    }
}
