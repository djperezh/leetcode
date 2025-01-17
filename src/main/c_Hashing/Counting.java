package main.c_Hashing;

import java.util.*;
import java.util.stream.Collectors;

public class Counting {
    /*
     * Example 1.
     * 3. Longest Substring Without Repeating Characters (https://leetcode.com/problems/longest-substring-without-repeating-characters/description/)
     * You are given a string s and an integer k. Find the length of the longest substring that contains at most k distinct characters.
     * For example, 
     * Input: s = "eceba", k = 2
     * Output: 3 -> The longest substring with at most 2 distinct characters is "ece".
    */
    public static int findLongestSubstring(String s, int k) {
        Map<Character, Integer> distinctCharsFreq = new HashMap<>();
        int ans = 0;

        int l = 0;
        for (int r = 0; r < s.length(); r++) {
            distinctCharsFreq.put(s.charAt(r), distinctCharsFreq.getOrDefault(s.charAt(r), 0) + 1);

            while (distinctCharsFreq.size() > k) {

                // Decrease the frequence for the char @ "left" pointer
                if (distinctCharsFreq.get(s.charAt(l)) == 1) {
                    distinctCharsFreq.remove(s.charAt(l));
                } else {
                    distinctCharsFreq.put(s.charAt(l), distinctCharsFreq.get(s.charAt(l)) - 1);
                }
                l++;
            }

            ans = Math.max(ans, (r - l) + 1);
        }

        return ans;
    }

    /*
     * Example 2.
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
        int[] freq = new int[1001]; // From constraints
        for (int[] arr : nums) {
            for (int n : arr) freq[n]++;
        }
        for (int i = 1; i < freq.length; i++) if (freq[i] == nums.length) ans.add(i); // nums.length = number of arrays
        return ans;
    }

    /*
     * Example 3: 
     * 1941. Check if All Characters Have Equal Number of Occurrences (https://leetcode.com/problems/check-if-all-characters-have-equal-number-of-occurrences/description/)
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

    /*
     * Example 4.
     * 560. Subarray Sum Equals K (https://leetcode.com/problems/subarray-sum-equals-k/description/)
     * Given an array of integers nums and an integer k,
     * return the total number of subarrays whose sum equals to k
     * A subarray is a contiguous non-empty sequence of elements within an array.
     * 
    */
    public static int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        counts.put(0,1);

        int ans = 0;
        int curr = 0;
        for (int n : nums) {
            curr += n;
            ans += counts.getOrDefault(curr - k, 0);
            counts.put(curr, counts.getOrDefault(curr, 0) + 1);
        }

