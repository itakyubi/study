package com.wa.leetcode;

import com.wa.model.TreeNode;

/**
 * MaxValue
 * https://leetcode-cn.com/problems/er-cha-shu-ran-se-UGC/
 *
 * @author: wuao
 * @time: 2022/2/27 8:22
 **/
public class MaxValue {

    private int res;

    public int maxValue(TreeNode root, int k) {
        int[] dp = helper(root, k);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i <= k; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    private int[] helper(TreeNode root, int k) {
        int[] dp = new int[k + 1];
        if (root == null)
            return dp;

        int[] left = helper(root.left, k);
        int[] right = helper(root.right, k);

        int maxLeft = Integer.MIN_VALUE, maxRight = Integer.MIN_VALUE;
        for (int i = 0; i <= k; i++) {
            maxLeft = Math.max(maxLeft, left[i]);
            maxRight = Math.max(maxRight, right[i]);
        }
        dp[0] = maxLeft + maxRight;

        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] = Math.max(dp[i], root.val + left[j] + right[i - j - 1]);
            }
        }
        return dp;
    }


}
