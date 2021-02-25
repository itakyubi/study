package com.wa.leetcode;

/**
 * GuessNumberHigherOrLowerII
 * 2021-02-25 21:24
 *
 * @author wuao
 */
public class GuessNumberHigherOrLowerII {

    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 2; i <= n; i++) {
            for (int start = 1; start <= n - i + 1; start++) {
                int min = Integer.MAX_VALUE;
                for (int pivot = start + (i - 1) / 2; pivot < start + i - 1; pivot++) {
                    int res = pivot + Math.max(dp[start][pivot - 1], dp[pivot + 1][start + i - 1]);
                    min = Math.min(res, min);
                }
                dp[start][start + i - 1] = min;
            }
        }

        return dp[1][n];
    }
}
