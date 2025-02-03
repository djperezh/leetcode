package main.d_LinkedList;

public class General {

    /*
     * 203. Remove Linked List Elements (https://leetcode.com/problems/remove-linked-list-elements/description/)
     * Given the head of a linked list and an integer val,
     * remove all the nodes of the linked list that has Node.val == val, and return the new head.
    */
    public static LinkedListNode removeElements(LinkedListNode head, int val) {
        while (head != null && head.val == val) head = head.next;
        if (head == null) return head;

        LinkedListNode prev = head;
        LinkedListNode curr = head.next;
        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }

        return head;
    }

    public static int getDecimalValue(LinkedListNode head) {
        int result = 0;
        LinkedListNode prev = reverseList(head);
        int i = 1;
        while (prev != null) {
            result += prev.val == 1 ? i * prev.val : 0;
            i *= 2; // base 2
            prev = prev.next;
        }
        return result;
    }

    /*
     * 328. Odd Even Linked List (https://leetcode.com/problems/odd-even-linked-list/description/)
     * Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.
     * The first node is considered odd, and the second node is even, and so on.
     * Note that the relative order inside both the even and odd groups should remain as it was in the input.
     * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
    */
    public static LinkedListNode oddEvenList(LinkedListNode head) {
        // TODO
        return null;
    }

    /*
     * 206. Reverse Linked List (https://leetcode.com/problems/reverse-linked-list/description/)
     * Given the head of a singly linked list, reverse the list, and return the reversed list.
    */
    public static LinkedListNode reverseList(LinkedListNode head) {
        if (head == null || head.next == null) return head;


        LinkedListNode prev = null;
        LinkedListNode curr = head;

        while (curr != null) {
            LinkedListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }
        return prev;
    }
}
