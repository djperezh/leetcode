package main.f_TreesAndGraphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import javafx.util.Pair;

public class ImplicitGraphs {
    /*
     * Example 1
     * 752. Open the Lock (https://leetcode.com/problems/open-the-lock/)
     * You have a lock with 4 circular wheels. Each wheel has the digits 0 to 9. 
     * The wheels rotate and wrap around - so 0 can turn to 9 and 9 can turn to 0. 
     * Initially, the lock reads "0000". One move consists of turning a wheel one slot. 
     * You are given an array of blocked codes deadends - if the lock reads any of these codes, 
     * then it can no longer turn. Return the minimum number of moves to make the lock read target.
    */
    public int openLock(String[] deadends, String target) {        
        Set<String> seen = new HashSet<>();
        
        for (String s: deadends) {
            if (s.equals("0000")) return -1;
            seen.add(s);
        }
        
        // BFS
        Queue<LockPair> queue = new LinkedList<>();
        queue.offer(new LockPair("0000", 0));
        seen.add("0000");

        while (!queue.isEmpty()) {
            LockPair pair = queue.poll();
            String node = pair.node;
            int steps = pair.steps;

            if (node.equals(target)) return steps;
            addLockPairNeighbors(node, queue, seen, steps);
        }
        return -1;
    }

    private static void addLockPairNeighbors(String node, Queue<LockPair> queue, Set<String> seen, int steps) {
        for (int i = 0; i < 4; i++) {
            int num = (node.charAt(i) - '0');
            for (int change: new int[]{-1, 1}) {
                int x = (num + change + 10) % 10;
                String newCombination = node.substring(0, i) + ("" + x) + node.substring(i + 1);
                if (!seen.contains(newCombination)) {
                    seen.add(newCombination);
                    queue.add(new LockPair(newCombination, steps + 1));
                }
            }
        }
    }

    private static class LockPair {
        String node;
        int steps;

        LockPair(String node, int steps) {
            this.node = node;
            this.steps = steps;
        }
    }

    /*
     * Example 2
     * 399. Evaluate Division (https://leetcode.com/problems/evaluate-division/)
     * You are given an array equations and a number array values of the same length. 
     * equations[i] = [x, y] represents x / y = values[i]. 
     * You are also given an array queries where queries[i] = [a, b] which represents the quotient a / b. 
     * Return an array answer where answer[i] is the answer to the ithith query, or -1 if it cannot be determined.
     * For example, let's say we have equations = [["a", "b"], ["b", "c"]] and values = [2, 3]. 
     * This input represents ab=2ba​=2 and bc=3cb​=3. If we had a query ["a", "c"],
     * the answer to that query would be 6, because we can deduce that ac=6ca​=6.
    */
    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        
        for (int i = 0; i < equations.size(); i++) {
            String numerator = equations.get(i).get(0), denominator = equations.get(i).get(1);
            double val = values[i];
            
            if (!graph.containsKey(numerator)) {
                graph.put(numerator, new HashMap<>());
            }
            if (!graph.containsKey(denominator)) {
                graph.put(denominator, new HashMap<>());
            }
                
            graph.get(numerator).put(denominator, val);
            graph.get(denominator).put(numerator, 1 / val);
        }
        
