package main.ArraysAndStrings;


public class TwoPointers {

    /*
    *
    * Complexity
    * Time: O(N)
    * Space: O(1)
    */
    public static boolean checkIfPalindrome(String s) {
        if (s == null || s.isEmpty()) return true;
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }
}
