package main.c_Hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MoreHashingExamples {
    /*
     * Example 1.
     * 49. Group Anagrams (https://leetcode.com/problems/group-anagrams/description/)
     * Given an array of strings strs, group the anagrams together.
     * You can return the answer in any order.
     * 
     * O(N∗(M∗Log(M))
    */
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();

        Map<String, List<String>> tmp = new HashMap<>();
        for (String s : strs) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String sorted = new String(arr);
            if (!tmp.containsKey(sorted)) tmp.put(sorted, new ArrayList<String>());
            tmp.get(sorted).add(s);
        }

        for (String key : tmp.keySet()) result.add(tmp.get(key));
        return result;
    }

    /*
     * Example 2.
     * 2260. Minimum Consecutive Cards to Pick Up (https://leetcode.com/problems/minimum-consecutive-cards-to-pick-up/description/)
     * You are given an integer array cards where cards[i] represents the value of the ith card.
     * A pair of cards are matching if the cards have the same value.
     * Return the minimum number of consecutive cards you have to pick up to have a pair of matching cards among the picked cards.
     * If it is impossible to have matching cards, return -1.
    */
    public static int minimumCardPickup(int[] cards) {
        int result = -1;

        // card Value, Index
        Map<Integer, Integer> cardValueIndexMap = new HashMap<>();
        
        for (int i = 0; i < cards.length; i++) {
            if (cardValueIndexMap.containsKey(cards[i])) {
                result = result == -1 ? 
                    (i - cardValueIndexMap.get(cards[i])) + 1 : 
                    Math.min(result, (i - cardValueIndexMap.get(cards[i])) + 1);
            }
            cardValueIndexMap.put(cards[i], i);
        }

        return result;
    }

    /*
     * Example 3.
     * 2342. Max Sum of a Pair With Equal Sum of Digits (https://leetcode.com/problems/max-sum-of-a-pair-with-equal-sum-of-digits/description/) 
     * You are given a 0-indexed array nums consisting of positive integers. 
     * You can choose two indices i and j, such that i != j, and the sum of digits of the number nums[i] is equal to that of nums[j].
     * Return the maximum value of nums[i] + nums[j] that you can obtain over all possible indices i and j that satisfy the conditions.
     */
    public static int maximumSum(int[] nums) {
        int ans = -1;
        Map<Integer, Integer> digitSumMap = new HashMap<>();
        for (int n : nums) {
            int digitSum = getDigitSum(n);
            if (digitSumMap.containsKey(digitSum)) {
                ans = Math.max(ans, digitSumMap.get(digitSum) + n);
            }
            digitSumMap.put(digitSum, Math.max(digitSumMap.getOrDefault(digitSum, 0), n));
        }
        return ans;
    }

    private static int getDigitSum(int num) {
        int ans = 0;
        while (num > 0) {
            ans += num % 10;
            num /= 10;
        }
        return ans;
    }

    /*
     * Example 4.
     * 2352. Equal Row and Column Pairs (https://leetcode.com/problems/equal-row-and-column-pairs/description/)
     * Given a 0-indexed n x n integer matrix grid, return the number of pairs (ri, cj) such that row ri and column cj are equal.
     * A row and column pair is considered equal if they contain the same elements in the same order (i.e., an equal array).
    */
    public static int equalPairs(int[][] grid) {
        int ans = 0;

        Map<String, Integer> r = new HashMap<>();
        Map<String, Integer> c = new HashMap<>();

        for (int i = 0; i < grid.length; i++) {
            StringBuilder row = new StringBuilder();
            StringBuilder col = new StringBuilder();
            for (int j = 0; j < grid.length; j++) {
                row.append(Integer.toString(grid[i][j]));
                row.append(",");

                col.append(Integer.toString(grid[j][i]));
                col.append(",");
            }
            r.put(row.toString(), r.getOrDefault(row.toString(), 0) + 1);
            c.put(col.toString(), c.getOrDefault(col.toString(), 0) + 1);
        }

        for (String s : r.keySet()) ans += (r.get(s) * c.getOrDefault(s, 0));

        return ans;
    }

    /*
     * 383. Ransom Note (https://leetcode.com/problems/ransom-note/description/)
     * Given two strings ransomNote and magazine, 
     * return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
     * Each letter in magazine can only be used once in ransomNote.
    */
    public static boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> freq = new HashMap<>();
        
        for (int i = 0; i < magazine.length(); i++) {
            char c = magazine.charAt(i);
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        } 
            
        for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            if (!freq.containsKey(c) || freq.get(c) == 0) return false;
            freq.put(c, freq.get(c) - 1);
        }
        
        return true;
    }

    /*
     * 771. Jewels and Stones (https://leetcode.com/problems/jewels-and-stones/description/)
     * You're given strings jewels representing the types of stones that are jewels, and stones representing the stones you have.
     * Each character in stones is a type of stone you have. 
     * You want to know how many of the stones you have are also jewels.
     * Letters are case sensitive, so "a" is considered a different type of stone from "A".
    */
    public static int numJewelsInStones(String jewels, String stones) {
        int result = 0;
        Set<Character> jewelsSet = new HashSet<>();
        for(int i = 0; i < jewels.length(); i++) jewelsSet.add(jewels.charAt(i));
        for(int i = 0; i < stones.length(); i++) result += jewelsSet.contains(stones.charAt(i)) ? 1 : 0;
        return result;
    }

    /*
     * 3. Longest Substring Without Repeating Characters (https://leetcode.com/problems/longest-substring-without-repeating-characters/description/)
     * Given a string s, 
     * find the length of the longest substring without repeating characters.
    */
    public static int lengthOfLongestSubstring(String s) {
        int result = 0;
        Set<Character> uniquesSet = new HashSet<>();
        int leftIndex = 0;
        
        // Sliding window approach (key: "LongestSubstring")
        // get the window that meets the criteria (use set to keep uniques)
        for (int rightIndex = 0; rightIndex < s.length(); rightIndex++) {
            char c = s.charAt(rightIndex);
            
            // Check constraint:
            if (uniquesSet.contains(c)) {
                // if criteria is no longer meet slide window (one position from Left)
                // until criteria is valid again
                char l = s.charAt(leftIndex);
                while (l != c) {
                    uniquesSet.remove(l);
                    leftIndex++;
                    l = s.charAt(leftIndex);
                }
                uniquesSet.remove(l);
                leftIndex++;
            }
            
            uniquesSet.add(c);
            
            // update result
            result = Math.max(result, uniquesSet.size());
        }
        
        return result;
    }
}
