package main.e_StackAndQueues;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Stacks {
    /*
     * Example 1.
     * 20. Valid Parentheses (https://leetcode.com/problems/valid-parentheses/description/)
     * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
    */
    public static boolean isValid(String s) {
        Stack<Character> parentheses = new Stack<>();
        Set<Character> openning = new HashSet<Character>(Arrays.asList(new Character[] {'(', '{', '['}));
        
        for (char c : s.toCharArray()) {
            if (openning.contains(c)) {
                parentheses.push(c);
            } else {
                if (parentheses.empty()) return false;
                char open = parentheses.pop();
                switch (c) {
                    case ')':
                        if (open != '(') return false;    
                        break;
                    case ']':
                        if (open != '[') return false;
                        break;
                    default:
                        if (open != '{') return false;
                        break;
                }
            }
        }
        return parentheses.empty();
    }

    /*
     * Example 2.
     * 1047. Remove All Adjacent Duplicates In String (https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/description/)
     * You are given a string s consisting of lowercase English letters. 
     * A duplicate removal consists of choosing two adjacent and equal letters and removing them.
     * We repeatedly make duplicate removals on s until we no longer can. 
     * Return the final string after all such duplicate removals have been made. 
     * It can be proven that the answer is unique.
     * Example:
     * Input: s = "abbaca", Output: "ca"
     * Explanation:
     * For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.
     * The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
    */
    public static String removeDuplicates(String s) {
        Stack<Character> letters = new Stack<>();

        for (char c : s.toCharArray()) {
            if (!letters.empty() && c == letters.peek()) {
                letters.pop();
            } else {
                letters.push(c);
            }
        }

        String ans = "";
        while (!letters.isEmpty()) ans = letters.pop() + ans;
        return ans;
    }

    /*
     * Example 3.
     * 844. Backspace String Compare (https://leetcode.com/problems/backspace-string-compare/description/)
     * Given two strings s and t, return true if they are equal when both are typed into empty text editors. 
     * '#' means a backspace character.
     * Note that after backspacing an empty text, the text will continue empty.
     * Follow up: Can you solve it in O(n) time and O(1) space?
    */
    public static boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;

        while (i >= 0 && j >= 0) {
            i = updateIndex(i, s);
            j = updateIndex(j, t);
            if (i >= 0 && j >= 0 && s.charAt(i) != t.charAt(j)) return false;
            i--;
            j--;
        }

        if (i >= 0) i = updateIndex(i, s);
        if (j >= 0) j = updateIndex(j, t);

        return i == j;
    }

    private static int updateIndex(int index, String str) {
        if (index < 0 || str.charAt(index) != '#') return index;

        int toSkip = 0;
        while (index >= 0 && str.charAt(index) == '#') {
            toSkip++;
            index--;
        }
        while (index >= 0 && toSkip > 0) {
            toSkip -= (str.charAt(index) != '#') ? 1 : -1;
            index--;
        }

        return updateIndex(index, str);
    }

    /*
     * 71. Simplify Path (https://leetcode.com/problems/simplify-path/description/)
     * Given an absolute path for a Unix-style file system, which begins with a slash '/', transform this path into its simplified canonical path.
     * In Unix-style file system context, a single period '.' signifies the current directory, a double period ".." denotes moving up one directory level, and multiple slashes such as "//" are interpreted as a single slash. In this problem, treat sequences of periods not covered by the previous rules (like "...") as valid names for files or directories.
     * The simplified canonical path should adhere to the following rules:
     *     It must start with a single slash '/'.
     *     Directories within the path should be separated by only one slash '/'.
     *     It should not end with a slash '/', unless it's the root directory.
     *     It should exclude any single or double periods used to denote current or parent directories.
    */
    public static String simplifyPath(String path) {
        Stack<String> pathElements = new Stack<>();
        String[] elements = path.split("/");

        for (String element : elements) {
            if (element.equals(".") || element.isEmpty()) continue;
            if (element.equals("..") && !pathElements.isEmpty()){
                pathElements.pop();
            } else {
                pathElements.push(element);
            }
        }

        StringBuilder ans = new StringBuilder();
        while (!pathElements.isEmpty()) ans.insert(0, "/" + pathElements.pop());
        
        String result = ans.toString();
        return result.isEmpty() ? "/" : result;
    }

    /*
     * 1544. Make The String Great (https://leetcode.com/problems/make-the-string-great/description/)
     * Given a string s of lower and upper case English letters.
     * A good string is a string which doesn't have two adjacent characters s[i] and s[i + 1] where: 
     *    0 <= i <= s.length - 2 
     *    s[i] is a lower-case letter and s[i + 1] is the same letter but in upper-case or vice-versa. 
     * To make the string good, you can choose two adjacent characters that make the string bad and remove them.
     * You can keep doing this until the string becomes good. Return the string after making it good.
     * The answer is guaranteed to be unique under the given constraints.
     * Notice that an empty string is also good.
    */
    public static String makeGood(String s) {
        Stack<Character> myStack = new Stack<>();
        
        for (Character c: s.toCharArray()) {
            if (myStack.size() == 0) {
                myStack.push(c);
            } else {
                Character curr = myStack.peek();
                if ((curr ==  Character.toLowerCase(curr) && Character.toUpperCase(curr) == c) || 
                    (curr ==  Character.toUpperCase(curr) && Character.toLowerCase(curr) == c)){
                    myStack.pop();
                } else {
                    myStack.push(c);
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while (myStack.size() > 0) sb.insert(0,myStack.pop());
        return sb.toString();
    }

    /*
     * 946. Validate Stack Sequences (https://leetcode.com/problems/validate-stack-sequences/description/)
     * Given two integer arrays pushed and popped each with distinct values, 
     * return true if this could have been the result of a sequence of push and pop operations
     * on an initially empty stack, or false otherwise.
    */
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> s = new Stack<>();

        int poppedIndex = 0;
        for (int pushedIndex = 0; pushedIndex < pushed.length; pushedIndex++) {
            int val = pushed[pushedIndex];
            s.push(val);

            if (val == popped[poppedIndex]) {
                while (!s.isEmpty() && s.peek() == popped[poppedIndex]) {
                    s.pop();
                    poppedIndex++;
                }
            }
        }

        for (int i = poppedIndex; i < popped.length; i++) {
            if (s.isEmpty() || popped[i] != s.peek()) return false;
            s.pop();
        }

        return s.isEmpty();
    }

    /*
     * 2390. Removing Stars From a String (https://leetcode.com/problems/removing-stars-from-a-string/description/)
     * You are given a string s, which contains stars *.
     * In one operation, you can:
     * Choose a star in s.
     * Remove the closest non-star character to its left, as well as remove the star itself.
     * Return the string after all stars have been removed.
    */
    public static String removeStars(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '*') 
                sb.deleteCharAt(sb.length() - 1);
            else
                sb.append(c);
        }
        return sb.toString();
    }

    /*
     * 2434. Using a Robot to Print the Lexicographically Smallest String (https://leetcode.com/problems/using-a-robot-to-print-the-lexicographically-smallest-string/description/)
     * You are given a string s and a robot that currently holds an empty string t. Apply one of the following operations until s and t are both empty:
     * Remove the first character of a string s and give it to the robot. The robot will append this character to the string t.
     * Remove the last character of a string t and give it to the robot. The robot will write this character on paper.
     * Return the lexicographically smallest string that can be written on the paper.
    */
    public static String robotWithString(String s) {
        StringBuilder ans = new StringBuilder();
        
        char[] arr = s.toCharArray();
        int[] freq = new int[27];
        for (int charOriginalPossition = 0; charOriginalPossition < arr.length; charOriginalPossition++) {
            int charIndex = arr[charOriginalPossition] - 'a';
            freq[charIndex] = charOriginalPossition;
        }

        Stack<Character> stack = new Stack<>();
        int i = 0;
        for (char c = 'a'; c <= 'z' + 1; c++) {

            while (!stack.isEmpty() && stack.peek() <= c) ans.append(stack.pop());

            int charOriginalPossition = freq[c - 'a'];
            while (i <= charOriginalPossition) {
                if (arr[i] == c) {
                    ans.append(arr[i++]);
                } else {   
                    stack.push(arr[i++]);
                }
            }
        }

        return ans.toString();
    }

    /*
     * 735. Asteroid Collision (https://leetcode.com/problems/asteroid-collision/description/)
     * We are given an array asteroids of integers representing asteroids in a row. The indices of the asteriod in the array represent their relative position in space.
     * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
     * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
    */
    public static int[] asteroidCollision(int[] asteroids) {
        if (asteroids == null || asteroids.length < 2) return asteroids;

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < asteroids.length; i++) {
            int a = asteroids[i];
            if (a > 0) {
                stack.push(a);
            } else {
                // If collition with smallest value then destroy
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < -a) stack.pop();
                // if no collitions (prev and curr asteroid going left) 
                if (stack.isEmpty() || stack.peek() < 0)  stack.push(a);
                // If collition with same value then destroy both
                if (stack.peek() == -a) stack.pop();
            }
        }
        
        int[] ans  = new int[stack.size()];
        int i = stack.size() - 1;
        while (stack.size() > 0) ans[i--] = stack.pop();
        return ans;
    }
}
