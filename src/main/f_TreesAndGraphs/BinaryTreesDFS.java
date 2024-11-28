package main.f_TreesAndGraphs;

public class BinaryTreesDFS {
/* 104. Maximum Depth of Binary Tree (https://leetcode.com/problems/maximum-depth-of-binary-tree/description/)
     * Given the root of a binary tree, return its maximum depth.
     * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
    */
    public static int maxDepth(BinaryTreeNode root) { 
        return (root == null) ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /*
     * 112. Path Sum (https://leetcode.com/problems/path-sum/description/)
     * Given the root of a binary tree and an integer targetSum,
     * return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
     * A leaf is a node with no children.
    */
    public static boolean hasPathSum(BinaryTreeNode root, int targetSum) {
        if (root == null) return false;
        return hasPathSum(root, targetSum, 0);
    }

    private static boolean hasPathSum(BinaryTreeNode root, int targetSum, int currentSum) { 
        if (root == null) return false;
        currentSum += root.val;
        
        // target is found in LEAF node
        if (currentSum == targetSum && root.left == null && root.right == null) return true;

        return hasPathSum(root.right, targetSum, currentSum) || hasPathSum(root.left, targetSum, currentSum);
    }

    /*
     * 111. Minimum Depth of Binary Tree (https://leetcode.com/problems/minimum-depth-of-binary-tree/description/
     * Given a binary tree, find its minimum depth.
     * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
     * Note: A leaf is a node with no children.
    */
    public int minDepth(BinaryTreeNode root) {
        if (root == null) return 0;
        
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        
        // if not LEAF then increase the depth
        if (left == 0 || right == 0) {
            return left + right + 1;
        } else {
            return Math.min(left, right) + 1;
        }
    }    
}
