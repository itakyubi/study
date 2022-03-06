package com.wa.leetcode;

/**
 * SubArrayRanges
 * https://leetcode-cn.com/problems/sum-of-subarray-ranges/
 *
 * @author: wuao
 * @time: 2022/2/19 18:45
 **/
public class SubArrayRanges {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        int[] nums2 = new int[]{1, 3, 3};
        int[] nums3 = new int[]{4, -2, -3, 4, 1};
        System.out.println(subArrayRanges(nums));
        System.out.println(subArrayRanges(nums2));
        System.out.println(subArrayRanges(nums3));

    }

    private static long subArrayRanges(int[] nums) {
        long res = 0;
        for (int i = 0; i < nums.length; i++) {
            int min = nums[i];
            int max = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > max) {
                    max = nums[j];
                } else if (nums[j] < min) {
                    min = nums[j];
                }
                res += max - min;
            }
        }
        return res;
    }
    
}
