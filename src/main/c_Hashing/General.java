package main.c_Hashing;

import java.util.*;

public class General {

    /*
     * 205. Isomorphic Strings (https://leetcode.com/problems/isomorphic-strings/description/)
     * Given two strings s and t, determine if they are isomorphic.
     * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
     * All occurrences of a character must be replaced with another character while preserving the order of characters.
     * No two characters may map to the same character, but a character may map to itself.
    */
    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Character> m = new HashMap<>();
        Set<Character> visited = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char equivalent = t.charAt(i);
            if (!m.containsKey(c)) {
                if (visited.contains(equivalent)) return false;
                m.put(c, equivalent);
                visited.add(equivalent);
            } else {
                if (!m.get(c).equals(equivalent)) return false;
            }
        }

        return true;
    }

    /*
     * 290. Word Pattern
     * Given a pattern and a string s, find if s follows the same pattern.
     * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s. Specifically:
     * Each letter in pattern maps to exactly one unique word in s.
     * Each unique word in s maps to exactly one letter in pattern.
     * No two letters map to the same word, and no two words map to the same letter.
    */
    public static boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (words.length != pattern.length()) return false;

        Map<Character, String> m = new HashMap<>();
        Set<String> visited = new HashSet<>();
        
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String w = words[i];

            if (m.containsKey(c)) {         
                if (!m.get(c).equals(w)) return false;
            } else {
                if (visited.contains(w)) return false;
                m.put(c, w);
                visited.add(w);
            }
        }
        return true;
    }

    /*
     * 791. Custom Sort String (https://leetcode.com/problems/custom-sort-string/description/)
     * You are given two strings order and s. All the characters of order are unique and were sorted in some custom order previously.
     * Permute the characters of s so that they match the order that order was sorted.
     * More specifically, if a character x occurs before a character y in order, then x should occur before y in the permuted string.
     * Return any permutation of s that satisfies this property.
    */
    public static String customSortString(String order, String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) freq.put(c, freq.getOrDefault(c, 0) + 1);

        StringBuilder sb = new StringBuilder();
        for (char c : order.toCharArray()) {
            if (freq.containsKey(c)) {
                while (freq.get(c) > 0) {
                    freq.put(c, freq.get(c) - 1);
                    sb.append(c);
                }
            }
        }
        
        for (char c : s.toCharArray()) {
            if (freq.get(c) != 0) sb.append(c);
        }

        return sb.toString();
    }

    /*
     * 1657. Determine if Two Strings Are Close (https://leetcode.com/problems/determine-if-two-strings-are-close/description/)
     * Two strings are considered close if you can attain one from the other using the following operations:
     * Operation 1: Swap any two existing characters. For example, abcde -> aecdb
     * Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
     * For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
     * You can use the operations on either string as many times as necessary.
     * Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.
    */
    public static boolean closeStrings(String word1, String word2) {
        Map<Character, Integer> freq1 = new HashMap<>();
        for (Character c : word1.toCharArray()) freq1.put(c, freq1.getOrDefault(c , 0) + 1);
        Map<Character, Integer> freq2 = new HashMap<>();
        for (Character c : word2.toCharArray()) freq2.put(c, freq2.getOrDefault(c , 0) + 1);

        if (freq1.size() != freq2.size()) return false;

        Map<Integer, Integer> freqCounter2 = new HashMap<>();
        for (Character c : freq2.keySet()) {
            if (!freq1.containsKey(c)) return false;

            int counter = freq2.get(c);
            freqCounter2.put(counter, freqCounter2.getOrDefault(counter, 0) + 1);
        }

        for (Character c : freq1.keySet()) {
            int counter = freq1.get(c);
            if (!freqCounter2.containsKey(counter)) return false;

            freqCounter2.put(counter, freqCounter2.getOrDefault(counter, 0) - 1);
            if (freqCounter2.get(counter) == 0) freqCounter2.remove(counter);
        }

        return freqCounter2.size() == 0;
    }

    /*
    * Given a Farmer operation image represented as a 2D binary array, where 
    * "1" = respresents "planting zone"
    * "0" = represents "dead zone" 
    * Write the getFieldsCount() function to find the number of independent fields
    * A field is an aread delimited by "No-Field" area (group of 1's surrounded by 0's to Left, Rigt, Top, Bottom)
    * Example:
    * input:
    *  1, 1, 1, 0, 0, 0
    *  1, 1, 1, 0, 0, 0
    *  0, 0, 0, 0, 1, 0
    *  1, 1, 1, 0, 0, 0
    *  1, 0, 1, 0, 0, 1
    *  0, 0, 0, 0, 1, 1
    * 
    * Outout: 4
    * Explanation:
    * There are 4 independent groups of field areas ("1") delimited with no-field areas ("0")
    */
    static Queue<int[]> q;
    static int[][] input;

    public static int getCandidateFieldsCount(int[][] matrix) {
        int ans = 0;
        int cadidatesCount = 0;

        q = new LinkedList<>();
        input = matrix;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                int cellValue =  input[i][j];
                if (cellValue == 1) {
                    ans++;
                    Field f = BFS(i, j);
                    if (f.FieldSize >= 4 && f.CornSize >= 1) cadidatesCount++;
                }
            }
        }
        return cadidatesCount;
    }

    private static Field BFS(int i, int j) {
        q.add(new int[] {i, j});
        int fieldSize = 1;
        int cornSize = input[i][j] == 2 ? 1 : 0;
        Field f = new Field(fieldSize, cornSize);

        input[i][j] = 0; // mark as visited

        while (q.size() > 0) {
            int[] cell = q.poll();
            traverseNeighbores(cell, f);
        }

        return f;
    }

    private static void traverseNeighbores(int[] cell, Field f) {

        int[][] directions = new int[][] { new int[] {0, 1}, new int[] {0, -1}, new int[] {1, 0}, new int[] {-1, 0} };
        for (int[] direction : directions) {
            int[] newCell = new int[] {cell[0] + direction[0], cell[1] + direction[1]};
            int row = newCell[0];
            int col = newCell[1];
            if (row >= 0 && row < input.length && col >= 0 &&
                col < input[row].length && input[row][col] != 0)  {
                f.CornSize += input[row][col] == 2 ? 1 : 0;
                f.FieldSize++;

                q.add(newCell);
                input[row][col] = 0; // mark as visited
            };
        }
    }
}

class Field {
    int FieldSize;
    int CornSize;

    public Field(int size, int cornSize) {
        FieldSize = size;
        CornSize = cornSize;
    }
}
