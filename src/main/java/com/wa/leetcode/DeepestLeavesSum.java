package com.wa.leetcode;

import com.wa.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * DeepestLeavesSum
 * https://leetcode.cn/problems/deepest-leaves-sum/
 * 1302. 层数最深叶子节点的和
 * 2022/8/17 2:58 下午
 *
 * @author wuao
 */
public class DeepestLeavesSum {

    /*
    给你一棵二叉树的根节点 root ，请你返回 层数最深的叶子节点的和 。

    示例 1：
        输入：root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
        输出：15
    示例 2：
        输入：root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
        输出：19

    提示：
        树中节点数目在范围 [1, 104]之间。
        1 <= Node.val <= 100
    */

    public int deepestLeavesSum(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int sum = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
        }
        return sum;
    }
}
