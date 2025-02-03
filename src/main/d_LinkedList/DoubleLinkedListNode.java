package main.d_LinkedList;

public class DoubleLinkedListNode {
    public int val;
    public DoubleLinkedListNode next;
    public DoubleLinkedListNode prev;
    
    public DoubleLinkedListNode (int val) {
        this.val = val;
    }

    public void addNode(DoubleLinkedListNode head, DoubleLinkedListNode nodeToAdd) {
        DoubleLinkedListNode n = head;
        while (n.next != null) n = n.next;
        nodeToAdd.prev = n;
        nodeToAdd.next = n.next;
        n.next = nodeToAdd;
    }
    
    public void deleteNode(DoubleLinkedListNode head) {
        if (head.next == null) return;

        DoubleLinkedListNode n = head;
        while (n.next != null) n = n.next;
        n.prev.next = null;
    }

}