package test.Backtracking;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.Test;
import main.Backtracking.General;

public class GeneralTest {
    @Test
    public void getPathBacktrackingTest() {
        int[][] maze = new int[][] {
            new int[] {0,0,0,1,0,0},
            new int[] {0,1,0,0,1,0},
            new int[] {0,0,1,0,0,1},
            new int[] {1,0,0,0,0,0},
            new int[] {0,1,0,0,1,1},
            new int[] {0,0,1,0,0,0}
        };
        
        List<int[]> result = General.getShortestPathBacktracking(maze, new int[]{0,0}, new int[]{5,5});
        assertEquals(10, result.size());
    }
}
