package com.wa.leetcode;

import com.wa.utils.Utils;

/**
 * FindKthLargest
 * 2021-12-16 15:27
 *
 * @author wuao
 */
public class FindKthLargest {

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 1, 2, 3, 8};
        System.out.println(findKthLargest(nums, 3));
    }

    public static int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    private static int quickSelect(int[] nums, int l, int r, int target) {
        int index = partition(nums, l, r);
        if (index == target) {
            return nums[index];
        } else if (index < target) {
            return quickSelect(nums, index + 1, r, target);
        } else {
            return quickSelect(nums, l, index - 1, target);
        }
    }

    private static int partition(int[] nums, int l, int r) {
        int pivot = nums[l];
        int small = l - 1;
        for (int i = l; i <= r; i++) {
            if (nums[i] <= pivot) {
                small++;
                Utils.swap(nums, small, i);
            }
        }
        Utils.swap(nums, l, small);
        return small;
    }


}