        return ans;
    }

    /*
     * Example 5
     * 1248. Count Number of Nice Subarrays (https://leetcode.com/problems/count-number-of-nice-subarrays/description/)
     * Given an array of integers nums and an integer k.
     * A continuous subarray is called nice if there are k odd numbers on it.
     * Return the number of nice sub-arrays.
    */
    public static int numberOfSubarrays(int[] nums, int k) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        counts.put(0, 1);

        int ans = 0;
        int curr = 0;

        for (int n : nums) {
            curr += n % 2;
            ans += counts.getOrDefault(curr - k, 0);
            counts.put(curr, counts.getOrDefault(curr, 0) + 1);
        }

        return ans;
    }

    /*
     * 2225. Find Players With Zero or One Losses (https://leetcode.com/problems/find-players-with-zero-or-one-losses/description/)
     * You are given an integer array matches where matches[i] = [winneri, loseri] indicates that the player winneri defeated player loseri in a match.
     * Return a list answer of size 2 where:
     *     answer[0] is a list of all players that have not lost any matches.
     *     answer[1] is a list of all players that have lost exactly one match.
     * The values in the two lists should be returned in increasing order.
    */
    public static List<List<Integer>> findWinners(int[][] matches) {
        // set for winners 
        Set<Integer> winners = new HashSet<>();
        
        // Map for lost freq
        Map<Integer, Integer> lostFreq = new HashMap<>();
        
        // loop each match and update DS's
        for (int matchIndex = 0; matchIndex < matches.length; matchIndex++) {
            int winner = matches[matchIndex][0];
            int loser = matches[matchIndex][1];
            
            // if winner hasn't lost before (not in lostFreq) then add it to winners
            if (!lostFreq.containsKey(winner)) winners.add(winner);

            // If it's player's 1st lost (loser found in winners) then remove it from winners
            if (winners.contains(loser)) winners.remove(loser);
            
            // Update lostFreq.
            lostFreq.put(loser, lostFreq.getOrDefault(loser, 0) + 1);
        }
        
        // losser (ONCE)
        List<Integer> lossers = new ArrayList<>();
        for (Map.Entry<Integer, Integer> l: lostFreq.entrySet()) {
            if (l.getValue() == 1) lossers.add(l.getKey());
        }
        
        // Set Result: add winners set and lostFreq keySet
        List<List<Integer>> result = new ArrayList<>();
        result.add(winners.stream().sorted().collect(Collectors.toList()));
        result.add(lossers.stream().sorted().collect(Collectors.toList()));
    
        return result;
    }

    /*
     * 1133. Largest Unique Number (https://leetcode.com/problems/largest-unique-number/description/)
     * Given an integer array nums, return the largest integer that only occurs once. If no integer occurs once, return -1.
    */
    public static int largestUniqueNumber(int[] nums) {
        int result = -1;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i : nums) freq.put(i, freq.getOrDefault(i, 0) + 1);
        for (int i : freq.keySet()) {
            if (freq.get(i) == 1) result = Math.max(result, i);
        }
        return result;
    }

    /*
     * 1189. Maximum Number of Balloons (https://leetcode.com/problems/maximum-number-of-balloons/description/)
     * Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.
     * You can use each character in text at most once. Return the maximum number of instances that can be formed.
    */
    public static int maxNumberOfBalloons(String text) {
        int result = Integer.MAX_VALUE;
        char[] arr = new char[] {'b','a','l','l','o','o','n'};
        
        Map<Character, Integer> freqBallon = new HashMap<>();
        for (char c : arr) freqBallon.put(c, freqBallon.getOrDefault(c, 0) + 1);

        Map<Character, Integer> freqText = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (freqBallon.containsKey(c)) freqText.put(c, freqText.getOrDefault(c, 0) + 1);
        }
        
        for (char c: freqBallon.keySet()) {
            int times = freqText.get(c);
            if (!freqText.containsKey(c)) return 0;
            result = Math.min(result, freqText.get(c) / times);
        }

        return result;
    }

    /*
     * 525. Contiguous Array (https://leetcode.com/problems/contiguous-array/description/)
     * Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.
    */
    public static int findMaxLength(int[] nums) {
        int sum = 0;
        int result = 0;
        
        Map<Integer, Integer> prefixFreq = new HashMap<>();
        prefixFreq.put(0, -1);
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i] == 0 ? -1 : 1;
            if (!prefixFreq.containsKey(sum)) prefixFreq.put(sum, i);
            result = Math.max(result, i - prefixFreq.get(sum));
        }
        
        return result;
    }

    /*
     * 1748. Sum of Unique Elements (https://leetcode.com/problems/sum-of-unique-elements/description/)
     * You are given an integer array nums. 
     * The unique elements of an array are the elements that appear exactly once in the array.
     * Return the sum of all the unique elements of nums.
    */
    public static int sumOfUnique(int[] nums) {
        int ans = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) {
            int times = freq.getOrDefault(n, 0) + 1;
            freq.put(n, times);
            if (times == 1) ans += n;
            if (times == 2) ans -= n;
        }
        return ans;
    }

    /*
     * 3005. Count Elements With Maximum Frequency (https://leetcode.com/problems/count-elements-with-maximum-frequency/description/)
     * You are given an array nums consisting of positive integers.
     * Return the total frequencies of elements in nums such that those elements all have the maximum frequency.
     * The frequency of an element is the number of occurrences of that element in the array.
    */
    public static int maxFrequencyElements(int[] nums) {
        int maxFreq = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) {
            int currFreq = freq.getOrDefault(n, 0) + 1;
            freq.put(n, currFreq);
            maxFreq = Math.max(maxFreq, currFreq);
        }

        int ans = 0;
        for (int key : freq.keySet()) {
            if (freq.get(key) == maxFreq) ans += maxFreq;
        }
        return ans;
    }

    /*
     * 1394. Find Lucky Integer in an Array (https://leetcode.com/problems/find-lucky-integer-in-an-array/description/)
     * Given an array of integers arr, a lucky integer is an integer that has a frequency in the array equal to its value.
     * Return the largest lucky integer in the array. If there is no lucky integer return -1.
    */
    public static int findLucky(int[] arr) {
        int ans = -1;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : arr) {
            int times = freq.getOrDefault(n, 0) + 1;
            freq.put(n, times);
        }
        
        for (int key : freq.keySet()) {
            if (freq.get(key) == key) ans = Math.max(ans, key);
        }
        return ans;
    }

    /*
     * 1207. Unique Number of Occurrences (https://leetcode.com/problems/unique-number-of-occurrences/description/)
     * Given an array of integers arr, 
     * return true if the number of occurrences of each value in the array is unique 
     * or false otherwise.
    */
    public static boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : arr) {
            int times = freq.getOrDefault(n, 0) + 1;
            freq.put(n, times);
        }

        Set<Integer> uniques = new HashSet<>();
        for (int v : freq.values()) uniques.add(v);

        return freq.values().size() == uniques.size();
    }

    /*
     * 451. Sort Characters By Frequency (https://leetcode.com/problems/sort-characters-by-frequency/description/)
     * Given a string s, sort it in decreasing order based on the frequency of the characters. 
     * The frequency of a character is the number of times it appears in the string.
     * Return the sorted string. If there are multiple answers, return any of them.
    */
    public String frequencySort(String s) {
        int[] freq = new int[128]; // support lowercase, uppercase, digits
        for (char c : s.toCharArray()) freq[c]++;
        
        Map<Integer, List<Character>> charFreqMap = new HashMap<>();
        int maxFreq = 0;
        for (int asciiValue = freq.length - 1; asciiValue >=0; asciiValue--) {
            int times = freq[asciiValue];
            if (times == 0) continue;
            
            if (!charFreqMap.containsKey(times)) charFreqMap.put(times, new ArrayList<>());
            charFreqMap.get(times).add((char)asciiValue);

            maxFreq = Math.max(maxFreq, times);
        }
        
        StringBuilder str = new StringBuilder();
        for (int times = maxFreq; times > 0; times--) {
            if (charFreqMap.containsKey(times)) {
                for (char c : charFreqMap.get(times)) str.append((Character.toString(c)).repeat(times));
            }
        }

        return str.toString();
    }

    /*
     * 2958. Length of Longest Subarray With at Most K Frequency (https://leetcode.com/problems/length-of-longest-subarray-with-at-most-k-frequency/description/)
     * You are given an integer array nums and an integer k.
     * The frequency of an element x is the number of times it occurs in an array.
     * An array is called good if the frequency of each element in this array is less than or equal to k.
     * Return the length of the longest good subarray of nums.
     * A subarray is a contiguous non-empty sequence of elements within an array.
    */
    public static int maxSubarrayLength(int[] nums, int k) {
        // freq table
        Map<Integer, Integer> freq = new HashMap<>();
        
        int result = 0;
        // sliding window (using K in condition)
        int leftIdx = 0;
        for (int j = 0; j < nums.length; j++) {
            int n = nums[j];
            freq.put(n, freq.getOrDefault(n, 0) + 1);

            // condition
            while (freq.get(n) > k) {
                // update result
                result = Math.max(result, j - leftIdx);
                // slide window (by increasing left pointer)
                freq.put(nums[leftIdx], freq.get(nums[leftIdx]) - 1);
                leftIdx++;
            }
        }

        return  Math.max(result, nums.length - leftIdx);
    }

    /*
     * 1512. Number of Good Pairs (https://leetcode.com/problems/number-of-good-pairs/description/)
     * Given an array of integers nums, return the number of good pairs.
     * A pair (i, j) is called good if nums[i] == nums[j] and i < j.
    */
    public static int numIdenticalPairs(int[] nums) {
        int ans = 0;
        
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) freq.put(n, freq.getOrDefault(n, 0) + 1); 
        
        for (int k : freq.keySet()) {
            int times = freq.get(k);
            if (times > 1) ans += getPairsCount(times);
        }
        
        return ans;
    }

    private static int getPairsCount(int freq) {
        if (freq == 2) return 1;
        int x = freq - 1;
        return (x * (x + 1)) / 2; // summation
    }

    /*
     * 930. Binary Subarrays With Sum (https://leetcode.com/problems/binary-subarrays-with-sum/description/)
     * Given a binary array nums and an integer goal, 
     * return the number of non-empty subarrays with a sum goal.
     * A subarray is a contiguous part of the array.
    */

}
