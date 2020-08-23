package com.wa.leetcode;

import java.util.Arrays;

public class PerfectSquares {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        int maxIndex = (int) Math.sqrt(n) + 1;
        int[] squareNums = new int[maxIndex];
        for (int i = 1; i < maxIndex; i++) {
            squareNums[i] = i * i;
        }

        for (int i = 1; i <= n; i++) {
            for (int s = 1; s < maxIndex; s++) {
                if (i < squareNums[s])
                    break;
                dp[i] = Math.min(dp[i], dp[i - squareNums[s]] + 1);
            }
        }
        return dp[n];
    }
}
