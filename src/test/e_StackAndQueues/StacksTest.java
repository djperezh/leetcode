package test.e_StackAndQueues;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.e_StackAndQueues.Stacks;

public class StacksTest {
    @Test
    public void robotWithStringTest() {

        String ans = Stacks.robotWithString("bdda");

        assertEquals("addb", ans);
    }
}
