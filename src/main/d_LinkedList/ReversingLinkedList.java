package main.d_LinkedList;

public class ReversingLinkedList {
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

    /*
     * 24. Swap Nodes in Pairs (https://leetcode.com/problems/swap-nodes-in-pairs/description/)
     * Given a linked list, swap every two adjacent nodes and return its head.
     * For example:
     * given a linked list 1 -> 2 -> 3 -> 4 -> 5 -> 6
     * return a linked list 2 -> 1 -> 4 -> 3 -> 6 -> 5.
     * You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
    */
    public static LinkedListNode swapPairs(LinkedListNode head) {
        if (head == null || head.next == null) return head;

        // save the 2nd element since it's the one we want to return
        LinkedListNode dummy = head.next;
        LinkedListNode prev = null;

        // while we have pair to process
        while (head != null && head.next != null) {
            // Update the saved "prev" pointer (2nd element of the previous pair) to point to the 2nd element of the current pair
            if (prev != null) prev.next = head.next;

            // save pointer to the future second element of the pair
            prev = head;

            // move the pointers accordingly
            LinkedListNode n = head.next.next;
            head.next.next = head;
            head.next = n;
            head = n;
        }
        return dummy;
    }

    /*
     * 2130. Maximum Twin Sum of a Linked List (https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/description/)
     * In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.
     * For example:
     * if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2. These are the only nodes with twins for n = 4. 
     * The twin sum is defined as the sum of a node and its twin.
     * Given the head of a linked list with even length, return the maximum twin sum of the linked list.
    */
    public int pairSum(LinkedListNode head) {
        LinkedListNode node = head;
        LinkedListNode runner = head;
        LinkedListNode prev = null;

        // use runner to identify middle point
        while (runner != null) {
            prev = node;
            node = node.next;
            runner = runner.next.next;
        }

        // reverse second half
        prev.next = reverseList(node);

        // use runner pointer to calc sum (curr + runner)
        node = head;
        runner = prev.next;
        
        int ans = head.val + runner.val; // initialize ans

        while (runner != null) {
            // update ans with Max sum
            ans = Math.max(ans, runner.val + node.val);
            node = node.next;
            runner = runner.next;
        }

        return ans;
    }

    /*
     * 92. Reverse Linked List II (https://leetcode.com/problems/reverse-linked-list-ii/description/)
     * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
    */
    public static LinkedListNode reverseBetween(LinkedListNode head, int left, int right) {
        if (head == null || head.next == null || left == right) return head;

        // this is needed to handle edge-case scenarios
        LinkedListNode dummy = new LinkedListNode(0);
        dummy.next = head;

        LinkedListNode prev = null;
        LinkedListNode curr = head;

        // Move the l pointer to "before" the left position
        LinkedListNode l = dummy;
        for (int i = 1; i < left; i++) {
            l = curr;
            curr = curr.next;
        }

        // Rever list algorithm
        for (int times = left; times < right; times++) {
            LinkedListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }

        // update the pointers (l.next: right before the swap; l.next.next: right after the swap)
        l.next.next = curr.next;
        curr.next = prev;
        l.next = curr;

        return dummy.next;
    }

    /*
     * 234. Palindrome Linked List ()
     * Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
    */
    public boolean LinkedListNode(LinkedListNode head) {
        if (head.next == null) return true;
        if (head.next.next == null) return head.val == head.next.val;

        // identify mid point
        LinkedListNode mid = head;
        LinkedListNode runner = mid;
        while (runner.next != null && runner.next.next != null) {
            mid = mid.next;
            runner = runner.next.next;
        }

        // use reverseList() to get the "reversedHead" (passing in the mid point)
        LinkedListNode rev = reverseList(mid);

        // head and reversehead and compare values (false if different)
        LinkedListNode n = head;
        while (n != null) {
            if (n.val != rev.val) return false;
            n = n.next;
            rev = rev.next;
        }

        return true;
    }

    /*
     * 2074. Reverse Nodes in Even Length Groups (https://leetcode.com/problems/reverse-nodes-in-even-length-groups/description/)
     * You are given the head of a linked list.
     * The nodes in the linked list are sequentially assigned to non-empty groups whose lengths form the sequence of the natural numbers (1, 2, 3, 4, ...). The length of a group is the number of nodes assigned to it. In other words,
     *     The 1st node is assigned to the first group.
     *     The 2nd and the 3rd nodes are assigned to the second group.
     *     The 4th, 5th, and 6th nodes are assigned to the third group, and so on.
     * Note that the length of the last group may be less than or equal to 1 + the length of the second to last group.
     * Reverse the nodes in each group with an even length, and return the head of the modified linked list.
    */
    public static LinkedListNode reverseEvenLengthGroups(LinkedListNode head) {
        int group = 1;
        int counter = 1;
        LinkedListNode curr = head;

        int total = 0;
        while (curr != null) {
            total++;
            curr = curr.next;
        }

        curr = head;
        while (curr != null && counter < total) {
            int x = total - (counter - 1);
            if ((x >= group && group % 2 == 0) || (x < group && x % 2 == 0)  ) {
                curr = reverseBetween(head, counter, (counter + Math.min(group, x)) - 1);
                counter += group;
            } else {
                counter += group;
                for (int i = 0; i < counter && curr != null; i++) curr = curr.next;
            }
            group++;
        }
        return head;
    }

}
