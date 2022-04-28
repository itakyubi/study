package com.wa.leetcode;

import java.util.Arrays;

/**
 * SortArrayByParity
 * 2022-04-28 16:13
 *
 * @author wuao
 */
public class SortArrayByParity {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 1, 2, 4};
        int[] nums2 = new int[]{0};

        System.out.println(Arrays.toString(sortArrayByParity(nums)));
        System.out.println(Arrays.toString(sortArrayByParity(nums2)));
    }

    private static int[] sortArrayByParity(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            while (i < j && (nums[i] & 1) == 0) {
                i++;
            }

            while (i < j && (nums[j] & 1) == 1) {
                j--;
            }

            swap(nums, i, j);
        }

        return nums;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
