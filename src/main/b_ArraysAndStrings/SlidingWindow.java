package main.b_ArraysAndStrings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SlidingWindow {
    /*
     * Example 1: Given an array of positive integers nums and an integer k, 
     * find the length of the longest subarray whose sum is less than or equal to k.
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
     * Example 2: 
     * See similar probel bellow "longestOnes"
    */

    /*
     * Example 3
     * 713. Subarray Product Less Than K ( https://leetcode.com/problems/subarray-product-less-than-k/description/ )
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
    * Example 4:
    * Given an integer array nums and an integer k, 
    * find the sum of the subarray with the largest sum whose length is k.
    * similar to
    * 1343. Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold
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
     * 1343. Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold ( https://leetcode.com/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/description/ )
     * Given an array of integers arr and two integers k and threshold, 
     * return the number of sub-arrays of size k and average greater than or equal to threshold.
    */
    public static int numOfSubarrays(int[] arr, int k, int threshold) {
        int l = 0;
        int curr = 0;
        int ans = 0;

        for (int r = 0; r < k; r++) curr += arr[r];
        if (curr/k >= threshold) ans++;

        for (int r = k; r < arr.length; r++) {
            curr += arr[r];
            curr -= arr[l++];
            if (curr/k >= threshold) ans++;
        }
        
        return ans;
    }

    /*
     * 643. Maximum Average Subarray I ( https://leetcode.com/problems/maximum-average-subarray-i/description/ )
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
     * 1004. Max Consecutive Ones III ( https://leetcode.com/problems/max-consecutive-ones-iii/description/ )
     * Given a binary array nums and an integer k, 
     * return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
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
     * 209. Minimum Size Subarray Sum ( https://leetcode.com/problems/minimum-size-subarray-sum )
     * Given an array of positive integers nums and a positive integer target, 
     * return the minimal length of a subarray whose sum is greater than or equal to target.
     * If there is no such subarray, return 0 instead.
    */
    public static int minSubArrayLen(int target, int[] nums) {
        int ans = Integer.MAX_VALUE;
        int sum = 0;
        int counter = 0;

        int l = 0;
        for (int r = 0; r < nums.length; r++) {
            sum += nums[r];
            counter++;
            // slide window
            while (sum >= target) {
                ans = Math.min(ans, counter);
                sum -= nums[l];
                counter--;
                l++;
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    /*
     * 1456. Maximum Number of Vowels in a Substring of Given Length ( https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/ )
     * Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
     * Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
     * Input: s = "abciiidef", k = 3
     * Output: 3
     * Explanation: The substring "iii" contains 3 vowel letters.
    */
    public static int maxVowels(String s, int k) {
        Set<Character> vowels = new HashSet<>(Arrays.asList(new Character[] {'a', 'e', 'i', 'o', 'u'}));
        int ans = 0;
        char[] arr = s.toCharArray();
        for (int i = 0; i < k; i++) ans += vowels.contains(arr[i]) ? 1 : 0;
        
        int curr = ans;
        int i = 0;
        for (int j = k; j < arr.length; j++) {
            curr += vowels.contains(arr[j]) ? 1 : 0;
            curr -= vowels.contains(arr[i++]) ? 1 : 0;
            ans = Math.max(ans, curr);
        }
        return ans;
    }

    /*
     * 1208. Get Equal Substrings Within Budget ( https://leetcode.com/problems/get-equal-substrings-within-budget/ )
     * You are given two strings s and t of the same length and an integer maxCost.
     * You want to change s to t. Changing the ith character of s to ith character of t costs |s[i] - t[i]| 
     * i.e., the absolute difference between the ASCII values of the characters. 
     * Return the maximum length of a substring of s that can be changed to be the same as the corresponding substring of t with a cost less than or equal to maxCost.
     * If there is no substring from s that can be changed to its corresponding substring from t, return 0.
    */
    public static int equalSubstring(String s, String t, int maxCost) {
        int[] cost = new int[s.length()];
        char[] origin = s.toCharArray();
        char[] target = t.toCharArray();
        for (int i = 0; i < s.length(); i++) cost[i] = Math.abs(origin[i] - target[i]);

        int leftIndex = 0;
        int tmpCost = 0;
        int result = 0;
        for (int i = leftIndex; i < cost.length; i++) {
            tmpCost += cost[i];
            // Condition
            if (tmpCost > maxCost) {
                // update result
                result = Math.max(result, i - leftIndex);
                // then slide window (left index) until valid again
                while (tmpCost > maxCost) tmpCost -= cost[leftIndex++];
            }
        }
        // in case remaining right elements are valid
        return Math.max(result, cost.length - leftIndex);
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

    /*
     * A farmer produces vegetables which are put in Plastic Crates Containers
     * and moved into storage rooms to be picked up by the Distributor.
     * Storage rooms are independent one from another but aligned in the same row.
     * Distributor handles the pickup of the product, However a considerable "logistics fee" is deducted from the bill
     * if the product is in multiple non-consecutive storage units.
     * Farmer wants to avoid the bill deduction and since it's cheaper for the farmer to move the product,
     * Farmer decides to move the product to consecutive storage units ready for PickUp
     * 
     * Given the storage units as a bynary array representation 
     * where "1" means "unit full with product" and "0" means empty,
     * implement a function "getMinMoves()" to get the minimun number of moves (swaps) needed
     * so the product is in consecutive storage units
     * 
     * i.e.
     * input = [0,1,0,1,0,1,1,0]
     * output = 1
     * explanation = product in second unit is moved to fifth unit ([0,0,0,1,1,1,1,0])
    */
    public static int findMinSwaps(int[] input) {
        int k = 0;
        for (int n : input) k += n == 1 ? 1 : 0;

        if (k == 0 || k == input.length) return k;

        int ans = -1;
        int moves = 0;
        int i = 0;
        int j = 0;
        while (j < input.length) {
            if (j-i < k) {
                moves += input[j++] == 0 ? 1 : 0;
            } else {
                ans = ans == -1 ? moves : Math.min(ans, moves);
                moves -= input[i++] == 0 ? 1 : 0;
            }
        }
        return Math.min(ans, moves);
    }
}
