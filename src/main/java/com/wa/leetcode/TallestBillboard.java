package com.wa.leetcode;

/**
 * TallestBillboard
 * https://leetcode-cn.com/problems/tallest-billboard/
 * <p>
 * 2022-02-28 09:14
 *
 * @author wuao
 */
public class TallestBillboard {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 6};
        int[] nums2 = new int[]{1, 2, 3, 4, 5, 6};
        int[] nums3 = new int[]{1, 2};
        System.out.println(tallestBillboard(nums));
        System.out.println(tallestBillboard(nums2));
        System.out.println(tallestBillboard(nums3));
    }

    // dp[i][j]: 表示用前i+1个钢筋，形成两个互斥子集合的差为j，子集合的最大和
    private static int tallestBillboard(int[] rods) {
        int sum = 0;
        for (int rod : rods) {
            sum += rod;
        }

        int[][] dp = new int[rods.length + 1][sum + 1];
        for (int i = 1; i <= rods.length; i++) {
            for (int j = 0; j <= sum; j++) {
                // 如果两个子集的差为j, 它们的和肯定大于j
                if (dp[i - 1][j] < j)
                    continue;

                // 元素i不放入任一一个子集合，那么子集差与子集和都不更新
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);

                // 元素i放入和更大的子集合，那么子集合差就会更大，更新子集差为 j + rods[i] 位置的子集和
                dp[i][j + rods[i - 1]] = Math.max(dp[i][j + rods[i - 1]], rods[i - 1] + dp[i - 1][j]);

                // 元素i放入和更小的子集合，那么子集合差就会变小，更新子集差为 j - rods[i] 位置的子集和
                int tmp = Math.abs(j - rods[i - 1]);
                dp[i][tmp] = Math.max(dp[i][tmp], rods[i - 1] + dp[i - 1][j]);
            }
        }

        return dp[rods.length][0] / 2;
    }
}
