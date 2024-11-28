package main.e_StackAndQueues;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Stacks {
    /*
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
        // TODO
        return null;
    }
}
