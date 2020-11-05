package com.wa.leetcode;

import java.util.Arrays;

/**
 * CoinChange
 * 2020-11-05 16:41
 *
 * @author wuao
 */
public class CoinChange {

    public static void main(String[] args) {
        System.out.println(coinChange2(new int[]{1, 2, 5}, 11));
    }

    private static int res = Integer.MAX_VALUE;

    public static int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        helper(coins, coins.length - 1, amount, 0);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private static void helper(int[] coins, int index, int amount, int count) {
        if (amount == 0) {
            res = Math.min(res, count);
        } else if (amount < 0) {
            return;
        }

        for (int i = index; i >= 0; i--) {
            count++;
            helper(coins, i, amount - coins[i], count);
            count--;
        }
    }

    public static int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
}
