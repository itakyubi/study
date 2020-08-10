package com.wa.leetcode;

import com.wa.model.TreeNode;

public class KthSmallest {

    private int count;
    private int k;
    private int res;

    public int kthSmallest(TreeNode root, int k) {
        this.count = 0;
        this.k = k;
        inorder(root);
        return res;
    }

    private void inorder(TreeNode root) {
        if (root == null || count > k)
            return;
        inorder(root.left);
        count++;
        if (count == k)
            res = root.val;
        inorder(root.right);
    }
}
