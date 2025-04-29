package main.f_TreesAndGraphs;

import java.util.*;

public class GraphsDFS {
    /*
     * Example 1
     * 547. Number of Provinces (https://leetcode.com/problems/number-of-provinces/description/)
     * There are n cities. Some of them are connected, while some are not. 
     * If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
     * A province is a group of directly or indirectly connected cities and no other cities outside of the group. 
     * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise. 
     * Return the total number of provinces.
    */
    public static int findCircleNum(int[][] isConnected) {
        int ans = 0;

        // build graph (Map)
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < isConnected.length; i++) {
            if (!graph.containsKey(i)) graph.put(i, new HashSet<>());
            for (int j = i + 1; j < isConnected[i].length; j++) {
                if (!graph.containsKey(j)) graph.put(j, new HashSet<>());

                // Since it's a undirected graph, we add both directions
                if (isConnected[i][j] == 1) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }

        // DFS for each entry (mark as seen)
        boolean[] seen = new boolean[isConnected.length];
        for (int k : graph.keySet()) {
            if (!seen[k]) {
                ans++;
                dfsMarkConnectedCities(k, graph, seen);
            }
        }
        return ans;
    }

    private static void dfsMarkConnectedCities(int k, Map<Integer, Set<Integer>> graph, boolean[] seen) {
        Stack<Integer> s = new Stack<>();
        s.push(k);
        seen[k] = true;
        while (!s.isEmpty()) {
            for (int i : graph.get(s.pop())) {
                if (!seen[i]) s.push(i);
                seen[i] = true;
            }
        }
    }

