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

    /*
     * 649. Dota2 Senate (https://leetcode.com/problems/dota2-senate/description/)
     * In the world of Dota2, there are two parties: the Radiant and the Dire.
     * The Dota2 senate consists of senators coming from two parties. Now the Senate wants to decide on a change in the Dota2 game. The voting for this change is a round-based procedure. In each round, each senator can exercise one of the two rights:
     *     Ban one senator's right: A senator can make another senator lose all his rights in this and all the following rounds.
     *     Announce the victory: If this senator found the senators who still have rights to vote are all from the same party, he can announce the victory and decide on the change in the game.
     * Given a string senate representing each senator's party belonging. The character 'R' and 'D' represent the Radiant party and the Dire party. Then if there are n senators, the size of the given string will be n.
     * The round-based procedure starts from the first senator to the last senator in the given order. This procedure will last until the end of voting. All the senators who have lost their rights will be skipped during the procedure.
     * Suppose every senator is smart enough and will play the best strategy for his own party. Predict which party will finally announce the victory and change the Dota2 game. The output should be "Radiant" or "Dire".
     * 
     * Based on this solution: https://leetcode.com/problems/dota2-senate/solutions/3483399/simple-diagram-explanation/
     */
    public static String predictPartyVictory(String senate) {
        Queue<Integer> rad = new LinkedList<>();
        Queue<Integer> dir = new LinkedList<>();
        int n = senate.length();

        // Add all senators to respect queue with index
        for (int i = 0; i < n; i++){
            if (senate.charAt(i) == 'R')
                rad.add(i);
            else
                dir.add(i);
        }

        // Use increasing n to keep track of position
        while (!rad.isEmpty() && !dir.isEmpty()){
            // Only "winner" stays in their queue
            if (rad.peek() < dir.peek())
                rad.add(n++);
            else
                dir.add(n++);
                
            rad.poll();
            dir.poll();
        }

        return rad.isEmpty() ? ("Dire") : ("Radiant");
    }
}