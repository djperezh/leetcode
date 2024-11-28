package main.c_Hashing;

import java.util.*;

public class Counting {
    /*
     * You are given a string s and an integer k. Find the length of the longest substring that contains at most k distinct characters.
     * For example, 
     * Input: s = "eceba", k = 2
     * Output: 3 -> The longest substring with at most 2 distinct characters is "ece".
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
     * 2248. Intersection of Multiple Arrays (https://leetcode.com/problems/intersection-of-multiple-arrays/description/)
     * Given a 2D array nums that contains n arrays of distinct integers, return a sorted array containing all the numbers that appear in all n arrays.
     * For example, given nums = [[3,1,2,4,5],[1,2,3,4],[3,4,5,6]], return [3, 4]. 3 and 4 are the only numbers that are in all arrays.
     * Constraints:
     * 1 <= nums.length <= 1000
     * 1 <= sum(nums[i].length) <= 1000
     *  <= nums[i][j] <= 1000
     * All the values of nums[i] are unique.
    */
    public static List<Integer> intersection(int[][] nums) { 
        List<Integer> ans = new ArrayList<>();
        int[] freq = new int[1001];
        for (int[] arr : nums) {
            for (int n : arr) freq[n]++;
        }
        for (int i = 1; i < freq.length; i++) if (freq[i] == nums.length) ans.add(i);
        return ans;
    }

    /*
     * 1941. Check if All Characters Have Equal Number of Occurrences (https://leetcode.com/problems/check-if-all-characters-have-equal-number-of-occurrences/description/)
     * Example 3: 1941. Check if All Characters Have Equal Number of Occurrences
     * Given a string s, determine if all characters have the same frequency.
     * For example:
     * given s = "abacbc", return true. All characters appear twice.
     * Given s = "aaabb", return false. "a" appears 3 times, "b" appears 2 times. 3 != 2.
    */
    public static boolean areOccurrencesEqual(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) freq.put(c, freq.getOrDefault(c, 0) + 1);
        int k = freq.get(s.charAt(0));
        for (char key : freq.keySet()) if (freq.get(key) != k) return false;
        return true;
    }

}
