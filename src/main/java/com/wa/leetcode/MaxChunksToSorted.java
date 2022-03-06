package com.wa.leetcode;

/**
 * MaxChunksToSorted
 * 2022-02-16 08:58
 *
 * @author wuao
 */
public class MaxChunksToSorted {

    public static void main(String[] args) {
        int[] nums = new int[]{4, 3, 2, 1, 0};
        int[] nums2 = new int[]{1, 0, 2, 3, 4};
        int[] nums3 = new int[]{0, 1};
        int[] nums4 = new int[]{0, 2, 1, 4, 3};

        System.out.println(maxChunksToSorted(nums));
        System.out.println(maxChunksToSorted(nums2));
        System.out.println(maxChunksToSorted(nums3));
        System.out.println(maxChunksToSorted(nums4));
    }

    private static int maxChunksToSorted(int[] arr) {
        int res = 0;
        int endIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            endIndex = Math.max(endIndex, arr[i]);
            if (i == endIndex) {
                res++;
            }
        }
        return res;
    }
}
