package com.wa.interview;

import com.wa.utils.Utils;

/**
 * FirstMissingPositive
 * Leetcode 41
 *
 * @author: wuao
 * @time: 2020/11/11 22:03
 **/
public class FirstMissingPositive {

    public static void main(String[] args) {
        System.out.println(firstMissingPositive(new int[]{-1, 2, 3, 4}));
    }

    public static int firstMissingPositive(int[] nums) {
        if (nums == null)
            return 1;

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            if (nums[left] == left + 1) {
                left++;
            } else if (nums[left] > nums.length || nums[left] < 1 || nums[left] == nums[nums[left] - 1]) {
                nums[left] = nums[right--];
            } else {
                Utils.swap(nums, left, nums[left] - 1);
            }
        }
        return left + 1;
    }
}
