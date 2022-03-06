package com.wa.algorithm;

/**
 * FindMinDrops
 *
 * @author: wuao
 * @time: 2022/2/13 20:39
 **/

/**
 * a balls, n floors, want to find the minimum number of floor
 * where a ball drops will be broken. output the minimum number
 * of drops
 **/
public class FindMinDrops {

    private static int[][] dp;

    public static void main(String[] args) {
        System.out.println(init(6, 2));
    }

    private static int init(int n, int m) {
        dp = new int[n + 1][m + 1];
        return cal(n, m);
    }

    private static int cal(int n, int m) {
        if (n == 0)
            return 0;
        if (m == 1)
            return n;

        if (dp[n][m] != 0)
            return dp[n][m];

        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            res = Math.min(res, Math.max(cal(i - 1, m - 1), cal(n - i, m)) + 1);
        }
        dp[n][m] = res;

        return res;
    }
}
