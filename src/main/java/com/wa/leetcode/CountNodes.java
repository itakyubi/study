package com.wa.leetcode;

import com.wa.model.TreeNode;

/**
 * CountNodes
 * 2020-07-21 18:14
 *
 * @author wuao
 */
public class CountNodes {
    public int countNodes(TreeNode root) {
        return root == null ? 0 : 1 + countNodes(root.left) + countNodes(root.right);
    }

    public int countNodes2(TreeNode root) {
        if (root == null)
            return 0;
        int leftLevel = countLevel(root.left);
        int rightLevel = countLevel(root.right);
        if (leftLevel == rightLevel) {
            return countNodes(root.right) + (1 << leftLevel);
        } else {
            return countNodes(root.left) + (1 << rightLevel);
        }
    }

    public int countNodes3(TreeNode root) {
        if (root == null)
            return 0;

        int d = countLevel(root);
        if (d == 0)
            return 1;

        int left = 1, right = (int) Math.pow(2, d) - 1, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (exist(mid, d, root)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return (int) Math.pow(2, d) - 1 + left;
    }

    private boolean exist(int idx, int d, TreeNode node) {
        int left = 0, right = (int) Math.pow(2, d) - 1, mid;
        for (int i = 0; i < d; i++) {
            mid = left + (right - left) / 2;
            if (idx <= mid) {
                node = node.left;
                right = mid;
            } else {
                node = node.right;
                left = mid + 1;
            }
        }
        return node != null;
    }

    private int countLevel(TreeNode node) {
        int level = 0;
        while (node != null) {
            node = node.left;
            level++;
        }
        return level;
    }
}
