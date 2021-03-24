package com.wa.leetcode;

/**
 * WiggleSubsequence
 * 2021-03-17 17:24
 *
 * @author wuao
 */
public class WiggleSubsequence {
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if (n < 2)
            return n;
        int up = 1, down = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                up = Math.max(up, down + 1);
            } else if (nums[i] < nums[i - 1]) {
                down = Math.max(up + 1, down);
            }
        }
        return Math.max(up, down);
    }
}
