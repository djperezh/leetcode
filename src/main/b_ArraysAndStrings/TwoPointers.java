package main.b_ArraysAndStrings;

import java.util.*;

public class TwoPointers {
    /*
    * Example 1: check If Palindrome 
    * 125. Valid Palindrome (https://leetcode.com/problems/valid-palindrome/description/)
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

    /*
     * Example 2: Given a sorted array of unique integers and a target integer, return true if there exists a pair of numbers that sum to target, false otherwise.
     * This problem is similar to Two Sum. (In Two Sum, the input is not sorted).
     * example
     * nums = [1, 2, 4, 6, 8, 9, 14, 15], target = 13
     * Output: true -> 4 + 9 = 13. 
    */
    public boolean checkForTarget(int[] nums, int target) {
        int r = nums.length;
        for (int l = 0; l < r; l++) {
            int sum = nums[l] + nums[r];
            if (sum == target) return true;

            if (sum > target) {
                r--;
                l--; // so we can keep l pointin to the same element for the next iteration
            }
        }
        return false;
    }

    /*
     * Example 3: 
     * Similar to 88. Merge Sorted Array (https://leetcode.com/problems/merge-sorted-array/description/)
     * Given two sorted integer arrays arr1 and arr2, return a new array that combines both of them and is also sorted.
    */
    public List<Integer> combine(int[] arr1, int[] arr2) {
        List<Integer> ans = new ArrayList<>();
        int x = 0;
        int y = 0;
        
        while (x < arr1.length && y < arr2.length) {
            if (arr1[x] == arr2[y]) {
                ans.add(x++);
                ans.add(y++);
            } else {
                if (arr1[x] > arr2[y]) {
                    ans.add(y++);
                } else {
                    ans.add(x++);
                }
            }
        }
        while (x < arr1.length) ans.add(arr1[x++]);
        while (y < arr1.length) ans.add(arr2[y++]);
        return ans;
    }

