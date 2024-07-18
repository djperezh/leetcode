package main.Hashing;

import java.util.*;

public class CheckingForExistance {
    /*
     * 1. Two Sum (https://leetcode.com/problems/two-sum/description/)
     * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
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
        return new int[] { -1, -1};
    }

    /*
     * 2351. First Letter to Appear Twice (https://leetcode.com/problems/first-letter-to-appear-twice/)
     * Given a string s consisting of lowercase English letters, return the first letter to appear twice.
     * Note: A letter a appears twice before another letter b if the second occurrence of a is before the second occurrence of b.
     * s will contain at least one letter that appears twice.
    */
    public static char repeatedCharacter(String s) {
        Set<Character> uniques = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (uniques.contains(c)) return c;
            uniques.add(c);
        }
        return '\0';
    }

    /*
     * Given an integer array nums, find all the unique numbers x in nums that satisfy the following: 
     * x + 1 is not in nums, and x - 1 is not in nums.
    */
    public static List<Integer> findNumbers(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int n : nums) s.add(n);

        List<Integer> ans = new ArrayList<>();
        for (int n : nums) {
            if (!s.contains(n + 1) && !s.contains(n - 1)) ans.add(n);
        }
        return ans;
    }

    /*
     * 1832. Check if the Sentence Is Pangram (https://leetcode.com/problems/check-if-the-sentence-is-pangram/description/)
     * Given a string sentence containing only lowercase English letters, return true if sentence is a pangram, or false otherwise.
     * A pangram is a sentence where every letter of the English alphabet appears at least once.
    */
    public static boolean checkIfPangram(String sentence) {
        Set<Character> s = new HashSet<>();
        for (int i = 'a'; i <= 'z'; i++) s.add((char)i);
        for (char c : sentence.toCharArray()) s.remove(c);
        return s.size() == 0;
    }

    /*
     * 268. Missing Number (https://leetcode.com/problems/missing-number/description/)
     * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
     * Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
    */
    public static int missingNumber(int[] nums) {
        int actual = 0;
        for (int num : nums) actual += num;
        
        int n = nums.length;
        long sum = (n * (n + 1)) / 2;
        
        return (int)(sum - actual);
    }

    /*
     * 1426. Counting Elements (https://leetcode.com/problems/counting-elements/description/)
     * Given an integer array arr, count how many elements x there are, such that x + 1 is also in arr. If there are duplicates in arr, count them separately.
    */
    public int countElements(int[] arr) {
        Set<Integer> s = new HashSet<>();
        for (int n : arr) s.add(n);
        int ans = 0;
        for (int n : arr) ans += (s.contains(n + 1)) ? 1 : 0;
        return ans;
    }
}
