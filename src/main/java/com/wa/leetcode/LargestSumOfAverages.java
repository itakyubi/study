package com.wa.leetcode;

/**
 * LargestSumOfAverages
 * https://leetcode.cn/problems/largest-sum-of-averages/
 * 813. 最大平均值和的分组
 * 2022/11/28 5:21 下午
 *
 * @author wuao
 */
public class LargestSumOfAverages {

    /*
    给定数组 nums 和一个整数 k 。我们将给定的数组 nums 分成 最多 k 个相邻的非空子数组 。 分数 由每个子数组内的平均值的总和构成。
    注意我们必须使用 nums 数组中的每一个数进行分组，并且分数不一定需要是整数。
    返回我们所能得到的最大 分数 是多少。答案误差在 10-6 内被视为是正确的。

    示例 1:
        输入: nums = [9,1,2,3,9], k = 3
        输出: 20.00000
        解释:
        nums 的最优分组是[9], [1, 2, 3], [9]. 得到的分数是 9 + (1 + 2 + 3) / 3 + 9 = 20.
        我们也可以把 nums 分成[9, 1], [2], [3, 9].
        这样的分组得到的分数为 5 + 2 + 6 = 13, 但不是最大值.
    示例 2:
        输入: nums = [1,2,3,4,5,6,7], k = 4
        输出: 20.50000

    提示:
        1 <= nums.length <= 100
        1 <= nums[i] <= 104
        1 <= k <= nums.length
    */

    public static void main(String[] args) {
        int[] nums = new int[]{9, 1, 2, 3, 9};
        int k = 3;

        int[] nums2 = new int[]{1, 2, 3, 4, 5, 6, 7};
        int k2 = 4;

        int[] nums3 = new int[]{4, 1, 7, 5, 6, 2, 3};
        int k3 = 4;

        System.out.println(largestSumOfAverages(nums, k));
        System.out.println(largestSumOfAverages(nums2, k2));
        System.out.println(largestSumOfAverages(nums3, k3));
    }

    private static double largestSumOfAverages(int[] nums, int k) {
        // 定义dp[i][j]表示前i个元素分成j组的最大平均和，dp[n][k]即为答案
        // 当j=1时，dp[i][j] = avg(0,i)
        // 当j>1时，dp[i][j] = max(dp[m-1][j-1] + avg(m,i))，2<=m<=i

        // 前缀和
        double[] sum = new double[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        double[][] dp = new double[nums.length + 1][k + 1];
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= k; j++) {
                if (j == 1) {
                    dp[i][j] = sum[i] / i;
                } else {
                    for (int m = 2; m <= i; m++) {
                        dp[i][j] = Math.max(dp[i][j], dp[m - 1][j - 1] + (sum[i] - sum[m - 1]) / (i - m + 1));
                    }
                }
            }
        }
        return dp[nums.length][k];
    }
}
