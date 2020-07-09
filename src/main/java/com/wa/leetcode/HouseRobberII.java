package com.wa.leetcode;

/**
 * HouseRobberII
 * 2020-07-09 19:21
 *
 * @author wuao
 */
public class HouseRobberII {

    private int[] nums;

    public int rob(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];
        this.nums = nums;
        return Math.max(robHelper(0, nums.length - 2), robHelper(1, nums.length - 1));
    }

    private int robHelper(int start, int end) {
        int pre = 0, cur = 0, tmp;
        for (int i = start; i <= end; i++) {
            tmp = cur;
            cur = Math.max(pre + nums[i], cur);
            pre = tmp;
        }
        return cur;
    }
}
