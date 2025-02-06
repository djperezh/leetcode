package main.e_StackAndQueues;

import java.util.*;

public class Queues {
    /*
     * 496. Next Greater Element I (https://leetcode.com/problems/next-greater-element-i/description/)
     * The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
     * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
     * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2.
     * If there is no next greater element, then the answer for this query is -1. 
     * Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
    */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> s = new Stack<>();
        Map<Integer, Integer> elementWithNextGreater = new HashMap<>();

        for (int n : nums2) {
            // Save map (Key: element, Value: Next Greater)  
            while (!s.isEmpty() && s.peek() < n) elementWithNextGreater.put(s.pop(), n);
            s.push(n);
        }

        // for those elements that didn't have "Next Greater" element, then update map with "-1"
        while (!s.isEmpty()) elementWithNextGreater.put(s.pop(), -1);

        // Find the element in the map and get the "Next Greater" to save it in the answer
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) ans[i] = elementWithNextGreater.get(nums1[i]);
        return ans;
    }

}