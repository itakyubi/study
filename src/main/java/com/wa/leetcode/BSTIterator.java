package com.wa.leetcode;

import com.wa.model.TreeNode;

import java.util.Stack;

/**
 * com.wa.leetcode.BSTIterator
 * 2020-06-16 19:06
 *
 * @author wuao
 */
public class BSTIterator {

    private Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        helper(root);
    }

    private void helper(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        TreeNode node = stack.pop();
        if (node.right != null) {
            helper(node.right);
        }
        return node.val;
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return stack.size() > 0;
    }
}
