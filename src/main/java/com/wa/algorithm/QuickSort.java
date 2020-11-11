package com.wa.algorithm;

import com.wa.utils.Utils;

/**
 * QuickSort
 * 2020-11-11 17:06
 *
 * @author wuao
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 7, 1, 2, 6, 5, 8, 4};
        quickSort(nums, 0, nums.length - 1);
    }

    public static void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private static void quickSort(int[] nums, int start, int end) {
        if (start >= end)
            return;

        int pivot = nums[start];
        int left = start, right = end;
        while (left < right) {
            while (left < right && nums[right] > pivot)
                right--;
            while (left < right && nums[left] <= pivot)
                left++;
            Utils.swap(nums, left, right);
        }
        Utils.swap(nums, left, start);
        quickSort(nums, start, left - 1);
        quickSort(nums, left + 1, end);
    }
}
