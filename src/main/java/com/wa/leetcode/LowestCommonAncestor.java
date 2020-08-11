package com.wa.leetcode;

import com.wa.model.TreeNode;

public class LowestCommonAncestor {

    private TreeNode res;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return res;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return false;
        boolean left = dfs(root.left, p, q);
        boolean right = dfs(root.right, p, q);
        if (left && right || (root.val == p.val || root.val == q.val) && (left || right))
            res = root;
        return left || right || root.val == p.val || root.val == q.val;
    }
}
