package com.wa.interview.leetcode;

import java.util.Arrays;

/**
 * Wanquanpinfangshu
 * 完全平方数
 * 2023/4/24 6:22 下午
 *
 * @author wuao
 */
public class Wanquanpinfangshu {

    public static void main(String[] args) {
        int n = 25;
        int n2 = 4;
        int n3 = 10;
        int n4 = 48;
        System.out.println(numSquares2(n));
        System.out.println(numSquares2(n2));
        System.out.println(numSquares2(n3));
        System.out.println(numSquares2(n4));
    }


    private static int numSquares(int n) {
        // dp[i]=k 代表最少需要k个平方数来表示i
        int[] dp = new int[n + 1];

        // dp[i] = min(dp[i - j*j]) + 1, 1<=j<=sqrt(i)
        // +1表示取一个数，i-j*j就是剩下的值
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j]);
            }
            dp[i]++;
        }
        return dp[n];
    }


    private static int[] record;

    private static int numSquares2(int n) {
        record = new int[n + 1];
        Arrays.fill(record, -1);
        return helper(n, 0);
    }

    // record[i]第一次找到的不一定是最小次数，所以优化在部分情况下失效
    private static int helper(int n, int level) {
        if (n < 0) {
            return -1;
        }
        if (n == 0) {
            return level;
        }
        if (record[n] != -1) {
            return record[n] + level;
        }

        int max = (int) Math.sqrt(n);
        int min = Integer.MAX_VALUE;
        for (int i = max; i >= 1; i--) {
            int tmp = helper(n - i * i, level + 1);
            if (tmp > 0) {
                record[n - i * i] = tmp;
                min = Math.min(min, tmp);
            }
        }

        return min;
    }


}
