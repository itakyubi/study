package com.wa.model;

/**
 * TreeNode
 *
 * @desc:
 * @author: wuao <wuao@baidu.com>
 * @time: 2020-01-10 14:45
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
