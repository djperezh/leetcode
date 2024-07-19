package main.LinkedList;

import java.util.List;

public class ReversingLinkedList {
    /*
     * 206. Reverse Linked List (https://leetcode.com/problems/reverse-linked-list/description/)
     * Given the head of a singly linked list, reverse the list, and return the reversed list.
    */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }
        return prev;
    }

    /*
     * 24. Swap Nodes in Pairs (https://leetcode.com/problems/swap-nodes-in-pairs/description/)
     * Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
    */
    public static ListNode swapPairs(ListNode head) {
        // TODO
        return null;
    }

    /*
     * 2130. Maximum Twin Sum of a Linked List (https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/description/)
     * In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.
     * For example:
     * if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2. These are the only nodes with twins for n = 4. 
     * The twin sum is defined as the sum of a node and its twin.
     * Given the head of a linked list with even length, return the maximum twin sum of the linked list.
    */
    public int pairSum(ListNode head) {
        // TODO
        return -1;
    }

    /*
     * 92. Reverse Linked List II (https://leetcode.com/problems/reverse-linked-list-ii/description/)
     * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
    */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // TODO
        return head;
    }
}
