package com.wa.leetcode;

/**
 * MaxProfit
 * 2020-10-09 16:06
 *
 * @author wuao
 */
public class MaxProfit {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;

        int a = -prices[0], b = 0, c = 0;
        for (int i = 1; i < prices.length; i++) {
            int aa = Math.max(a, c - prices[i]);
            int bb = a + prices[i];
            int cc = Math.max(b, c);
            a = aa;
            b = bb;
            c = cc;
        }
        return Math.max(b, c);
    }
}
