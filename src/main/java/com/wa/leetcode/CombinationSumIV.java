package com.wa.leetcode;

import java.util.Arrays;

/**
 * CombinationSumIV
 * 2021-04-12 19:17
 *
 * @author wuao
 */
public class CombinationSumIV {
    public int combinationSum4(int[] nums, int target) {
        if (nums.length == 0 && target > 0)
            return 0;

        int[] memory = new int[target + 1];
        Arrays.fill(memory, -1);

        return helper(nums, target, memory);
    }

    private int helper(int[] nums, int target, int[] memory) {
        if (target < 0)
            return 0;
        if (target == 0)
            return 1;
        if (memory[target] != -1)
            return memory[target];

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += helper(nums, target - nums[i], memory);
        }
        memory[target] = res;
        return res;
    }

    public int combinationSum42(int[] nums, int target) {
        if (nums.length == 0 && target > 0)
            return 0;

        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i >= nums[j]) {
                    dp[i] = dp[i] + dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }
}
