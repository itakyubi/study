package com.wa.leetcode;

import com.wa.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * FindBottomLeftValue
 * https://leetcode.cn/problems/find-bottom-left-tree-value/
 * 513. 找树左下角的值
 *
 * @author: wuao
 * @time: 2022/6/22 22:15
 **/
public class FindBottomLeftValue {

    /*
    给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
    假设二叉树中至少有一个节点。

    示例 1:
    输入: root = [2,1,3]
    输出: 1
    示例 2:
    输入: [1,2,3,4,null,5,6,null,null,7]
    输出: 7
             
    提示:
        二叉树的节点个数的范围是 [1,104]
        -231 <= Node.val <= 231 - 1 
    */

    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int res = root.val;
        while (!queue.isEmpty()) {
            int size = queue.size();
            res = queue.peek().val;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return res;
    }
}
