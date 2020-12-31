package com.wa.leetcode;

import com.wa.model.TreeNode;

/**
 * BalancedBinaryTree
 * 2020-12-31 15:03
 *
 * @author wuao
 */
public class BalancedBinaryTree {

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return Math.abs(getLevel(root.left) - getLevel(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
        }
    }

    private int getLevel(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return Math.max(getLevel(node.left), getLevel(node.right)) + 1;
        }
    }


    public boolean isBalanced2(TreeNode root) {
        return getLevel2(root) >= 0;
    }

    private int getLevel2(TreeNode node) {
        if (node == null)
            return 0;

        int left = getLevel2(node.left);
        int right = getLevel2(node.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        } else {
            return Math.max(left, right) + 1;
        }
    }
}
