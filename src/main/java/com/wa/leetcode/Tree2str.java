package com.wa.leetcode;

import com.wa.model.TreeNode;

/**
 * Tree2str
 * https://leetcode-cn.com/problems/construct-string-from-binary-tree/
 *
 * @author: wuao
 * @time: 2022/3/19 16:57
 **/
public class Tree2str {

    private StringBuilder sb;

    public String tree2str(TreeNode root) {
        sb = new StringBuilder();
        helper(root);
        return sb.toString();
    }

    private void helper(TreeNode root) {
        if (root == null)
            return;
        sb.append(root.val);

        if (root.left == null && root.right == null)
            return;

        sb.append("(");
        helper(root.left);
        sb.append(")");

        if (root.right != null) {
            sb.append("(");
            helper(root.right);
            sb.append(")");
        }
    }
}
