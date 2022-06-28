package com.wa.leetcode;

import com.wa.utils.Utils;

import java.util.Arrays;

/**
 * WiggleSort
 * https://leetcode.cn/problems/wiggle-sort-ii/
 * 324. 摆动排序 II
 * 2022-06-28 15:19
 *
 * @author wuao
 */
public class WiggleSort {

    /*
    给你一个整数数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
    你可以假设所有输入数组都可以得到满足题目要求的结果。      

    示例 1：
        输入：nums = [1,5,1,1,6,4]
        输出：[1,6,1,5,1,4]
        解释：[1,4,1,5,1,6] 同样是符合题目要求的结果，可以被判题程序接受。
    示例 2：
        输入：nums = [1,3,2,2,3,1]
        输出：[2,3,1,3,1,2]

    提示：
        1 <= nums.length <= 5 * 104
        0 <= nums[i] <= 5000
    题目数据保证，对于给定的输入 nums ，总能产生满足题目要求的结果
    进阶：你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
    */

    public static void main(String[] args) {
        int[] nums = new int[]{1, 5, 1, 1, 6, 4};
        int[] nums2 = new int[]{1, 3, 2, 2, 3, 1};

        wiggleSort(nums);
        System.out.println(Arrays.toString(nums));

        wiggleSort(nums2);
        System.out.println(Arrays.toString(nums2));
    }

    private static void wiggleSort(int[] nums) {
        int n = nums.length;
        int midNum = quickSelect(nums, 0, n-1, (n-1) >> 1);
        int left = 0, right = n-1;
        for (int i = 0; i <= right; i++) {
            if (nums[getIndex(i, n)] > midNum) {
                while (right > i && nums[getIndex(right, n)] > midNum) {
                    right--;
                }
                Utils.swap(nums, getIndex(i, n), getIndex(right, n));
                right--;
            }
            if (nums[getIndex(i, n)] < midNum) {
                Utils.swap(nums, getIndex(i, n), getIndex(left, n));
                left++;
            }
        }
    }

    private static int quickSelect(int[] nums, int left, int right, int target) {
        int index = partition(nums, left, right);
        if (index == target) {
            return nums[index];
        } else if (index < target) {
            return quickSelect(nums, index + 1, right, target);
        } else {
            return quickSelect(nums, left, index - 1, target);
        }
    }

    private static int partition(int[] nums, int left, int right) {
        int pivot = nums[left], small = left - 1;
        for (int i = left; i <= right; i++) {
            if (nums[i] <= pivot) {
                small++;
                Utils.swap(nums, small, i);
            }
        }
        Utils.swap(nums, left, small);
        return small;
    }

    private static int getIndex(int i, int n) {
        return (2 * n - 2 * i - 1) % (n | 1);
    }
}
