package test.c_Hashing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.c_Hashing.General;

public class GeneralTest {

    @Test
    public void getCandidateFieldsCountTest() {
        int[][] matrix = new int[][] {
            new int[] {1, 1, 1, 0, 0, 0},
            new int[] {1, 1, 1, 0, 0, 0},
            new int[] {0, 0, 0, 0, 1, 0},
            new int[] {1, 1, 1, 0, 0, 0},
            new int[] {1, 0, 2, 0, 0, 1},
            new int[] {0, 0, 0, 0, 1, 2}
        };

        int ans = General.getCandidateFieldsCount(matrix);

        assertEquals(1, ans);
    }

    @Test
    public void getCandidateFieldsCountTest_irregularFieldShape() {
        int[][] matrix = new int[][] {
            new int[] {1, 1, 2, 0, 1 },
            new int[] {1, 1, 1},
            new int[] {0, 0, 0, 0, 2},
            new int[] {1, 1, 1, 0, 0, 0},
            new int[] {1, 0, 2, 0, 1},
            new int[] {0, 0, 0, 0, 1, 1}
        };
        int ans = General.getCandidateFieldsCount(matrix);

        assertEquals(2, ans);
    }
}
