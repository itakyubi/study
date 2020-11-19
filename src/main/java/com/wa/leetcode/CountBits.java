package com.wa.leetcode;

/**
 * CountBits
 * 2020-11-19 18:54
 *
 * @author wuao
 */
public class CountBits {
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            res[i] = res[i >> 1] + (i & 1);
        }
        return res;
    }
}