    /*
     * Example 4
     * 392. Is Subsequence. (https://leetcode.com/problems/is-subsequence)
     * 
     * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
     * A subsequence of a string is a new string that is formed from the original string
     * by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters.
     * (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
    */
    public static boolean isSubsequence(String s, String t) {
        if (s.length() > t.length()) return false;
        if (s.length() == 0) return true;
        int j = 0;
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == s.charAt(j)) j++;
            if (j == s.length()) return true;
        }
        return false;
    }

    /*
     * 1. Two Sum (https://leetcode.com/problems/two-sum/description/)
     * Given an "unsorted" array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     * You can return the answer in any order.
     * NOTE: we are assuming that no dupes elements are allowed (otherwise, use a map where the value is a Set containin the list of indexes)
    */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> elementIndexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (elementIndexMap.containsKey(complement) && elementIndexMap.get(complement) != i) return new int[] {i, elementIndexMap.get(complement)};
            elementIndexMap.put(nums[i], i);
        }
        return null;
    }

    /*
     * 344. Reverse String (https://leetcode.com/problems/reverse-string)
     * Write a function that reverses a string. The input string is given as an array of characters s.
     * You must do this by modifying the input array in-place with O(1) extra memory.
    */
    public static void reverseString(char[] s) {
        int j = s.length - 1;
        
        for (int i = 0; i < j; i++) {
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
            j--;
        }
    }

    /*
     * 557. Reverse Words in a String III (https://leetcode.com/problems/reverse-words-in-a-string-iii)
     * Given a string s, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
     * Input: s = "Let's take LeetCode contest"
     * Output: "s'teL ekat edoCteeL tsetnoc"
    */
    public String reverseWords(String s) {
        int i = 0;

        char[] arr = s.toCharArray();
        for (int runner = 0; runner < s.length(); runner++) {
            if (arr[runner] == ' ' || runner == s.length() - 1) {
                int j = runner == s.length() - 1 ? runner : runner - 1;
                while (i < j) {
                    char tmp = arr[j];
                    arr[j--] = arr[i];
                    arr[i++] = tmp;
                }
                i = runner + 1;
            }
        }

        return new String(arr);
    }

    /*
     * 917. Reverse Only Letters (https://leetcode.com/problems/reverse-only-letters)
     * Given a string s, reverse the string according to the following rules:
     * All the characters that are not English letters remain in the same position.
     * All the English letters (lowercase or uppercase) should be reversed.
     * Return s after reversing it.
    */
    public String reverseOnlyLetters(String s) {
        char[] arr = s.toCharArray();

        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            while (!isLetter(arr[i]) && i < j) i++;
            while (!isLetter(arr[j]) && i < j) j--;
            char tmp = arr[j];
            arr[j] = arr[i];
            arr[i] = tmp;
        }

        return new String(arr);
    }

    private static boolean isLetter(char c) {
        int val = c;
        return (val >= 'a' && val <= 'z') || (val >= 'A' && val <= 'Z');
    }

    /*
     * 2540. Minimum Common Value (https://leetcode.com/problems/minimum-common-value)
     * Given two integer arrays nums1 and nums2, sorted in non-decreasing order, return the minimum integer common to both arrays.
     * If there is no common integer amongst nums1 and nums2, return -1.
     * Note that an integer is said to be common to nums1 and nums2 if both arrays have at least one occurrence of that integer.
    */
    public int getCommon(int[] nums1, int[] nums2) {
        if ((nums1[nums1.length - 1] < nums2[0]) || (nums2[nums2.length - 1] < nums1[0])) return -1;

        int i = 0;
        int j = 0;

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) return nums1[i];
            if (i == nums1.length - 1 && j == nums2.length - 1) break;

            if (nums1[i] < nums2[j]) {
                while (nums1[i] < nums2[j] && i < nums1.length - 1) i++;
            } else {
                while (nums2[j] < nums1[i] && j < nums2.length - 1) j++;
            }
        }
        return -1;
    }

    /*
     * 283. Move Zeroes (https://leetcode.com/problems/move-zeroes)
     * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
     * Note that you must do this in-place without making a copy of the array.
    */
    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length < 2) return;
        int runner = 0;
        for (int ini = 0; ini < nums.length; ini++) {
            if (nums[ini] != 0) continue;
            runner = ini;
            while (nums[runner] == 0) {
                runner++;
                if (runner == nums.length) return;
            }
            nums[ini] = nums[runner];
            nums[runner] = 0;
        }
    }

    /*
     * 2000. Reverse Prefix of Word (https://leetcode.com/problems/reverse-prefix-of-word/description/)
     * Given a 0-indexed string word and a character ch, reverse the segment of word that starts at index 0 and ends at the index of the first occurrence of ch (inclusive).
     * If the character ch does not exist in word, do nothing.
     * For example, if word = "abcdefd" and ch = "d", then you should reverse the segment that starts at 0 and ends at 3 (inclusive). 
     * The resulting string will be "dcbaefd".Return the resulting string.
     * Input: word = "abcdefd", ch = "d"
     * Output: "dcbaefd"
     * Explanation: The first occurrence of "d" is at index 3. Reverse the part of word from 0 to 3 (inclusive), the resulting string is "dcbaefd".
    */
    public String reversePrefix(String word, char ch) {
        char[] arr = word.toCharArray();
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (ch == c) {
                index = i;
                break;
            }
        }
        return (index == -1) ? word : reverse(arr, 0, index);
    }

    /*
     * 344. Reverse String ( https://leetcode.com/problems/reverse-string/description/ )
    */
    private static String reverse(char[] arr, int ini, int end) {
        for (int i = ini, j = end; i < j; i++, j--) {
            char c = arr[i];
            arr[i] = arr[j];
            arr[j] = c;
        }
        return new String(arr);
    }

    /*
     * 977. Squares of a Sorted Array (https://leetcode.com/problems/squares-of-a-sorted-array)
     * Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.
     * find an O(n) solution
     * Input: nums = [-4,-1,0,3,10]
        Output: [0,1,9,16,100]
        Explanation: After squaring, the array becomes [16,1,0,9,100].
        After sorting, it becomes [0,1,9,16,100].
    */
    public static int[] sortedSquares(int[] nums) {
        // indexes
        int r = nums.length - 1;
        int l = 0;
        int index = nums.length - 1;
        int[] result = new int[nums.length];
        
        while (l <= r) {
            if (l == r) {
                result[index--] = nums[r] * nums[r];
                r--;
                continue;
            }
            
            if ((nums[r] * nums[r]) == (nums[l] * nums[l])) {
                result[index--] = nums[r] * nums[r];
                result[index--] = nums[l] * nums[l];
                r--;
                l++;
                continue;
            }
            
            if ((nums[r] * nums[r]) > (nums[l] * nums[l])) {
                result[index--] = nums[r] * nums[r];
                r--;
            } else {
                result[index--] = nums[l] * nums[l];
                l++;
            }
        }
        
        return result;
    }

}
