package main.c_Hashing;

import java.util.*;

public class CheckingForExistance {
    /*
     * Example 1.
     * Two Sum (https://leetcode.com/problems/two-sum/description/)
     * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     * You can return the answer in any order.
     * NOTE: we are assuming that no dupes elements are allowed (otherwise, use a map where the value is a Set containin the list of indexes)
    */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> dic = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (dic.containsKey(complement) && dic.get(complement) != i) return new int[] {i, dic.get(complement)};
            dic.put(nums[i], i);
        }
        return new int[] { -1, -1};
    }

    /*
     * Example 2.
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
     * Example 3.
     * Given an integer array nums, 
     * find all the unique numbers x in nums that satisfy the following: 
     * x + 1 is not in nums, and x - 1 is not in nums.
    */
    public static List<Integer> findNumbers(int[] nums) {
        Set<Integer> s = new HashSet<>();
        
        for (int n : nums) s.add(n); // uniques

        List<Integer> ans = new ArrayList<>();
        for (int n : nums) {
            if (!s.contains(n + 1) && !s.contains(n - 1)) ans.add(n);
        }
        return ans;
    }

    /*
     * 1832. Check if the Sentence Is a Perfect Pangram (https://leetcode.com/problems/check-if-the-sentence-is-pangram/description/)
     * Given a string sentence containing only lowercase English letters, return true if sentence is a pangram, or false otherwise.
     * A pangram is a sentence where every letter of the English alphabet appears at least once.
     * i.e. (from https://www.yourdictionary.com/articles/examples-pangrams):
     * The five boxing wizards jump quickly
     * The quick brown fox jumps over a lazy dog
    */
    public static boolean checkIfPangram(String sentence) {
        Set<Character> s = new HashSet<>();
        for (int i = 'a'; i <= 'z'; i++) s.add((char)i); // English alphabet's chars
        for (char c : sentence.toCharArray()) s.remove(Character.toLowerCase(c));
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
        long summation = (nums.length * (nums.length + 1)) / 2;
        return (int)(summation - actual);
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

    /*
     * 217. Contains Duplicate (https://leetcode.com/problems/contains-duplicate/description/)
     * Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
    */
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int n : nums) s.add(n);
        return nums.length != s.size();
    }

    /*
     * 1436. Destination City (https://leetcode.com/problems/destination-city/description/)
     * You are given the array paths, where paths[i] = [cityAi, cityBi] means there exists a direct path going from cityAi to cityBi.
     * Return the destination city, that is, the city without any path outgoing to another city.
     * It is guaranteed that the graph of paths forms a line without any loop, therefore, there will be exactly one destination city.
    */
    public static String destCity(List<List<String>> paths) {
        Set<String> origins = new HashSet<>();
        for (List<String> pairCities: paths) origins.add(pairCities.get(0));
        for (List<String> pairCities: paths) {
            String destination = pairCities.get(1);
            if (!origins.contains(destination)) return destination;
        }
        return "";
    }

    /*
     * 1496. Path Crossing (https://leetcode.com/problems/path-crossing/description/)
     * Given a string path, where path[i] = 'N', 'S', 'E' or 'W', 
     * each representing moving one unit north, south, east, or west, respectively. 
     * You start at the origin (0, 0) on a 2D plane and walk on the path specified by path.
     * Return true if the path crosses itself at any point, that is, if at any time you are on a location you have previously visited. 
     * Return false otherwise.
    */
    public static boolean isPathCrossing(String path) {
        Map<Integer, HashSet<Integer>> coor = new HashMap<>();
        coor.put(0, new HashSet<>());
        coor.get(0).add(0);
        int[] curr = new int[] {0,0};
        for (char c : path.toCharArray()) {
            int[] candidate = curr;
            if (c == 'N') candidate[1] = curr[1] + 1;
            if (c == 'S') candidate[1] = curr[1] - 1;
            if (c == 'E') candidate[0] = curr[0] + 1;
            if (c == 'W') candidate[0] = curr[0] - 1;
            
            if (coor.containsKey(candidate[0]) && coor.get(candidate[0]).contains(candidate[1])) return true;
            
            if (!coor.containsKey(candidate[0])) coor.put(candidate[0], new HashSet<>());
            coor.get(candidate[0]).add(candidate[1]);
            curr = candidate;
        }
        return false;
    }
}