        double[] ans = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            ans[i] = answerQuery(graph, queries.get(i).get(0), queries.get(i).get(1));
        }
        
        return ans;
    }

    private static double answerQuery(Map<String, Map<String, Double>> graph, String start, String end) {
        // no info on this node
        if (!graph.containsKey(start)) {
            return -1;
        }
        
        Set<String> seen = new HashSet<>();
        Stack<EquationPair> stack = new Stack<>();
        seen.add(start);
        stack.push(new EquationPair(start, 1));
        while (!stack.empty()) {
            EquationPair pair = stack.pop();
            String node = pair.node;
            double ratio = pair.ratio;
            if (node.equals(end)) {
                return ratio;
            }
            
            if (graph.containsKey(node)) {
                for (String neighbor: graph.get(node).keySet()) {
                    if (!seen.contains(neighbor)) {
                        seen.add(neighbor);
                        stack.push(new EquationPair(neighbor, ratio * graph.get(node).get(neighbor)));
                    }
                }
            }
        }
        
        return -1;
    }

    private static class EquationPair {
        String node;
        double ratio;

        EquationPair(String node, double ratio) {
            this.node = node;
            this.ratio = ratio;
        }
    }

    /*
     * 433. Minimum Genetic Mutation (https://leetcode.com/problems/minimum-genetic-mutation/description/)
     * A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.
     * Suppose we need to investigate a mutation from a gene string startGene to a gene string endGene where one mutation is defined as one single character changed in the gene string.
     *     For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
     * There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to make it a valid gene string.
     * Given the two gene strings startGene and endGene and the gene bank bank, return the minimum number of mutations needed to mutate from startGene to endGene. If there is no such a mutation, return -1.
     * Note that the starting point is assumed to be valid, so it might not be included in the bank.
    */
    public static int minMutation(String startGene, String endGene, String[] bank) {
        int result = 0;
        if (startGene.equals(endGene)) return result;

        //  initialize visited/seen genes. Put bank in a set
        Set<String> seen = new HashSet<>();
        Set<String> bankSet = new HashSet<String>(Arrays.asList(bank));

        // use BFS (queue) to chech each state (step)
        Queue<String> q = new LinkedList<>(); //offer & poll
        q.offer(startGene);
        seen.add(startGene);

        while (!q.isEmpty()) {
            int genesPerStep = q.size();
            for (int i = 0; i < genesPerStep; i++) {
                String g = q.poll();
                if (g.equals(endGene)) return result;
                for (char c: new char[] {'A', 'C', 'G', 'T'}) {
                    for (int j = 0; j < g.length(); j++) {
                        String neighbor = g.substring(0, j) + c + g.substring(j + 1);
                        if (!seen.contains(neighbor) && bankSet.contains(neighbor)) {
                            q.add(neighbor);
                            seen.add(neighbor);
                        }
                    }
                }
            }
            result++;
        }
        return -1;
    }

    /*
     * 1306. Jump Game III (https://leetcode.com/problems/jump-game-iii/description/)
     * Given an array of non-negative integers arr, you are initially positioned at start index of the array. 
     * When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach any index with value 0.
     * Notice that you can not jump outside of the array at any time.
    */
    public static boolean canReach(int[] arr, int start) {
        Set<Integer> seen = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        seen.add(start);

        while (!q.isEmpty()) {
            int index = q.poll();
            int n = arr[index];

            if (n == 0) return true;

            int candidateIndex = index + n;
            if (candidateIndex < arr.length && !seen.contains(candidateIndex)) q.offer(candidateIndex);
            seen.add(candidateIndex);

            candidateIndex = index - n;
            if (candidateIndex >= 0 && !seen.contains(candidateIndex)) q.offer(candidateIndex);
            seen.add(candidateIndex);
        }
        return false;
    }

    /*
     * 2101. Detonate the Maximum Bombs (https://leetcode.com/problems/detonate-the-maximum-bombs/description/)
     * You are given a list of bombs. The range of a bomb is defined as the area where its effect can be felt. This area is in the shape of a circle with the center as the location of the bomb.
     * The bombs are represented by a 0-indexed 2D integer array bombs where bombs[i] = [xi, yi, ri]. xi and yi denote the X-coordinate and Y-coordinate of the location of the ith bomb, whereas ri denotes the radius of its range.
     * You may choose to detonate a single bomb. When a bomb is detonated, it will detonate all bombs that lie in its range. These bombs will further detonate the bombs that lie in their ranges.
     * Given the list of bombs, return the maximum number of bombs that can be detonated if you are allowed to detonate only one bomb.
    */
    public static int maximumDetonation(int[][] bombs) {
        int result = 1;

        // build graph
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int bomb = 0; bomb < bombs.length; bomb++) {
          for (int otherBomb = 0; otherBomb < bombs.length; otherBomb++) {
            if (bomb != otherBomb) {
              // calculate eucladian distance
              double dist = Math.sqrt(
                Math.pow(bombs[bomb][0] - bombs[otherBomb][0], 2) + 
                Math.pow(bombs[bomb][1] - bombs[otherBomb][1], 2));
              double radious = bombs[bomb][2];
              if (radious >= dist) {
                if (!graph.containsKey(bomb)) graph.put(bomb, new HashSet<Integer>());
                graph.get(bomb).add(otherBomb);
              }
            }
          }
        }

        // for each bomb (in the graph) get the number of reachable bombs 
        for (int k : graph.keySet()) result = Math.max(getReachableBombs(k, graph), result);

        return result;
    }
    
      private static int getReachableBombs(int origin, Map<Integer, Set<Integer>> graph) {
        Set<Integer> seen = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer(origin);
        seen.add(origin);
        while (!q.isEmpty()) {
          int curr = q.poll();
          Set<Integer> neighbors = graph.get(curr);
          if (neighbors != null && !neighbors.isEmpty()) {
            for (int n : neighbors) {
              if (!seen.contains(n)) {
                q.offer(n);
                seen.add(n);
              }
            }
          }
        }
        return seen.size();
      }
}
