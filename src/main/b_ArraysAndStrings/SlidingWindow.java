package main.b_ArraysAndStrings;

import java.util.HashMap;
import java.util.Map;

public class SlidingWindow {
    /*
     * Given an array of positive integers nums and an integer k,
     * find the length of the longest subarray whose sum is less than or equal to k
     * Example:
     * nums = [3, 1, 2, 7, 4, 2, 1, 1, 5] and k = 8
     * output = 4 -> [4, 2, 1, 1]
    */
    public int findLength(int[] nums, int k) {
        int l = 0;
        int curr = 0;
        int ans = 0;
        for (int r = 0; r < nums.length; r++) {
            curr += nums[r];
            while (curr > k) curr -= nums[l++];
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }

    /*
     * 713. Subarray Product Less Than K ()
     * Given an array of integers nums and an integer k, return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than k.
     * Input: nums = [10,5,2,6], k = 100
     * Output: 8
     * Explanation: The 8 subarrays that have product less than 100 are:
     * [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
     * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
    */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int l = 0;
        int curr = 1;
        int ans = 0;
        for (int r = 0; r < nums.length; r++) {
            curr *= nums[r];
            while (curr >= k && l <= r) curr /= nums[l++];
            ans += (r - l) + 1; 
        }
        return ans;
    }

    /*
    * Given an integer array nums and an integer k, find the sum of the subarray with the largest sum whose length is k.
    */
    public static int findBestSubarray(int[] nums, int k) {
        int ans = Integer.MIN_VALUE;
        
        int curr = 0;
        int l = 0;
        for (int r = 0; r < nums.length; r++) {
            curr += nums[r];
            if ((r - l) + 1 > k) curr -= nums[l++];
            if ((r - l) + 1 == k) ans = Math.max(ans, curr);
        }
        return Math.max(ans, curr);
    }

    /*
     * 643. Maximum Average Subarray I
     * You are given an integer array nums consisting of n elements, and an integer k.
     * Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value.
     * Any answer with a calculation error less than 10-5 will be accepted.
     * Input: nums = [1,12,-5,-6,50,3], k = 4
        Output: 12.75000
        Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 12.75
    */
    public static double findMaxAverage(int[] nums, int k) {
        int l = 0;
        double curr = 0;
        for (int r = 0; r < k; r++) curr += nums[r];
        double ans = curr;
        
        for (int r = k; r < nums.length; r++) {
            curr += nums[r] - nums[l++];
            ans = Math.max(ans, curr);
        }
        return (ans * 1.0) / k; // avg
    }

    /*
     * 1004. Max Consecutive Ones III
     * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
     * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
     * Output: 6
     * Explanation: [1,1,1,0,0,1,1,1,1,1,1]
    */
    public static int longestOnes(int[] nums, int k) {
        int counter = 0;
        int result = 0;
        int left = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) counter++;

            // Shrink window until valid again
            while (counter > k) {
                result = Math.max(result, i - left);
                if (nums[left] == 0) counter--;
                left++;
            }
        }

        // "nums.length - left" to cover the case where no ZERO's found
        return Math.max(result, nums.length - left);
    }

    /*
     * 209. Minimum Size Subarray Sum
     * Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose sum is greater than or equal to target.
     * If there is no such subarray, return 0 instead.
    */
    public static int minSubArrayLen(int target, int[] nums) {
        // TODO
        return -1;
    }

    /*
     * 1456. Maximum Number of Vowels in a Substring of Given Length
     * Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
     * Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
     * Input: s = "abciiidef", k = 3
     * Output: 3
     * Explanation: The substring "iii" contains 3 vowel letters.
    */
    public static int maxVowels(String s, int k) {
        // TODO
        return -1;
    }

    /*
     * 1208. Get Equal Substrings Within Budget
     * You are given two strings s and t of the same length and an integer maxCost.
     * You want to change s to t. Changing the ith character of s to ith character of t costs |s[i] - t[i]| 
     * i.e., the absolute difference between the ASCII values of the characters. 
     * Return the maximum length of a substring of s that can be changed to be the same as the corresponding substring of t with a cost less than or equal to maxCost.
     * If there is no substring from s that can be changed to its corresponding substring from t, return 0.
    */
    public static int equalSubstring(String s, String t, int maxCost) {
        // TODO
        return -1;
    }

    /*
     * You are given a string s and an integer k. Find the length of the longest substring that contains at most k distinct characters.
     * For example, 
     * Input: s = "eceba", k = 2
     * Output: 3 -> The longest substring with at most 2 distinct characters is "ece".
     * NOTE: This problem was found in the "Hashing-Counting" section, however is also a candidate for Sliding Window
    */
    public static int findLongestSubstring(String s, int k) {
        Map<Character, Integer> freq = new HashMap<>();
        int ans = 0;

        int l = 0;
        for (int r = 0; r < s.length(); r++) {
            freq.put(s.charAt(r), freq.getOrDefault(s.charAt(r), 0) + 1);

            while (freq.size() > k) {
                if (freq.get(s.charAt(l)) == 1) {
                    freq.remove(s.charAt(l));
                } else {
                    freq.put(s.charAt(l), freq.get(s.charAt(l)) - 1);
                }
                l++;
            }

            ans = Math.max(ans, (r - l) + 1);
        }

        return ans;
    }
}
