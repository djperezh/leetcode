package main.Backtracking;

import java.util.*;

public class General {

    /*
     * Given a Maze (2D array), return the list of cells for the
     * shortest path from the start to the exit.
     * Start and Exit are given as a integer array (as X, Y coordinates)
     * If cell has value "1", means that it's a wall or cell is blocked.
     * Input:
       maze = [ {0,0,0,1,0,0},
                {0,1,0,0,1,0},
                {0,0,1,0,0,1},
                {1,0,0,0,0,0},
                {0,1,0,0,1,1},
                {0,0,1,0,0,0} ]
        startCell = [0,0]
        exitCell = [5,5]
        output: [ [0,1],[0,2],[1,2],[1,3],[2,3],[3,3],[4,3],[5,3],[5,4],[5,5] ]
    */
    static List<int[]> resultShortestPathBacktracking = new ArrayList<>();
    
    public static List<int[]> getShortestPathBacktracking(int[][] maze, int[] startCell, int[] exitCell) {
        maze[startCell[0]][startCell[1]] = 1;
        getPathBacktracking(maze, startCell, exitCell, new ArrayList<>());
        return resultShortestPathBacktracking;
    }

    private static void getPathBacktracking(int[][] maze, int[] currCell, int[] exitCell, List<int[]> ans) {     
        if (currCell[0] == exitCell[0] && currCell[1] == exitCell[1]) {   
            resultShortestPathBacktracking = new ArrayList<>(ans); 
            return;
        }

        for (int[] neighbor : getNeighborCells(maze, currCell)){
            ans.add(neighbor);
            getPathBacktracking(maze, neighbor, exitCell, ans);
            ans.remove(ans.size() - 1);
        }
    }

    private static List<int[]> getNeighborCells(int[][] maze, int[] currCell) {
        int[][] directions = new int[][] { new int[] {0, 1}, new int[] {0, -1}, new int[] {1, 0}, new int[] {-1, 0} };

        List<int[]> result = new ArrayList<>();
        for (int[] dir : directions) {
            int r = currCell[0] + dir[0];
            int c = currCell[1] + dir[1];
            if (r >= 0 && r < maze.length && c >= 0 && c < maze[r].length && maze[r][c] != 1) {
                result.add(new int[] {r, c});
                maze[r][c] = 1; // mark as visited
            }
        }
        return result;
    }
}
