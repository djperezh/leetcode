package main.f_TreesAndGraphs;
/**
 * Definition for a binary tree node.
 */
public class BinaryTreeNode {
    int val;
    BinaryTreeNode left;
    BinaryTreeNode right;
    
    BinaryTreeNode() {}
 
    BinaryTreeNode(int val) { this.val = val; }
 
    BinaryTreeNode(int val, BinaryTreeNode left, BinaryTreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}