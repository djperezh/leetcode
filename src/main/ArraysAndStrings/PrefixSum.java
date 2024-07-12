package main.ArraysAndStrings;

public class PrefixSum {
    /*
     * Given an integer array nums, an array queries where queries[i] = [x, y] and an integer limit,
     * return a boolean array that represents the answer to each query.
     * A query is true if the sum of the subarray from x to y is less than limit, or false otherwise.
     * For example, given
     * nums = [1, 6, 3, 2, 7, 2]
     * queries = [[0, 3], [2, 5], [2, 4]]
     * limit = 13,
     * Output: [true, false, true] -> For each query, the subarray sums are [12, 14, 12]
    */
    public boolean[] answerQueries(int[] nums, int[][] queries, int limit) {
        boolean[] ans = new boolean[queries.length];
        for (int i = 1; i < nums.length; i++) nums[i] =+ nums[i - 1];
        for (int i = 0; i < queries.length; i++) ans[i] = nums[queries[i][1]] - nums[queries[i][0]] < limit;
        return ans;
    }

    /*
     * 2270. Number of Ways to Split Array
     * Given an integer array nums,
     * find the number of ways to split the array into two parts so that the first section has a sum greater than or equal to the sum of the second section.
     * The second section should have at least one number.
     * Input: nums = [10,4,-8,7]
     * Output: 2
     * Explanation: There are three ways of splitting nums into two non-empty parts:
     * - Split nums at index 0. Then, the first part is [10], and its sum is 10. The second part is [4,-8,7], and its sum is 3. Since 10 >= 3, i = 0 is a valid split.
     * - Split nums at index 1. Then, the first part is [10,4], and its sum is 14. The second part is [-8,7], and its sum is -1. Since 14 >= -1, i = 1 is a valid split.
     * - Split nums at index 2. Then, the first part is [10,4,-8], and its sum is 6. The second part is [7], and its sum is 7. Since 6 < 7, i = 2 is not a valid split.
     * Thus, the number of valid splits in nums is 2
    */
    public static int waysToSplitArray(int[] nums) throws Exception {
        if (nums == null || nums.length < 2) throw new Exception("Validation error");
        int ans = 0;
        int n = nums.length;
        long[] prefix = new long[n]; // since prefix will be increasing, we need a "long" data type
        prefix[0] = nums[0];
        for (int i = 1; i < n; i++) prefix[i] = nums[i] + prefix[i - 1];
        for (int i = 0; i < n - 1; i++) ans += (prefix[i] >= prefix[n - 1] - prefix[i]) ? 1 : 0;
        return ans;
    }

    /*
     * 1480. Running Sum of 1d Array
     * Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).
     * Return the running sum of nums.
    */
    public int[] runningSum(int[] nums) {
        for (int i = 1; i < nums.length; i++) nums[i] += nums[i-1];
        return nums;
    }

    /*
     * 1413. Minimum Value to Get Positive Step by Step Sum
     * Given an array of integers nums, you start with an initial positive value startValue.
     * In each iteration, you calculate the step by step sum of startValue plus elements in nums (from left to right).
     * Return the minimum positive value of startValue such that the step by step sum is never less than 1.
        Input: nums = [-3,2,-3,4,2]
        Output: 5
        Explanation: If you choose startValue = 4, in the third iteration your step by step sum is less than 1.
        step by step sum
        startValue = 4 | startValue = 5 | nums
        (4 -3 ) = 1  | (5 -3 ) = 2    |  -3
        (1 +2 ) = 3  | (2 +2 ) = 4    |   2
        (3 -3 ) = 0  | (4 -3 ) = 1    |  -3
        (0 +4 ) = 4  | (1 +4 ) = 5    |   4
        (4 +2 ) = 6  | (5 +2 ) = 7    |   2
    */
    public static int minStartValue(int[] nums) {
        // TODO

        return -1;
    }

    /*
     * 2090. K Radius Subarray Averages
     * You are given a 0-indexed array nums of n integers, and an integer k.
     * The k-radius average for a subarray of nums centered at some index i with the radius k is the average of all elements in nums between the indices i - k and i + k (inclusive).
     * If there are less than k elements before or after the index i, then the k-radius average is -1.
     * Build and return an array avgs of length n where avgs[i] is the k-radius average for the subarray centered at index i.
     * The average of x elements is the sum of the x elements divided by x, using integer division.
     * The integer division truncates toward zero, which means losing its fractional part.
     * For example
     * input: [2, 3, 1, 5]
     * Output: (2 + 3 + 1 + 5) / 4 = 2.75, which truncates to 2.
    */
    public static int[] getAverages(int[] nums, int k) {
        // TODO

        return nums;
    }

    /*
     * 1732. Find the Highest Altitude
     * There is a biker going on a road trip. The road trip consists of n + 1 points at different altitudes. The biker starts his trip on point 0 with altitude equal 0.
     * You are given an integer array gain of length n where gain[i] is the net gain in altitude between points i​​​​​​ and i + 1 for all (0 <= i < n).
     * Return the highest altitude of a point.
     * Input: gain = [-5,1,5,0,-7]
     * Output: 1
     * Explanation: The altitudes are [0,-5,-4,1,1,-6]. The highest is 1.
    */
    public static int largestAltitude(int[] gain) {
        // TODO
        return -1;
    }

    /*
     * 724. Find Pivot Index
     * Given an array of integers nums, calculate the pivot index of this array.
     * The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right.
     * If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left.
     * This also applies to the right edge of the array. Return the leftmost pivot index. If no such index exists, return -1.
    */
    public static int pivotIndex(int[] nums) {
        // TODO
        return -1;
    }

    public static int getNumArraySumRange(int[] nums) {
        NumArray obj = new NumArray(nums);
        return obj.sumRange(0, nums.length - 1);
    }

}

/*
    * 303. Range Sum Query - Immutable
    * Given an integer array nums, handle multiple queries of the following type:
    * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
    * Implement the NumArray class:
    * - NumArray(int[] nums) Initializes the object with the integer array nums.
    * - int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
    * Your NumArray object will be instantiated and called as such:
    * NumArray obj = new NumArray(nums);
    * int param_1 = obj.sumRange(left,right);
*/
class NumArray {

    public NumArray(int[] nums) {
        // TODO
    }
    
    public int sumRange(int left, int right) {
        // TODO
        return -1;
    }
}