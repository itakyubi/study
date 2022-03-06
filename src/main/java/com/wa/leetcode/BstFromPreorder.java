package com.wa.leetcode;

import com.wa.model.TreeNode;

/**
 * BstFromPreorder
 * 2022-02-25 09:12
 *
 * @author wuao
 */
public class BstFromPreorder {

    public static void main(String[] args) {
        int[] preorder = new int[]{8, 5, 1, 7, 10, 12};
        TreeNode root = bstFromPreorder(preorder);
        System.out.println(root);
    }

    public static TreeNode bstFromPreorder(int[] preorder) {
        return helper(preorder, 0, preorder.length - 1);
    }

    private static TreeNode helper(int[] preorder, int start, int end) {
        if (start > end) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[start]);
        int index = start + 1;
        while (index <= end) {
            if (preorder[start] > preorder[index]) {
                index++;
            } else {
                break;
            }
        }

        root.left = helper(preorder, start + 1, index - 1);
        root.right = helper(preorder, index, end);
        return root;
    }
}
