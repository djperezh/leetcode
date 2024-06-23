package test.ArraysAndStrings;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import main.ArraysAndStrings.TwoPointers;

public class TwoPointersTest {

    @Test
    public void checkIfPalindromeTest() throws Exception {
        assertEquals(true, TwoPointers.checkIfPalindrome("racecar"));
    }  
}
