package main.f_TreesAndGraphs;

import java.util.*;

public class Graphs {
    /*
     * 1971. Find if Path Exists in Graph (https://leetcode.com/problems/find-if-path-exists-in-graph/description/)
     * There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive).
     * The edges in the graph are represented as a 2D integer array edges,
     * where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi.
     * Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
     * You want to determine if there is a valid path that exists from vertex source to vertex destination. 
     * Given edges and the integers n, source, and destination,
     * return "true" if there is a valid path from source to destination, or "false" otherwise.
     * [
     * [0,1],
     * [1,2],
     * [2,0]
     * ]
    */
    public static boolean validPath(int n, int[][] edges, int source, int destination) {
        if (edges == null || edges.length == 0) return n == 1;
        
        // build graph
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int s = edges[i][0];
            int d = edges[i][1];
            if (!graph.containsKey(s)) graph.put(s, new HashSet<>());
            graph.get(s).add(d);
            // since it's bi-directional, we need to add the other direction too
            if (!graph.containsKey(d)) graph.put(d, new HashSet<>());
            graph.get(d).add(s);
        }

        Set<Integer> seen = new HashSet<>();
        Queue<Integer> paths = new LinkedList<>();
        paths.offer(source);
        seen.add(source);

        while (!paths.isEmpty()) {
            Set<Integer> candidates = graph.get(paths.poll());
            
            if (candidates.contains(destination)) return true;
            
            for (int d : candidates) {
                if (!seen.contains(d)) paths.offer(d);
                seen.add(d);
            }
        }

        return false;
    }

    /*
     * 695. Max Area of Island (https://leetcode.com/problems/max-area-of-island/description/)
     * You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) 
     * You may assume all four edges of the grid are surrounded by water.
     * The area of an island is the number of cells with a value 1 in the island.
     * Return the maximum area of an island in grid. If there is no island, return 0.
    */
    public static int maxAreaOfIsland(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    int area = 0;
                    
                    // BFS updating visited
                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[] {i, j});
                    grid[i][j] = 0; // mark as visited
                    while (q.size() > 0) {
                        int[] currRowCol = q.remove();
                        area++;
                        
                        addNeighbors(currRowCol[0], currRowCol[1], grid, q);
                    }
                    
                    ans = Math.max(ans, area);
                }
            }
        }
        
        return ans;
    }
    
    private static void addNeighbors(int i, int j, int[][] grid, Queue<int[]> q) {
        int[][] directions = new int[][] { new int[] {0,1}, new int[] {1, 0}, new int[] {0,-1}, new int[] {-1,0}};
        for (int[] d : directions) {
            int newRow = i + d[0];
            int newCol = j + d[1];
            if ((newRow >= 0 && newRow < grid.length) &&
                (newCol >= 0 && newCol < grid[newRow].length) &&
                grid[newRow][newCol] == 1) {
                q.add(new int[] {newRow, newCol});
                grid[newRow][newCol] = 0; // mark as visited
            } 
        }
    }
}
