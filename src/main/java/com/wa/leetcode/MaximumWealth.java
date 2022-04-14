package com.wa.leetcode;

/**
 * MaximumWealth
 * https://leetcode-cn.com/problems/richest-customer-wealth/
 * 2022-04-14 09:12
 *
 * @author wuao
 */
public class MaximumWealth {
    public int maximumWealth(int[][] accounts) {
        int max = 0;
        for (int[] account : accounts) {
            int sum = 0;
            for (int a : account) {
                sum += a;
            }
            max = Math.max(max, sum);
        }
        return max;
    }
}
