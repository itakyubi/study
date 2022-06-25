package com.wa.leetcode;

import com.wa.model.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LargestValues
 * https://leetcode.cn/problems/find-largest-value-in-each-tree-row/
 * 515. 在每个树行中找最大值
 * 2022-06-24 09:04
 *
 * @author wuao
 */
public class LargestValues {

    /*
    给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。

    示例1：
        输入: root = [1,3,2,5,3,null,9]
        输出: [1,3,9]

    示例2：
        输入: root = [1,2,3]
        输出: [1,3]
             
    提示：
        二叉树的节点个数的范围是 [0,104]
        -231 <= Node.val <= 231 - 1
    */

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
                max = Math.max(max, node.val);
            }
            res.add(max);
        }
        return res;
    }
}
