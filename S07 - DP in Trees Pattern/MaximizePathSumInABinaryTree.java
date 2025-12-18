package com.company.dpintrees;

import java.util.HashMap;
import java.util.Map;

public class MaximizePathSumInABinaryTree {

    private static int maxPathSum = Integer.MIN_VALUE;

    public static void main (String[] args) {
        TreeNode root = new TreeNode(3,
                new TreeNode(-5, new TreeNode(12), null),
                new TreeNode(2, new TreeNode(-10), new TreeNode(7, new TreeNode(-8), null)));

        getMaximumPathSumInABinaryTree(root);

        System.out.println("The maximum path sum in the binary tree = " + maxPathSum);
    }

    /*
             3
            / \
           -5  2
          /   / \
         12 -10  7
                /
              -8
     */

    private static void getMaximumPathSumInABinaryTree(TreeNode root) {
        Map<TreeNode, Integer> maxPathSumGoingDown = new HashMap<>();

        helper(maxPathSumGoingDown, root);
    }

    private static void helper(Map<TreeNode, Integer> maxPathSumGoingDown, TreeNode node) {
        // if null, just ignore
        if (node == null) {
            return;
        }

        // first do the work for children so I can use their answers for calculate ans at node
        if (!maxPathSumGoingDown.containsKey(node.left)) {
            helper(maxPathSumGoingDown, node.left);
        }

        if (!maxPathSumGoingDown.containsKey(node.right)) {
            helper(maxPathSumGoingDown, node.right);
        }

        // MaxPathSumGoingDown = Max (val, val + Max (left, right))
        int left = maxPathSumGoingDown.getOrDefault(node.left, 0);
        int right = maxPathSumGoingDown.getOrDefault(node.right, 0);

        int ans = Math.max(node.val, node.val + Math.max(left, right));

        maxPathSumGoingDown.put(node, ans);

        // MaxPathSumThrough - [v, v + a, v + b, v + a + b], MaxPathSumThrough needs to be calculated for every node and then take the overall max
        maxPathSum = Math.max(maxPathSum, Math.max(Math.max(node.val, node.val + left), Math.max(node.val + right, node.val + left + right)));
    }
}

/*
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
 */
