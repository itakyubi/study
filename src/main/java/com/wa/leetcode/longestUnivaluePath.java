package com.wa.leetcode;

import com.wa.model.TreeNode;

/**
 * longestUnivaluePath
 * https://leetcode.cn/problems/longest-univalue-path/
 * 687. 最长同值路径
 * 2022/9/2 2:04 下午
 *
 * @author wuao
 */
public class longestUnivaluePath {

    /*
    给定一个二叉树的root，返回最长的路径的长度 ，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
    两个节点之间的路径长度由它们之间的边数表示。

    示例 1:
    输入：root = [5,4,5,1,1,5]
    输出：2
    示例 2:
    输入：root = [1,4,5,4,4,5]
    输出：2

    提示:
        树的节点数的范围是 [0, 104]
        -1000 <= Node.val <= 1000
        树的深度将不超过 1000
    */

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(1);
        TreeNode node6 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;
        System.out.println(longestUnivaluePath(node1));

        TreeNode node11 = new TreeNode(1);
        TreeNode node22 = new TreeNode(4);
        TreeNode node33 = new TreeNode(5);
        TreeNode node44 = new TreeNode(4);
        TreeNode node55 = new TreeNode(4);
        TreeNode node66 = new TreeNode(5);
        node11.left = node22;
        node11.right = node33;
        node22.left = node44;
        node22.right = node55;
        node33.right = node66;
        System.out.println(longestUnivaluePath(node11));
    }

    private static int res;

    private static int longestUnivaluePath(TreeNode root) {
        res = 0;
        helper(root);
        return res;
    }

    private static int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left);
        int right = helper(root.right);
        int left1 = 0, right1 = 0;
        if (root.left != null && root.left.val == root.val) {
            left1 = left + 1;
        }
        if (root.right != null && root.right.val == root.val) {
            right1 = right + 1;
        }
        res = Math.max(res, left1 + right1);
        return Math.max(left1, right1);
    }
}
