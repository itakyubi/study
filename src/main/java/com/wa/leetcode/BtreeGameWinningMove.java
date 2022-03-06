package com.wa.leetcode;

import com.wa.model.TreeNode;

/**
 * BtreeGameWinningMove
 * https://leetcode-cn.com/problems/binary-tree-coloring-game/
 *
 * @author: wuao
 * @time: 2022/2/26 17:45
 **/
public class BtreeGameWinningMove {
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        TreeNode xNode = findNode(root, x);
        int half = n / 2;
        int leftCount = count(xNode.left);
        int rightCount = count(xNode.right);
        return leftCount > half || rightCount > half || leftCount + rightCount < half;
    }

    private TreeNode findNode(TreeNode root, int x) {
        if (root == null)
            return null;

        if (root.val == x) {
            return root;
        }

        TreeNode left = findNode(root.left, x);
        if (left != null) {
            return left;
        }
        return findNode(root.right, x);
    }

    private int count(TreeNode root) {
        if (root == null)
            return 0;
        return count(root.left) + count(root.right) + 1;
    }
}
