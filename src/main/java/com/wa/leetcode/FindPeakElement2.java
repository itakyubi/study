package com.wa.leetcode;

/**
 * FindPeakElement2
 * https://leetcode.cn/problems/find-peak-element/
 * 162. 寻找峰值
 *
 * @Date: 2023/12/18 13:48
 * @Author: wuao
 */
public class FindPeakElement2 {

    /*
    峰值元素是指其值严格大于左右相邻值的元素。
    给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
    你可以假设 nums[-1] = nums[n] = -∞ 。
    你必须实现时间复杂度为 O(log n) 的算法来解决此问题。

    示例 1：
        输入：nums = [1,2,3,1]
        输出：2
        解释：3 是峰值元素，你的函数应该返回其索引 2。
    示例 2：
        输入：nums = [1,2,1,3,5,6,4]
        输出：1 或 5
        解释：你的函数可以返回索引 1，其峰值元素为 2；
        或者返回索引 5， 其峰值元素为 6。

    提示：
        1 <= nums.length <= 1000
        -231 <= nums[i] <= 231 - 1
        对于所有有效的 i 都有 nums[i] != nums[i + 1]
    */

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 1};
        int[] nums2 = new int[]{1, 2, 1, 3, 5, 6, 4};

        System.out.println(findPeakElement(nums));
        System.out.println(findPeakElement(nums2));
    }

    public static int findPeakElement(int[] nums) {
        // 因为对于所有有效的 i 都有 nums[i] != nums[i + 1]
        // 所以对于任意的i，通过nums[i]和nums[i+]1可以判断出，nums[i]处于上升段还是下降段
        // 如果处于上升段则峰值一定在i后边
        // 如果处于下降段则峰值一定在i前边

        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] < nums[mid + 1]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
