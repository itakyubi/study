package com.wa.leetcode;

import com.wa.model.TreeNode;

import java.util.*;

/**
 * FindFrequentTreeSum
 * https://leetcode.cn/problems/most-frequent-subtree-sum/
 * 508. 出现次数最多的子树元素和
 *
 * @author: wuao
 * @time: 2022/6/19 19:21
 **/
public class FindFrequentTreeSum {

    /*
    给你一个二叉树的根结点 root ，请返回出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的子树元素和（不限顺序）。
    一个结点的 「子树元素和」 定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。

    示例 1：
        输入: root = [5,2,-3]
        输出: [2,-3,4]

    示例 2：
        输入: root = [5,2,-5]
        输出: [2]

    提示:
        节点数在 [1, 104] 范围内
        -105 <= Node.val <= 105
    */

    private Map<Integer, Integer> map;

    public int[] findFrequentTreeSum(TreeNode root) {
        map = new HashMap<>();
        helper(root);

        int maxCount = 0;
        List<Integer> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                res.clear();
                res.add(entry.getKey());
            } else if (entry.getValue() == maxCount) {
                res.add(entry.getKey());
            }
        }
        return res.stream().mapToInt(Integer::valueOf).toArray();
    }

    private int helper(TreeNode root) {
        if (root == null)
            return 0;

        int sum = helper(root.left) + root.val + helper(root.right);
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        return sum;
    }

}
