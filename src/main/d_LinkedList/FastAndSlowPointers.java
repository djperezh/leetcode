package main.d_LinkedList;

public class FastAndSlowPointers {
    /*
     * Example 2.
     * 141. Linked List Cycle (https://leetcode.com/problems/linked-list-cycle/description/)
     * Given head, the head of a linked list, determine if the linked list has a cycle in it.
     * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.
     * Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
     * Return true if there is a cycle in the linked list. Otherwise, return false.
    */
    public static boolean hasCycle(LinkedListNode head) {
        LinkedListNode fast = head;
        LinkedListNode slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    /*
     * Example 3.
     * Given the head of a linked list and an integer k, return the kthkth node from the end.
     * For example, 
     * given 1 -> 2 -> 3 -> 4 -> 5 and k = 2, return the node with value 4, as it is the 2nd node from the end.
    */
    public static LinkedListNode findNode(LinkedListNode head, int k) {
        LinkedListNode node = head;
        LinkedListNode runner = head;

        for (int i = 0; i < k; i++) runner = runner.next;
        
        while (runner != null) {
            node = node.next;
            runner = runner.next;
        }

        return node;
    }

    /*
     * Example 1.
     * 876. Middle of the Linked List (https://leetcode.com/problems/middle-of-the-linked-list/description/)
     * Given the head of a singly linked list, return the middle node of the linked list.
     * If there are two middle nodes, return the second middle node.
    */
    public static LinkedListNode middleNode(LinkedListNode head) {
        LinkedListNode slow = head;
        LinkedListNode fast = head;

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
    public static LinkedListNode deleteDuplicates(LinkedListNode head) { 
        LinkedListNode fast = head;
        LinkedListNode slow = head;
        while (fast != null && fast.next != null) {
            while (fast != null && fast.val == slow.val) fast = fast.next;
            slow.next = fast;
            slow = slow.next;
        }
        return head;
    }

    /*
     * 2095. Delete the Middle Node of a Linked List (https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/description/?submissionId=1527877790)
     * You are given the head of a linked list. Delete the middle node, and return the head of the modified linked list. 
     * The middle node of a linked list of size n is the ⌊n / 2⌋th node from the start using 0-based indexing, 
     * where ⌊x⌋ denotes the largest integer less than or equal to x. 
     * For n = 1, 2, 3, 4, and 5, the middle nodes are 0, 1, 1, 2, and 2, respectively.
    */
    public static LinkedListNode deleteMiddle(LinkedListNode head) {
        if (head == null || head.next == null) return null;

        // save head reference
        LinkedListNode dummy = head;
        LinkedListNode prev = null;
        LinkedListNode middle = head;
        LinkedListNode runner = head;
        
        // user runner to find middle node (use prev to save node before middle point)
        while (runner != null && runner.next != null) {
            prev = middle;
            middle = middle.next;
            runner = runner.next.next;
        }

        // remove node (use prev)
        prev.next = middle.next;

        // return saved head reference
        return dummy;
    }

    /*
     * 19. Remove Nth Node From End of List (https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/?submissionId=1527898652)
     * Given the head of a linked list, remove the nth node from the end of the list and return its head.
    */
    public static LinkedListNode removeNthFromEnd(LinkedListNode head, int n) {
        // save head reference
        LinkedListNode h = head;

        // use curr, node and runner references
        LinkedListNode prev = null;
        LinkedListNode curr = head;
        LinkedListNode runner = head;

        // use runner references to advance pointers n positions
        for (int i = 0; i < n; i++) runner = runner.next;
        while (runner != null) {
            prev = curr;
            curr = curr.next;
            runner = runner.next;
        }

        // delete curr (using prev reference)
        if (prev == null) {
            h = curr.next;
        } else {
            prev.next = curr.next;
            curr = null;
        }

        // return saved head reference
        return h;
    }

    /*
     * 82. Remove Duplicates from Sorted List II (https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/description/)
     * Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list. Return the linked list sorted as well.
    */
    public static LinkedListNode deleteDuplicatesII(LinkedListNode head) {
        LinkedListNode h = head;
        LinkedListNode curr = head;
        LinkedListNode prev = null;

        while (curr != null) {
            LinkedListNode tmp = curr;
            boolean dupe = false;
            while (tmp.next != null && tmp.next.val == tmp.val) {
                tmp = tmp.next;
                dupe = true;
            }
            
            // if dupes are found, we keep prev in the same possition
            if (dupe) {
                if (prev != null) 
                    prev.next = tmp.next;
                else
                    h = tmp.next;
            } else {
                prev = tmp;
            }
            curr = tmp.next;
        }

        // return saved head reference
        return h;
    }

    /*
     * 1721. Swapping Nodes in a Linked List (https://leetcode.com/problems/swapping-nodes-in-a-linked-list/description/)
     * You are given the head of a linked list, and an integer k.
     * Return the head of the linked list after swapping the values of the kth node
     * from the beginning and the kth node from the end (the list is 1-indexed).
    */
    public static LinkedListNode swapNodes(LinkedListNode head, int k) {
        LinkedListNode n = head;
        int ini = 1;
        int end = 1;
        LinkedListNode beginning = head;
        LinkedListNode ending = head;
        while (n != null) {
            n = n.next;
            if (ini < k) beginning = beginning.next;
            ini++;
            if (end > k) ending = ending.next;
            end++;
        }
        int tmp = beginning.val;
        beginning.val = ending.val;
        ending.val = tmp;
        return head;
    }
}
