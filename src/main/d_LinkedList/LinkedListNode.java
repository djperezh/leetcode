package main.d_LinkedList;


/*
 * 707. Design Linked List (https://leetcode.com/problems/design-linked-list/)
*/
public class LinkedListNode {
    public int val;
    public LinkedListNode next;
    public LinkedListNode prev;
    
    public LinkedListNode (int val) {
        this.val = val;
    }

    public void addToEnd(LinkedListNode head, LinkedListNode nodeToAdd) {
        LinkedListNode n = head;
        while (n.next != null) n = n.next;
        n.next = nodeToAdd;
    }

    public void removeFromEnd(LinkedListNode head) {
        LinkedListNode prev = head;
        LinkedListNode n = head.next;
        while (n.next != null) {
            prev = n;
            n = n.next;
        }
        prev.next = null;
    }

    public void addToStart(LinkedListNode head, LinkedListNode nodeToAdd) {
        if (head.next != null) nodeToAdd.next = head.next;
        head.next = nodeToAdd;
    }

    public void removeFromStart(LinkedListNode head) {
        head.next = head.next.next;
    }

}