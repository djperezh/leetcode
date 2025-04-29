package main.e_StackAndQueues;

import java.util.ArrayDeque;
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
        Stack<Integer> indexes = new Stack<>();
        int[] ans = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while (indexes.size() > 0 && temperatures[i] > temperatures[indexes.peek()]) ans[indexes.peek()] = i - indexes.pop();
            indexes.push(i);
        }
        return ans;
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
        LinkedList<Integer> increasing = new LinkedList<>();
        LinkedList<Integer> decreasing = new LinkedList<>();
        int left = 0;
        int ans = 0;
        
        for (int right = 0; right < nums.length; right++) {
            // maintain the monotonic deques
            while (!increasing.isEmpty() && increasing.getLast() > nums[right]) {
                increasing.removeLast();
            }
            while (!decreasing.isEmpty() && decreasing.getLast() < nums[right]) {
                decreasing.removeLast();
            }
            
            increasing.addLast(nums[right]);
            decreasing.addLast(nums[right]);
            
            // maintain window property
            while (decreasing.getFirst() - increasing.getFirst() > limit) {
                if (nums[left] == decreasing.getFirst()) {
                    decreasing.removeFirst();
                }
                if (nums[left] == increasing.getFirst()) {
                    increasing.removeFirst();
                }
                left++;
            }
            
            ans = Math.max(ans, right - left + 1);
        }
        
        return ans;
    }
}
