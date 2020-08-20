package com.wa.leetcode;

import java.util.Arrays;

/**
 * HIndex
 * 2020-08-20 19:08
 *
 * @author wuao
 */
public class HIndex {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int i = 0;
        while (i < citations.length && citations[citations.length - 1 - i] > i)
            i++;
        return i;
    }

    public int hIndex2(int[] citations) {
        int len = citations.length;
        int[] tmp = new int[len + 1];
        for (int c : citations)
            tmp[Math.min(len, c)]++;

        int i = len;
        for (int s = tmp[len]; i > s; s += tmp[i])
            i--;
        return i;
    }
}
