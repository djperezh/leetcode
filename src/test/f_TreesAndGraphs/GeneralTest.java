package test.f_TreesAndGraphs;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import main.f_TreesAndGraphs.General;

public class GeneralTest {

    @Test
    public void getPathBFSTest() {
        List<String[]> fligths = new ArrayList<>();
        fligths.add(new String[] {"A", "B"});
        fligths.add(new String[] {"A", "C"});
        fligths.add(new String[] {"D", "C"});
        fligths.add(new String[] {"D", "E"});
        fligths.add(new String[] {"F", "H"});
        fligths.add(new String[] {"C", "E"});
        fligths.add(new String[] {"G", "F"});
        fligths.add(new String[] {"E", "G"});

        List<String> result = General.getPathBFS("A", "W", fligths);

        for (String s : result) System.out.println(s);
    }

    @Test
    public void findDistanceToClosestTest() {
        String[][] grid = new String[][] {
            new String[] { "X", " ", " ", "G", " ", " ", "X", " ", "X"},
            new String[] { "X", " ", "X", "X", " ", " ", " ", " ", " "},
            new String[] { " ", " ", " ", "G", "X", "X", " ", "X", " "},
            new String[] { " ", " ", " ", "G", " ", "X", " ", " ", " "},
            new String[] { " ", " ", " ", " ", " ", "X", " ", " ", "X"},
            new String[] { " ", " ", " ", " ", "X", " ", " ", "X", "X"}
        };

        int[][] locations = new int[][]{
            new int[] {200, 200},
            new int[] {1, 4},
            new int[] {0, 3},
            new int[] {5, 0},
            new int[] {5, 5},
            new int[] {1, 8},
            new int[] {5, 8}
        };

        List<int[]> ans = General.findDistanceToClosest(grid, locations);

        int[] expected = new int[] {-1, 2, 0, 5, 9, 6, -1};
        int index = 0;
        for (int[] r : ans) assertEquals(r[2], expected[index++]);
    }
}
