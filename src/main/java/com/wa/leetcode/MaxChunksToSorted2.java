package com.wa.leetcode;

/**
 * MaxChunksToSorted2
 * https://leetcode.cn/problems/max-chunks-to-make-sorted/
 * 769. 最多能完成排序的块
 * 2022/10/13 6:33 下午
 *
 * @author wuao
 */
public class MaxChunksToSorted2 {

    public int maxChunksToSorted(int[] arr) {
        int res = 0, max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            if (max == i) {
                res++;
            }
        }
        return res;
    }
}
