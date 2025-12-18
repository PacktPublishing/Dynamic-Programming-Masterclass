package com.company.dpintrees;

import java.util.*;

public class MaximizeRobberyInABinaryTree {

    public static void main (String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(13);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(7);
        root.left.right = new TreeNode(16);
        root.right.right = new TreeNode(15);
        root.right.right.left = new TreeNode(20);

        int maxRobberyAmount = getMaximumRobberyAmount(root);

        System.out.println("The maximum amount of money that a robber can get from the binary tree = " + maxRobberyAmount);
    }

    /*
             10
            /  \
           13   8
          / \    \
         7  16    15
                 /
               20
     */

    private static int getMaximumRobberyAmount(TreeNode root) {
        Map<TreeNode, Integer> rob = new HashMap<>();
        Map<TreeNode, Integer> notRob = new HashMap<>();

        helper(root, rob, notRob);

        // either the root node can be robbed or not robbed
        return Math.max(rob.get(root), notRob.get(root));
    }

    private static void helper(TreeNode node, Map<TreeNode, Integer> rob, Map<TreeNode, Integer> notRob) {
        // base, just ignore
        if (node == null) {
            return;
        }

        // solve for my children first
        helper(node.left, rob, notRob);
        helper(node.right, rob, notRob);

        // solve for myself - rob condition => we cannot rob the left and right child
        if (!rob.containsKey(node)) {
            int ans = node.val;
            if (node.left != null) {
                ans += notRob.get(node.left);
            }
            if (node.right != null) {
                ans += notRob.get(node.right);
            }
            rob.put(node, ans);
        }

        // solve for myself - not rob condition => we can rob as well as not rob the left and the right child, so take the max
        if (!notRob.containsKey(node)) {
            int ans = 0;
            if (node.left != null) {
                ans += Math.max(rob.get(node.left), notRob.get(node.left));
            }
            if (node.right != null) {
                ans += Math.max(rob.get(node.right), notRob.get(node.right));
            }
            notRob.put(node, ans);
        }

    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) { this.val = val; }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

