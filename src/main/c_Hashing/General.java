package main.c_Hashing;

import java.util.*;


/*
 * Given a Farmer operation image represented as a 2D binary array, where 
 * "1" = Field area
 * "0" = No-Field area
 * Write the getFieldsCount() function to found the number of independent fields
 * A field is an aread delimited by "No-Field" area (Left, Rigt, Top, Bottom)
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

public class General {
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
                    int fieldSize = BFS(i, j);
                    if (fieldSize >= 4) cadidatesCount++;
                }
            }
        }
        return ans;
    }

    private static int BFS(int i, int j) {
        q.add(new int[] {i, j});
        input[i][j] = 0; // mark as visited
        int fieldSize = 1;

        while (q.size() > 0) {
            int[] cell = q.poll();
            fieldSize = traverseNeighbores(cell, fieldSize);
        }

        return fieldSize;
    }

    private static int traverseNeighbores(int[] cell, int fieldSize) {
        int[][] directions = new int[][] { new int[] {0, 1}, new int[] {0, -1}, new int[] {1, 0}, new int[] {-1, 0} };
        for (int[] direction : directions) {
            int[] newCell = new int[] {cell[0] + direction[0], cell[1] + direction[1]};
            int row = newCell[0];
            int col = newCell[1];
            if (row >= 0 && row < input.length && col >= 0 &&
                col < input[row].length &&
                input[row][col] == 1)  {
                q.add(newCell);
                input[row][col] = 0; // mark as visited
                fieldSize++;
            };
        }
        return fieldSize;
    }
}
