package main.f_TreesAndGraphs;

import java.util.Stack;

import javax.swing.tree.TreeNode;

public class BinaryTreesDFS {
    /* 
     * Example 1
     * 104. Maximum Depth of Binary Tree (https://leetcode.com/problems/maximum-depth-of-binary-tree/description/)
     * Given the root of a binary tree, return its maximum depth.
     * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
    */
    public static int maxDepth(BinaryTreeNode root) { 
        return (root == null) ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /* 
     * Example 1
     * 104. Maximum Depth of Binary Tree (https://leetcode.com/problems/maximum-depth-of-binary-tree/description/)
     * Given the root of a binary tree, return its maximum depth.
     * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
    */
    public static int maxDepthIterative(BinaryTreeNode root) {
        if (root == null) return 0;

        Stack<PairLevel> stack = new Stack<>();
        stack.push(new PairLevel(root, 1));
        int ans = 0;
        while (!stack.empty()) {
            PairLevel pair = stack.pop();
            BinaryTreeNode node = pair.node;
            int depth = pair.depth;
            ans = Math.max(ans, depth);
            if (node.left != null) stack.push(new PairLevel(node.left, depth + 1));
            if (node.right != null) stack.push(new PairLevel(node.right, depth + 1));
        }
        return ans;
    }

    public static class PairLevel {
        public BinaryTreeNode node;
        public int depth;

        public PairLevel(BinaryTreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    /*
     * Example 2
     * 112. Path Sum (https://leetcode.com/problems/path-sum/description/)
     * Given the root of a binary tree and an integer targetSum,
     * return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
     * A leaf is a node with no children.
    */
    public static boolean hasPathSum(BinaryTreeNode root, int targetSum) {
        return (root == null) ? false : dfsPathSum(root, targetSum, 0);
    }

    private static boolean dfsPathSum(BinaryTreeNode root, int targetSum, int currentSum) { 
        if (root == null) return false;
        currentSum += root.val;        
        // target is found in LEAF node
        return (currentSum == targetSum && root.left == null && root.right == null) ? true :
            dfsPathSum(root.right, targetSum, currentSum) || dfsPathSum(root.left, targetSum, currentSum);
    }

    /*
     * Example 2
     * 112. Path Sum (https://leetcode.com/problems/path-sum/description/)
     * Given the root of a binary tree and an integer targetSum,
     * return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
     * A leaf is a node with no children.
    */
    public static boolean hasPathSumIterative(BinaryTreeNode root, int targetSum) {
        if (root == null) return false;
        
        Stack<PairSum> s = new Stack<>();
        s.push(new PairSum(root, root.val));

        while (!s.empty()) {
            PairSum p = s.pop();
            int currentSum = p.currSum;
            BinaryTreeNode n = p.node;
            // if Node is LEAF and Sum found
            if (n.left == null && n.right == null && currentSum == targetSum) return true;
            // else Then update the curr sum and add the children to the stack
            currentSum += n.val;
            if (n.left != null) s.push(new PairSum(n.left, currentSum + n.left.val));
            if (n.right != null) s.push(new PairSum(n.right, currentSum + n.right.val));
        }
        return false;
    }

    public static class PairSum {
        public BinaryTreeNode node;
        public int currSum;

        PairSum(BinaryTreeNode n, int sum) {
            node = n;
            currSum = sum;
        }
    }

    /*
     * Example 3
     * 1448. Count Good Nodes in Binary Tree (https://leetcode.com/problems/count-good-nodes-in-binary-tree/)
     * Given the root of a binary tree, find the number of nodes that are good.
     * A node is good if the path between the root and the node has no nodes with a greater value.
    */
    public static int goodNodes(BinaryTreeNode root) {
        return dfsGoodNodes(root, Integer.MIN_VALUE);
    }

    private static int dfsGoodNodes(BinaryTreeNode node, int maxValue) {
        if (node == null) return 0;
        int ans = dfsGoodNodes(node.left, Math.max(node.val, maxValue)) +
            dfsGoodNodes(node.right, Math.max(node.val, maxValue));
        return node.val >= maxValue ? ans + 1 : ans;
    }

    /*
     * Example 3
     * 1448. Count Good Nodes in Binary Tree (https://leetcode.com/problems/count-good-nodes-in-binary-tree/)
     * Given the root of a binary tree, find the number of nodes that are good.
     * A node is good if the path between the root and the node has no nodes with a greater value.
    */
    public static int goodNodesIterative(BinaryTreeNode root) {
        if (root == null) return 0;
        int ans = 0;
        Stack<PairMax> s = new Stack<>();
        s.push(new PairMax(root, Integer.MIN_VALUE));
        while (!s.isEmpty()) {
            PairMax p = s.pop();
            if (p.node.left != null) s.push(new PairMax(p.node.left, Math.max(p.maxSoFar, p.node.val)));
            if (p.node.right != null) s.push(new PairMax(p.node.right, Math.max(p.maxSoFar, p.node.val)));
            ans += (p.node.val >= p.maxSoFar) ? 1 : 0;
        }
        return ans;
    }

    private static class PairMax {
        BinaryTreeNode node;
        int maxSoFar;
        
        PairMax(BinaryTreeNode node, int maxSoFar) {
            this.node = node;
            this.maxSoFar = maxSoFar;
        }
    }

    /*
     * Example 4
     * 100. Same Tree (https://leetcode.com/problems/same-tree/)
     * Given the roots of two binary trees p and q, check if they are the same tree. 
     * Two binary trees are the same tree if they are structurally identical and the nodes have the same values.
    */
    public static boolean isSameTree(BinaryTreeNode p, BinaryTreeNode q) {
        return (p != null && q != null) ? 
            p.val == q.val &&
            isSameTree(p.left, q.left) && 
            isSameTree(p.right, q.right) : p == q;
    }

    /*
     * Example 4
     * 100. Same Tree (https://leetcode.com/problems/same-tree/)
     * Given the roots of two binary trees p and q, check if they are the same tree. 
     * Two binary trees are the same tree if they are structurally identical and the nodes have the same values.
    */
    public static boolean isSameTreeIterative(BinaryTreeNode p, BinaryTreeNode q) {
        if (p == null && q == null) return true;

        Stack<NodePair> s = new Stack<>();
        s.push(new NodePair(p, q));
        while (!s.isEmpty()) {
            NodePair pair = s.pop();
            if (pair.p == null && pair.q == null) continue;
            // If one null is null then false
            if (pair.p == null || pair.q == null) return false;
            // If value is not equal then false
            if (pair.q.val != pair.p.val) return false;
            s.push(new NodePair(pair.p.left, pair.q.left));
            s.push(new NodePair(pair.p.right, pair.q.right));
        }
        return true;
    }

    private static class NodePair {
        BinaryTreeNode p;
        BinaryTreeNode q;
        
        NodePair(BinaryTreeNode p, BinaryTreeNode q) {
            this.p = p;
            this.q = q;
        }
    }

    /*
     * Bonus example
     * 236. Lowest Common Ancestor of a Binary Tree (https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/)
     * Given the root of a binary tree and two nodes p and q that are in the tree,
     * return the lowest common ancestor (LCA) of the two nodes.
     * The LCA is the lowest node in the tree that has both p and q as descendants
     * (note: a node is a descendant of itself).
    */
    public BinaryTreeNode lowestCommonAncestor(BinaryTreeNode root, BinaryTreeNode p, BinaryTreeNode q) {
        if (root == null) return root;

        // 1st case: node is one of the targets (p OR q), then return it
        if (root == p || root == q) return root;

        // DFS traversal to find the targets (backtracking the LCA node)
        BinaryTreeNode l = lowestCommonAncestor(root.left, p, q);
        BinaryTreeNode r = lowestCommonAncestor(root.right, p, q);

        // 2nd case: Tagets found in Left and Right, then return current node
        if (l != null && r != null) return root;

        // 3rd case: if targets found in ONLY one side (l OR r)
        if (l != null) return l;
        return r;
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

    /*
     * 1026. Maximum Difference Between Node and Ancestor (https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/editorial/)
     * Given the root of a binary tree, find the maximum value v for which there exist different nodes a and b
     * where v = |a.val - b.val| and a is an ancestor of b.
     * A node a is an ancestor of b if either: any child of a is equal to b or any child of a is an ancestor of b.
    */
    static int diff = Integer.MIN_VALUE;
    public static int maxAncestorDiff(BinaryTreeNode root) {
        if (root == null) return 0;
        dfsMaxAncestorDiff(root, root.val, root.val);
        return diff;
    }

    private static void dfsMaxAncestorDiff(BinaryTreeNode n,int max,int min){
        if (n == null) return;
        max = Math.max(max, n.val);
        min = Math.min(min, n.val);

        diff = Math.max(diff, max - min);

        dfsMaxAncestorDiff(n.left, max, min);
        dfsMaxAncestorDiff(n.right, max, min);
    }


    /*
     * 543. Diameter of Binary Tree (https://leetcode.com/problems/diameter-of-binary-tree/editorial/)
     * Given the root of a binary tree, return the length of the diameter of the tree.
     * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
     * This path may or may not pass through the root.
     * The length of a path between two nodes is represented by the number of edges between them.
    */
    private static int result;

    public static int diameterOfBinaryTree(BinaryTreeNode root) {
        result = 0;
        findDiameter(root);
        return result;
    }

    private static int findDiameter(BinaryTreeNode n) {
        if (n == null) return 0;
        int lMax = findDiameter(n.left);
        int rMax = findDiameter(n.right);
        result = Math.max(result, lMax + rMax);
        return Math.max(lMax, rMax) + 1;
    }
}
