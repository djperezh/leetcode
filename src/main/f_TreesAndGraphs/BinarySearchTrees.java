package main.f_TreesAndGraphs;

public class BinarySearchTrees {
    /*
     * 938. Range Sum of BST (https://leetcode.com/problems/range-sum-of-bst/description/)
     * Given the root node of a binary search tree and two integers low and high, 
     * return the sum of values of all nodes with a value in the inclusive range [low, high].
    */
    public static int rangeSumBST(BinaryTreeNode root, int low, int high) {
        if (root == null) return 0;

        int ans = 0;
        if (low <= root.val && root.val <= high) ans += root.val;

        // Traverse R or L depending of the node value and the range values
        if (low < root.val) ans += rangeSumBST(root.left, low, high);
        if (root.val < high) ans += rangeSumBST(root.right, low, high);
        
        return ans;
    }

    /*
     * 530. Minimum Absolute Difference in BST (https://leetcode.com/problems/minimum-absolute-difference-in-bst/)
     * Given the root of a Binary Search Tree (BST), 
     * return the minimum absolute difference between the values of any two different nodes in the tree.
    */
    static int prev = Integer.MAX_VALUE;
    static int result = Integer.MAX_VALUE;
    public static int getMinimumDifference(BinaryTreeNode root) {
        inOrder(root);
        return result;
    }

    // inOrder, for a BST, traverse the elements in a sorted way
    // so we can calculate the difference between the current and previous element
    private static void inOrder(BinaryTreeNode n) {
        if (n == null) return;
        inOrder(n.left);

        int curr = Math.abs(n.val - prev);
        result = Math.min(result, curr);
        prev = n.val;
        
        inOrder(n.right);
    }

    /*
     * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
     * A valid BST is defined as follows:
     *    The left subtree of a node contains only nodes with keys less than the node's key.
     *    The right subtree of a node contains only nodes with keys greater than the node's key.
     *    Both the left and right subtrees must also be binary search trees.
    */
    public static boolean isValidBST(BinaryTreeNode root) { 
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isValidBST(BinaryTreeNode n, int small, int large) {
        if (n == null) return true;
        if (small >= n.val || n.val >= large) return false;
        return isValidBST(n.left, small, n.val) && isValidBST(n.right, n.val, large);
    }

    /*
     * 701. Insert into a Binary Search Tree (https://leetcode.com/problems/insert-into-a-binary-search-tree/description/) 
     * You are given the root node of a binary search tree (BST) and a value to insert into the tree.
     * Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.
     * Notice that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.
    */
    public BinaryTreeNode insertIntoBST(BinaryTreeNode root, int val) {
        BinaryTreeNode newNode = new BinaryTreeNode(val);
        if (root == null) return newNode;

        if (val < root.val) {
            insertIntoBST(root, root.left, newNode);
        } else {
            insertIntoBST(root, root.right, newNode);
        }

        return root;
    }

    private static void insertIntoBST(BinaryTreeNode parent, BinaryTreeNode curr, BinaryTreeNode newNode) {
        if (curr == null) {
            if (newNode.val > parent.val) {
                parent.right = newNode;
            } else {
                parent.left = newNode;
            }
            return;
        }

        if (newNode.val < curr.val) {
            insertIntoBST(curr, curr.left, newNode);
        } else {
            insertIntoBST(curr, curr.right, newNode);
        }
    }

    /*
     * 270. Closest Binary Search Tree Value (https://leetcode.com/problems/closest-binary-search-tree-value/description/)
     * Given the root of a binary search tree and a target value, return the value in the BST that is closest to the target.
     * If there are multiple answers, print the smallest.
    */
    static int closestValue = Integer.MAX_VALUE;
    static double diff = Double.MAX_VALUE;
    public static int closestValue(BinaryTreeNode root, double target) { 
        if (root == null) return 0;

        closestValue = root.val;
        diff = Math.abs(root.val - target);

        if (target < root.val) {
            inOrder(root.left, target);
        } else {
            inOrder(root.right, target);
        }

        return closestValue;
    }
    
    private static void inOrder(BinaryTreeNode curr, double target) {
        if (curr == null) return;

        inOrder(curr.left, target);

        double diffCurr = Math.abs(curr.val - target);
        if (diffCurr == diff) {
            closestValue = Math.min(curr.val, closestValue);
        } else {
            if (diffCurr < diff) {
                closestValue = curr.val;
                diff = diffCurr;
            }
        }
        
        inOrder(curr.right, target);
    }
}
