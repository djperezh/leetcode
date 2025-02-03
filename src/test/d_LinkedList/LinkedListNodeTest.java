package test.d_LinkedList;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.d_LinkedList.*;

public class LinkedListNodeTest {

    @Test
    public void LinkedListTest() {
        LinkedListNode head = new LinkedListNode(-1);
        
        head.addToStart(head, new LinkedListNode(10));
        head.addToStart(head, new LinkedListNode(1));
        head.addToEnd(head, new LinkedListNode(100));

        assertEquals(head.next.val, 1);
        assertEquals(head.next.next.val, 10);
        assertEquals(head.next.next.next.val, 100);

        head.removeFromEnd(head);
        assertEquals(head.next.val, 1);
        assertEquals(head.next.next.val, 10);
        assertEquals(head.next.next.next, null);

        head.removeFromStart(head);
        assertEquals(head.next.val, 10);
        assertEquals(head.next.next, null);
    }


}
