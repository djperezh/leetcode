package test.d_LinkedList;

import org.junit.Test;

import main.d_LinkedList.LinkedListNode;
import main.d_LinkedList.ReversingLinkedList;

public class ReversingLinledListTest {

    @Test
    public void reverseBetweenTest() {

        LinkedListNode head = new LinkedListNode(1);
        head.addToEnd(head, new LinkedListNode(2));
        head.addToEnd(head, new LinkedListNode(3));
        head.addToEnd(head, new LinkedListNode(4));
        head.addToEnd(head, new LinkedListNode(5));

        LinkedListNode ans = ReversingLinkedList.reverseBetween(head,2, 3);

        while (ans != null) {
            System.out.println(ans.val);
            ans = ans.next;
        }
    }
}
