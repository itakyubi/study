package com.wa.leetcode;

/**
 * IntegerBreak
 * 2020-11-24 18:52
 *
 * @author wuao
 */
public class IntegerBreak {

    public static void main(String[] args) {
        System.out.println(integerBreak(2));
        System.out.println(integerBreak(3));
        System.out.println(integerBreak(10));
    }

    public static int integerBreak(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            int tmpMax = 0;
            for (int j = 1; j < i; j++) {
                tmpMax = Math.max(tmpMax, Math.max(j * (i - j), j * dp[i - j]));
            }
            dp[i] = tmpMax;
        }
        return dp[n];
    }
}
