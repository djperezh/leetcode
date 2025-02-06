package main.e_StackAndQueues;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class Monotonic {
    /*
     * Example I.
     * 739. Daily Temperatures (https://leetcode.com/problems/daily-temperatures/description/)
     * Given an array of integers temperatures represents the daily temperatures,
     * return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.
     * If there is no future day for which this is possible, keep answer[i] == 0 instead.
    */
    public static int[] dailyTemperatures(int[] temperatures) {
        // TODO
        return null;
    }

    /*
     * 239. Sliding Window Maximum (https://leetcode.com/problems/sliding-window-maximum/description/)
     * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
     * You can only see the k numbers in the window.
     * Each time the sliding window moves right by one position.
     * Return the max sliding window.
    */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[(nums.length - k) + 1];
        LinkedList<Integer> queue = new LinkedList<>();
        // We will use indexes instead of values
        for (int i = 0; i < nums.length; i++) {
            // keep decreasing-monotonic property
            while (!queue.isEmpty() && nums[i] > nums[queue.getLast()]) queue.removeLast();
            queue.addLast(i);
            // If index (queue.getFirst()) is outside the window then Remove it from the queue
            if (queue.getFirst() + k == i) queue.removeFirst();
            // if window reached "k", update answer
            if (i >= k - 1) ans[(i - k) + 1] = nums[queue.getFirst()];
        }
        return ans;
    }

    /*
     * 1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit (https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/description/)
     * Given an array of integers nums and an integer limit,
     * return the size of the longest non-empty subarray such that the absolute difference between any two elements of this subarray is less than or equal to limit.
    */
    public static int longestSubarray(int[] nums, int limit) {
        // TODO
        return -1;
    }

    /*
     * 496. Next Greater Element I (https://leetcode.com/problems/next-greater-element-i/description/)
     * The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
     * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
     * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.
     * Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
    */
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> m = new HashMap<>();
        Stack<Integer> myStack = new Stack<>();

        for (int i = 0; i < nums2.length; i++) {
            int n = nums2[i];
            while (myStack.size() > 0 && myStack.peek() < n)  m.put(myStack.pop(), n);
            myStack.push(n);
        }

        while (myStack.size() > 0) m.put(myStack.pop(), -1);

        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) result[i] = m.get(nums1[i]);

        return result;
    }
}
