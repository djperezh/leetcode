package test.TreesAndGraphs;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import main.TreesAndGraphs.General;

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

}