    /*
     * Example 2
     * 200. Number of Islands (https://leetcode.com/problems/number-of-islands/description/)
     * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
     * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
     * You may assume all four edges of the grid are all surrounded by water.
    */
    public static int numIslands(char[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    bfsNumIslands(grid, i, j);
                    ans++;
                } 
            }
        }
        return ans;
    }

    private static void bfsNumIslands(char[][] grid, int i, int j){
        int[][] directions = new int[][] {
            new int[] {0, 1}, // R
            new int[] {1, 0}, // B
            new int[] {0, -1},// L
            new int[] {-1, 0},// T
        };

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        grid[i][j] = '0';

        while (q.size() > 0) {
            int[] curr = q.poll();
            for (int[] d : directions) {
                int nextRow = curr[0] + d[0];
                int nextCol = curr[1] + d[1];
                if (nextRow >= 0 && nextRow < grid.length && nextCol >=0 && nextCol < grid[i].length && grid[nextRow][nextCol] == '1') {
                    q.add(new int[]{nextRow, nextCol});
                    grid[nextRow][nextCol] = '0';
                }
            }
        }
    }

    /*
     * Example 3
     * 1466. Reorder Routes to Make All Paths Lead to the City Zero (https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/)
     * There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two different cities.
     * Roads are represented by connections where connections[i] = [x, y] represents a road from city x to city y.
     * The edges are directed. You need to swap the direction of some edges so that every city can reach city 0.
     * Return the minimum number of swaps needed.
    */
    public static int minReorder(int n, int[][] connections) {
        // build undirected graph based on connections[][]
        Set<String> roadsOrigDest = new HashSet<>();
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) graph.put(i, new HashSet<>());
        for (int[] connection: connections) {
            int x = connection[0];
            int y = connection[1];
            graph.get(x).add(y);
            graph.get(y).add(x);
            roadsOrigDest.add(String.valueOf(x) + "-" + String.valueOf(y));
        }
        return dfsMinReorder(graph, roadsOrigDest);
    }

    // DFS from city 0. if origen-destination are in connections then include it in the answer (direction is moving away from city 0)
    private static int dfsMinReorder(Map<Integer, Set<Integer>> graph, Set<String> roadsOrigDest) {
        int ans = 0;
        Set<Integer> seen = new HashSet<>();
        Stack<Integer> s = new Stack<>();
        s.push(0);
        seen.add(0);
        while (!s.isEmpty()) {
            int origin = s.pop();
            for (int dest : graph.get(origin)) {
                if (!seen.contains(dest)) {
                    s.push(dest);
                    seen.add(dest);
                    if (roadsOrigDest.contains(String.valueOf(origin) + String.valueOf(dest))) ans++;
                }
            }
        }
        return ans;
    }

    /*
     * Example 4
     * 841. Keys and Rooms (https://leetcode.com/problems/keys-and-rooms/)
     * There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0. 
     * Your goal is to visit all the rooms. When you visit a room, you may find a set of distinct keys in it. 
     * Each key has a number on it, denoting which room it unlocks, and you can take all of them with you to unlock the other rooms. 
     * Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i, 
     * return true if you can visit all the rooms, or false otherwise.
    */
    static Set<Integer> seen = new HashSet<>();

    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        // bfs Iterative
        bfsIterative(rooms);

        // // bfs Recursive
        // seen.add(0);
        // bfsRecursive(0, rooms);
        
        return (seen.size() == rooms.size());
    }

    private static void bfsRecursive(int node, List<List<Integer>> rooms) {
        for (int neighbor : rooms.get(node)) {
            if (!seen.contains(neighbor)) {
                seen.add(neighbor);
                bfsRecursive(neighbor, rooms);
            }
        }
    }

    private static void bfsIterative(List<List<Integer>> rooms) {
        Stack<Integer> s = new Stack<>();
        s.add(0);
        seen.add(0);
        while(!s.isEmpty()) {
            for (int k : rooms.get(s.pop())) {
                if (!seen.contains(k)) {
                    s.add(k);
                    seen.add(k);
                }
            }
        }
    }

    /*
     * Example 5
     * 1557. Minimum Number of Vertices to Reach All Nodes (https://leetcode.com/problems/minimum-number-of-vertices-to-reach-all-nodes/)
     * Given a directed acyclic graph, with n vertices numbered from 0 to n-1, and an array edges where edges[i] = [x, y] represents a directed edge from node x to node y. 
     * Find the smallest set of vertices from which all nodes in the graph are reachable.
     * This simple (hard-to-understand) solution was taken from https://leetcode.com/problems/minimum-number-of-vertices-to-reach-all-nodes/solutions/6349662/easy-to-do/
    */
    public static List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        List<Integer> res = new ArrayList<>();
        
        int indegree[] = new int[n];
        for (List<Integer> edge : edges) indegree[edge.get(1)]++;
        
        for(int i = 0; i < indegree.length; i++){
            if (indegree[i] == 0) res.add(i);
        }
        
        return res;
    }

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
     * 323. Number of Connected Components in an Undirected Graph (https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/description/)
     * You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.
     * Return the number of connected components in the graph.
    */
    public int countComponents(int n, int[][] edges) {
        if (edges == null) return 0;
        
        int result = 0;
        HashSet<Integer> seenComponent = new HashSet<>();
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
        
        // biuld graph
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                // we want all the sources in the list
                seenComponent.add(edges[i][j]);

                // the first element (j) is the key for the graph
                if (j == 0 && !graph.containsKey(edges[i][j])) {
                    graph.put(edges[i][j], new HashSet<Integer>());
                }
                else {
                    // If it's not the first element (j>0), then is a values for the graph key
                    graph.get(edges[i][0]).add(edges[i][j]);
                    
                    // since is bidirectional, we create a entry for the other direction
                    if (!graph.containsKey(edges[i][j])) graph.put(edges[i][j], new HashSet<Integer>());
                    graph.get(edges[i][j]).add(edges[i][0]);
                }
            }
        }
        
        // dfs
        Queue<Integer> paths = new LinkedList<>();
        paths.add(edges[0][0]);
        seen.remove(edges[0][0]);        
        while (paths.size() > 0) {
            HashSet<Integer> destinations = graph.get(paths.remove());
            for (int d: destinations) {
                if (seen.contains(d)) {
                    paths.add(d);
                    seen.remove(d);
                }
            }
            if (paths.size() == 0) {
                result++;
                if (seen.size() > 0) paths.add(seen.iterator().next());
            }
        }
        
        // In case we have orphan nodes
        return result + n - graph.keySet().size();
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

    /*
     * 2368. Reachable Nodes With Restrictions (https://leetcode.com/problems/reachable-nodes-with-restrictions/description/)
     * There is an undirected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.
     * You are given a 2D integer array edges of length n - 1 where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
     * You are also given an integer array restricted which represents restricted nodes.
     * Return the maximum number of nodes you can reach from node 0 without visiting a restricted node.
     * Note that node 0 will not be a restricted node.
    */
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        int result = 0;
        HashSet<Integer> seen = new HashSet<>();
        
        HashSet<Integer> restrictedSet = new HashSet<>();
        for (int r: restricted) restrictedSet.add(r);
        
        // build graph
        Map<Integer, HashSet<Integer>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                if (j == 0 && !graph.containsKey(edges[i][j])) {
                    graph.put(edges[i][j], new HashSet<Integer>());
                }
                else {
                    graph.get(edges[i][0]).add(edges[i][j]);
                    if (!graph.containsKey(edges[i][j])) graph.put(edges[i][j], new HashSet<Integer>());
                    graph.get(edges[i][j]).add(edges[i][0]);
                }
            }
        }
        
        // DFS
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        seen.add(0);
        
        while (q.size() > 0) {
            HashSet<Integer> neighbors = graph.get(q.remove());
            for (int curr: neighbors) {
                // if current node is NOT restricted then its neighbors
                if (!restrictedSet.contains(curr) && !seen.contains(curr)) {
                    q.add(curr);
                    result++;
                }
                seen.add(curr);
            }
        }
        
        return result + 1;
    }
}
