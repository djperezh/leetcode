package test.d_LinkedList;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.d_LinkedList.*;

public class DoubleLinkedListNodeTest {

    @Test
    public void DoubleLinkedListTest() {
        DoubleLinkedListNode head = new DoubleLinkedListNode(-1);
        
        head.addNode(head, new DoubleLinkedListNode(1));
        head.addNode(head, new DoubleLinkedListNode(10));
        head.addNode(head, new DoubleLinkedListNode(100));

        assertEquals(head.next.val, 1);
        assertEquals(head.next.next.val, 10);
        assertEquals(head.next.next.next.val, 100);

        head.deleteNode(head);
        assertEquals(head.next.val, 1);
        assertEquals(head.next.next.val, 10);
        assertEquals(head.next.next.next, null);

        head.deleteNode(head);
        assertEquals(head.next.val, 1);
        assertEquals(head.next.next, null);

        head.deleteNode(head);
        assertEquals(head.next, null);

        head.deleteNode(head);
        assertEquals(head.next, null);
    }



}
