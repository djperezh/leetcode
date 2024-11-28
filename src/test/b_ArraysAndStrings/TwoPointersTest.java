package test.b_ArraysAndStrings;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.b_ArraysAndStrings.TwoPointers;

public class TwoPointersTest {

    @Test
    public void checkIfPalindromeTest() throws Exception {
        assertEquals(true, TwoPointers.checkIfPalindrome("racecar"));
    }  
}
