package main.d_LinkedList;

public class FastAndSlowPointers {
    /*
     * Given the head of a linked list with an odd number of nodes head, return the value of the node in the middle.
     * For example, given a linked list that represents 1 -> 2 -> 3 -> 4 -> 5, return 3
    */
    public static int getMiddle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.val;
    }

    /*
     * 141. Linked List Cycle (https://leetcode.com/problems/linked-list-cycle/description/)
     * Given head, the head of a linked list, determine if the linked list has a cycle in it.
     * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.
     * Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
     * Return true if there is a cycle in the linked list. Otherwise, return false.
    */
    public static boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    /*
     * Given the head of a linked list and an integer k, return the kthkth node from the end.
     * For example, 
     * given 1 -> 2 -> 3 -> 4 -> 5 and k = 2, return the node with value 4, as it is the 2nd node from the end.
    */
    public static ListNode findNode(ListNode head, int k) {
        ListNode node = head;
        ListNode runner = head;

        for (int i = 0; i < k; i++) runner = runner.next;
        
        while (runner != null) {
            node = node.next;
            runner = runner.next;
        }

        return node;
    }

    /*
     * 876. Middle of the Linked List (https://leetcode.com/problems/middle-of-the-linked-list/description/)
     * Given the head of a singly linked list, return the middle node of the linked list.
     * If there are two middle nodes, return the second middle node.
    */
    public static ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    /*
     * 83. Remove Duplicates from Sorted List (https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/)
     * Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.
    */
    public static ListNode deleteDuplicates(ListNode head) { 
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            while (fast != null && fast.val == slow.val) fast = fast.next;
            slow.next = fast;
            slow = slow.next;
        }
        return head;
    }
}
