package main.f_TreesAndGraphs;

import java.security.InvalidParameterException;
import java.util.*;

public class General {

    /* 
    Imagine we work for a company whose business is scheduling flights for customers. 
    Today, only journeys with a single flight from one airport to another (which we will call direct flights) are supported.
    I'd like you to write a function that extends this functionality by returning one path from two airports,
    even when there is no direct flight between them. With direct flights:  
        Seattle -> Portland 
        Seattle -> Houston 
        Seattle -> New York City 
        New York City -> Philadelphia 
        Houston -> New York City

    If a customer wants to go from Seattle -> Philadelphia, the valid paths are: 
        1. Seattle -> Houston -> New York City -> Philadelphia 
        2. Seattle -> New York City -> Philadelphia

    */
    public static List<String> getPathBFS(String origin, String destination, List<String[]> flights) {
        if ((origin == null || destination == null) || flights == null || origin.equals(destination))
            throw new InvalidParameterException("Wrong Input Parameters");
        
        // build the graph
        Map<String, Set<String>> cityConnections = new HashMap<>();
        for (String[] f : flights) {
            if (!cityConnections.containsKey(f[0])) cityConnections.put(f[0], new HashSet<>());
            cityConnections.get(f[0]).add(f[1]);
        }
        
        // use bfs to traverse it
        Queue<String> q = new LinkedList<>();
        q.add(origin);
        Set<String> seen = new HashSet<>();
        seen.add(origin);
        
        // track path (so we avoid reversing the optimal path)
        Map<String, List<String>> pathTracking = new HashMap<>();
        pathTracking.put(origin, new ArrayList<>());
        pathTracking.get(origin).add(origin);

        while (q.size() > 0) {
            int counter = q.size();
            int i = 0;
            while (i < counter) {
                String city = q.poll();
                List<String> currentPath = pathTracking.get(city);

                // return path
                if (city == destination) return currentPath;

                if (cityConnections.containsKey(city)) {
                    for (String c : cityConnections.get(city)) {
                        if (!seen.contains(c)) {
                            q.add(c);
                            seen.add(c);

                            // Save candidate path
                            List<String> newpath = new ArrayList<>(currentPath);
                            newpath.add(c);
                            pathTracking.put(c, newpath);
                        }
                    }
                }
                i++;
            }
        }
        // no connection found
        return new ArrayList<>();
    }


    /*
    We have a city (M x N grid) with open roads (' '), blocked off roads (X), [G]roceries' Stores, and customers.
    City planners want you to identify how far a location is from its closest [G]roceries' Stores.

    #     0    1    2    3    4    5    6    7    8
        ['X', ' ', ' ', 'G', ' ', ' ', 'X', ' ', 'X'], # 0
        ['X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', 'X'], # 1
        [' ', ' ', ' ', 'G', 'X', 'X', ' ', 'X', ' '], # 2
        [' ', ' ', ' ', 'G', ' ', 'X', ' ', ' ', ' '], # 3
        [' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X'], # 4
        [' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X', 'X']  # 5

    locations = [ K
        [200, 200],
        [1, 4], 
        [0, 3],
        [5, 0],
        [5, 5], 
        [1, 8], 
        [5, 8]
    ]

    answer = [-1, 2, 0, 5, 9, 6, -1]

    NOTE: loof for (M*N)+k complexity
    */
    public static List<int[]> findDistanceToClosest(String[][] grid, int[][] locations) {
        // initialize QUeue based on the Grocery Stores
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == "G") {
                    q.add(new int[]{i, j});
                    grid[i][j] = "0";
                }
            }
        }

        BFS(grid, q);

        // Contruct answer
        List<int[]> ans = new ArrayList<>();
        for (int i = 0; i < locations.length; i++) {
            int[] curr = locations[i];
            int row = curr[0];
            int coll = curr[1];
            // If valid location then get distance
            if (row >= 0 && row < grid.length && coll >= 0 && coll < grid[curr[0]].length && grid[row][coll] != "X") {
                ans.add(new int[] {row, coll, Integer.parseInt(grid[row][coll])});
            } else {
                // add "-1" as anser for that location
                ans.add(new int[] {row, coll, -1});
            }
        }
        return ans;
    }

    private static void BFS(String[][] grid, Queue<int[]> q) {
        int[][] directions = new int[][] {new int[]{0,1}, new int[]{0,-1}, new int[]{1,0}, new int[]{-1,0}};

        while (!q.isEmpty()) {
            int[] curr = q.remove();

            // add Neighbores
            for (int[] d : directions) {
                int row = curr[0] + d[0];
                int coll = curr[1] + d[1];
                if (row >= 0 && row < grid.length && coll >= 0 && coll < grid[curr[0]].length && grid[row][coll] == " ") {
                    q.add(new int[]{row, coll});
                    int distance = Integer.parseInt(grid[curr[0]][curr[1]]);
                    grid[row][coll] = String.valueOf(distance + 1);
                }
            }
        }
    }
}