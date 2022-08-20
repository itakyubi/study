package com.wa.leetcode;

import com.wa.utils.Utils;

/**
 * SortArray
 * https://leetcode.cn/problems/sort-an-array/
 * 912. 排序数组
 * 2022/8/20 4:39 下午
 *
 * @author wuao
 */
public class SortArray {

    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start >= end)
            return;
        int index = partition(nums, start, end);
        quickSort(nums, start, index - 1);
        quickSort(nums, index + 1, end);
    }

    private int partition(int[] nums, int start, int end) {
        int pivot = nums[start];
        int small = start - 1;
        for (int i = start; i <= end; i++) {
            if (nums[i] <= pivot) {
                small++;
                Utils.swap(nums, small, i);
            }
        }
        Utils.swap(nums, small, start);
        return small;
    }

}
