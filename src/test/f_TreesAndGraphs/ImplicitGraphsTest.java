package test.f_TreesAndGraphs;
// ... existing code ...

import org.junit.Test;

import main.f_TreesAndGraphs.ImplicitGraphs;

import static org.junit.Assert.*;

public class ImplicitGraphsTest {
    // ... existing tests ...

    @Test
    public void testMinMutation() {
        // Setup test data
        String startGene = "AACCGGTT";
        String endGene = "AAACGGTA";
        String[] bank = new String[]{"AACCGGTA","AACCGCTA","AAACGGTA"};

        // Call the method
        int result = ImplicitGraphs.minMutation(startGene, endGene, bank);

        // Assert the expected outcome
        assertEquals(2, result);
    }

    
    // ... rest of the class ...
}