package com.wa.leetcode;

import com.wa.model.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * DelNodes
 * https://leetcode.cn/problems/delete-nodes-and-return-forest/
 * 1110. 删点成林
 * 2023/5/30 9:11 AM
 *
 * @author wuao
 */
public class DelNodes {

    /*
    给出二叉树的根节点root，树上每个节点都有一个不同的值。
    如果节点值在to_delete中出现，我们就把该节点从树上删去，最后得到一个森林（一些不相交的树构成的集合）。
    返回森林中的每棵树。你可以按任意顺序组织答案。

    示例 1：
        输入：root = [1,2,3,4,5,6,7], to_delete = [3,5]
        输出：[[1,2,null,4],[6],[7]]
    示例 2：
        输入：root = [1,2,4,null,3], to_delete = [3]
        输出：[[1,2,4]]

    提示：
        树中的节点数最大为1000。
        每个节点都有一个介于1 到1000之间的值，且各不相同。
        to_delete.length <= 1000
        to_delete 包含一些从1 到1000、各不相同的值。
    */

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        System.out.println(delNodes(node1, new int[]{3, 5}));
    }

    private static List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> res = new ArrayList<>();

        Set<Integer> set = new HashSet<>();
        for (int del : to_delete) {
            set.add(del);
        }

        helper(root, set, res, true);

        return res;
    }

    private static TreeNode helper(TreeNode node, Set<Integer> set, List<TreeNode> res, boolean isRoot) {
        if (node == null) {
            return null;
        }

        boolean del = set.contains(node.val);
        node.left = helper(node.left, set, res, del);
        node.right = helper(node.right, set, res, del);

        if (del) {
            return null;
        } else {
            if (isRoot) {
                res.add(node);
            }
            return node;
        }
    }
}
