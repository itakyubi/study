package com.wa.leetcode;

/**
 * LongestIncreasingSubsequence
 * 2020-08-26 17:16
 *
 * @author wuao
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS2(nums));
    }

    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int[] dp = new int[nums.length];
        dp[0] = 1;

        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j])
                    max = Math.max(max, dp[j]);
            }
            dp[i] = max + 1;
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int[] dp = new int[nums.length + 1];
        dp[1] = nums[0];

        int len = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > dp[len]) {
                dp[++len] = nums[i];
            } else {
                int left = 1, right = len, mid, index = 0;
                while (left <= right) {
                    mid = left + (right - left) / 2;
                    if (dp[mid] < nums[i]) {
                        index = mid;
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                dp[index + 1] = nums[i];
            }
        }
        return len;
    }
}
