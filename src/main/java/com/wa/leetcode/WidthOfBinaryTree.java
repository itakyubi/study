package com.wa.leetcode;

import com.wa.model.TreeNode;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * WidthOfBinaryTree
 * https://leetcode.cn/problems/maximum-width-of-binary-tree/
 * 662. 二叉树最大宽度
 * 2022/8/27 2:37 下午
 *
 * @author wuao
 */
public class WidthOfBinaryTree {

    /*
    给你一棵二叉树的根节点 root ，返回树的 最大宽度 。
    树的 最大宽度 是所有层中最大的 宽度 。
    每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。
    题目数据保证答案将会在 32 位 带符号整数范围内。

    示例 1：
        输入：root = [1,3,2,5,3,null,9]
        输出：4
        解释：最大宽度出现在树的第 3 层，宽度为 4 (5,3,null,9) 。
    示例 2：
        输入：root = [1,3,2,5,null,null,9,6,null,7]
        输出：7
        解释：最大宽度出现在树的第 4 层，宽度为 7 (6,null,null,null,null,null,7) 。
    示例 3：
        输入：root = [1,3,2,5]
        输出：2
        解释：最大宽度出现在树的第 2 层，宽度为 2 (3,2) 。
    */

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(9);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;

        System.out.println(widthOfBinaryTree(node1));
    }

    private static int widthOfBinaryTree(TreeNode root) {
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 1));
        int max = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int start = -1, end = -1;
            for (int i = 0; i < size; i++) {
                Pair<TreeNode, Integer> pair = queue.poll();
                if (start == -1) {
                    start = pair.getValue();
                }
                end = pair.getValue();

                if (pair.getKey().left != null) {
                    queue.offer(new Pair<>(pair.getKey().left, 2 * pair.getValue()));
                }
                if (pair.getKey().right != null) {
                    queue.offer(new Pair<>(pair.getKey().right, 2 * pair.getValue() + 1));
                }
            }
            max = Math.max(max, end - start + 1);
        }
        return max;
    }
}
