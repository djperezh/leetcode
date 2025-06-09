package main.i_BinarySearch;

import java.util.Arrays;

public class OnArrays {
    /*
     * Example 1
     * 704. Binary Search (https://leetcode.com/problems/binary-search/description/)
     * Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. 
     * If target exists, then return its index. Otherwise, return -1.
    */
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int n = nums[mid];
            if (n == target) return mid;
            
            if (n > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /*
     * Example 2.
     * 74. Search a 2D Matrix (https://leetcode.com/problems/search-a-2d-matrix/description/)
     * You are given an m x n integer matrix matrix with the following two properties:
     * Each row is sorted in non-decreasing order.
     * The first integer of each row is greater than the last integer of the previous row.
     * Given an integer target, return true if target is in matrix or false otherwise.
    */
    public static boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int l = 0;
        int r = rows * cols;

        while (l < r) {
            int mid = l + (r - l) / 2;
            int row = mid / cols;
            int col = mid % cols;
            int n = matrix[row][col];

            if (n == target) return true;

            if (n > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return false;     
    }

    /*
     * Exercise 3.
     * 2300. Successful Pairs of Spells and Potions (https://leetcode.com/problems/successful-pairs-of-spells-and-potions/description/)
     * You are given two positive integer arrays spells and potions, of length n and m respectively, 
     * where spells[i] represents the strength of the ith spell and potions[j] represents the strength of the jth potion.
     * You are also given an integer success. A spell and potion pair is considered successful if the product of their strengths is at least success.
     * Return an integer array pairs of length n where pairs[i] is the number of potions that will form a successful pair with the ith spell.
    */
    public static int[] successfulPairs(int[] spells, int[] potions, long success) {
        int[] ans = new int[spells.length];
        Arrays.sort(potions);
        for (int i = 0; i < spells.length; i++) {
            int index = binarySearchSuccessfulPairs (potions, success / (double) spells[i]);
            ans[i] = potions.length - index;
        }
        return ans;
    }

    private static int binarySearchSuccessfulPairs (int[] potions, double target) {
        int l = 0;
        int r = potions.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (potions[mid] >= target) { // most left target
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    /*
     * 35. Search Insert Position (https://leetcode.com/problems/search-insert-position/description/)
     * Given a sorted array of distinct integers and a target value, return the index if the target is found. 
     * If not, return the index where it would be if it were inserted in order.
     * You must write an algorithm with O(log n) runtime complexity.
    */
    public static int searchInsert(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (target == nums[mid]) return mid;
            
            if (target < nums[mid])
                r = mid - 1; 
            else
                l = mid + 1;
        }
        return l;
    }

    /*
     * 2389. Longest Subsequence With Limited Sum (https://leetcode.com/problems/longest-subsequence-with-limited-sum/description/)
     * You are given an integer array nums of length n, and an integer array queries of length m.
     * Return an array answer of length m where answer[i] is the maximum size of a subsequence that you can take from nums such that the sum of its elements is less than or equal to queries[i].
     * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
    */
    public static int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int[] result = new int[queries.length];
        // Build prefix array
        int[] prefix = new int[nums.length];
        prefix[0] = nums[0];
        for (int i = 1; i < nums.length; i++) prefix[i] = prefix[i-1] + nums[i];        
        for (int i = 0; i < queries.length; i++) result[i] = binarySearch(prefix, queries[i]);
        return result;
    }
    
    private static int binarySearch(int[] arr, int target) {
        int l = 0;
        int r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
