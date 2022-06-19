package com.wa.leetcode;

import com.wa.model.TreeNode;

/**
 * DeleteNode
 *
 * @author: wuao
 * @time: 2022/6/2 9:01
 **/
public class DeleteNode {

    public static void main(String[] args) {
        TreeNode node0 = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);


        node3.left = node2;
        node3.right = node4;
        node6.right = node7;
        node5.left = node3;
        node5.right = node6;

        TreeNode root = node5;


    }

    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (root.val > key) {
            root.left = deleteNode(root.left, key);
            return root;
        }
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
            return root;
        }

        if (root.left == null && root.right == null) {
            return null;
        }
        if (root.left == null) {
            return root.right;
        }
        if (root.right == null) {
            return root.left;
        }
        TreeNode next = root.right;
        while (next.left != null) {
            next = next.left;
        }

        root.right = deleteNode(root.right, next.val);
        next.left = root.left;
        next.right = root.right;

        return next;
    }


}
