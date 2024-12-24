package main.c_Hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoreHashingExamples {
    /*
     * Example 1.
     * 49. Group Anagrams (https://leetcode.com/problems/group-anagrams/description/)
     * Given an array of strings strs, group the anagrams together.
     * You can return the answer in any order.
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
}
