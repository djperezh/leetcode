package main.f_TreesAndGraphs;

import java.util.*;

public class BinaryTreesBFS {
    /*
     * Example 1
     * 199. Binary Tree Right Side View (https://leetcode.com/problems/binary-tree-right-side-view/description/)
     * Given the root of a binary tree, imagine yourself standing on the right side of it,
     * return the values of the nodes you can see ordered from top to bottom.
    */
    public static List<Integer> rightSideView(BinaryTreeNode root) {
        if (root == null) return new ArrayList<Integer>();

        List<Integer> ans = new ArrayList<>();
        Queue<BinaryTreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int nodesPerLevel = q.size();
            for (int i = 0; i < nodesPerLevel; i++){
                BinaryTreeNode mostRightNode = q.poll();
                if (mostRightNode.left != null) q.offer(mostRightNode.left);
                if (mostRightNode.right != null) q.offer(mostRightNode.right);
                if (i == nodesPerLevel - 1) ans.add(mostRightNode.val);
            }
        }
        return ans;
    }

    /*
     * Example 2
     * 515. Find Largest Value in Each Tree Row (https://leetcode.com/problems/find-largest-value-in-each-tree-row/description/)
     * Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).
    */
    public static List<Integer> largestValues(BinaryTreeNode root) {
        if (root == null) return new ArrayList<>();

        List<Integer> ans = new ArrayList<>();
        Queue<BinaryTreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int nodesPerLevel = q.size();
            int largest = q.peek().val;
            for (int i = 1; i <= nodesPerLevel; i++) {
                BinaryTreeNode n = q.poll();
                if (n.left != null) q.offer(n.left);
                if (n.right != null) q.offer(n.right);
                largest = Math.max(largest, n.val);
            }
            ans.add(largest);
        }
        return ans;
    }

    /*
     * 1302. Deepest Leaves Sum (https://leetcode.com/problems/deepest-leaves-sum/description/)
     * Given the root of a binary tree, return the sum of values of its deepest leaves. 
    */
    public static int deepestLeavesSum(BinaryTreeNode root) {
        if (root == null) return 0;

        Queue<BinaryTreeNode> q = new LinkedList<>();
        q.add(root);

        int sum = 0;
        while (!q.isEmpty()) {
            int nodesPerLevel = q.size();
            sum = 0;
            for (int i = 1; i <= nodesPerLevel; i++) {
                BinaryTreeNode n = q.poll();
                if (n.left != null) q.add(n.left);
                if (n.right != null) q.add(n.right);
                sum += n.val;
            }
        }
        return sum;
    }

    /*
     * 103. Binary Tree Zigzag Level Order Traversal (https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/)
     * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. 
     * (i.e., from left to right, then right to left for the next level and alternate between).
    */
    public List<List<Integer>> zigzagLevelOrder(BinaryTreeNode root) { 
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if (root == null) return ans;

        Queue<BinaryTreeNode> q = new LinkedList<>();
        q.add(root);

        boolean directionFlag = true;
        while (!q.isEmpty()) {
            int nodesPerLevel = q.size();
            List<Integer> levelNodeValues = new ArrayList<>();

            for (int i = 1; i <= nodesPerLevel; i++) {
                BinaryTreeNode n = q.poll();
                if (n.left != null) q.add(n.left);
                if (n.right != null) q.add(n.right);

                // Add the value to the beggining OR last of the list based on the flag
                if (directionFlag) {
                    levelNodeValues.add(n.val);
                } else {
                    levelNodeValues.add(0, n.val);
                }
            }
            ans.add(levelNodeValues);
            directionFlag = !directionFlag;
        }
        return ans;
    }


}
