package com.wa.leetcode;

/**
 * PredictTheWinner
 * 2022-02-18 09:36
 *
 * @author wuao
 */
public class PredictTheWinner {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 5, 2};
        int[] nums2 = new int[]{1, 5, 233, 7};
        System.out.println(predictTheWinner2(nums));
        System.out.println(predictTheWinner2(nums2));
    }

    private static boolean predictTheWinner(int[] nums) {
        return helper(nums, 0, nums.length - 1, 1) >= 0;
    }

    private static int helper(int[] nums, int start, int end, int turn) {
        if (start == end)
            return nums[start] * turn;

        int scoreStart = nums[start] * turn + helper(nums, start + 1, end, -turn);
        int scoreEnd = nums[end] * turn + helper(nums, start, end - 1, -turn);

        if (turn > 0) {
            return Math.max(scoreStart, scoreEnd);
        } else {
            return Math.min(scoreStart, scoreEnd);
        }
    }

    private static boolean predictTheWinner2(int[] nums) {
        int[][] dp = new int[nums.length][nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i][i] = nums[i];
        }

        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < nums.length; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][nums.length - 1] >= 0;
    }
}
