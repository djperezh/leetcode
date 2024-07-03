package main.TreesAndGraphs;

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

}
