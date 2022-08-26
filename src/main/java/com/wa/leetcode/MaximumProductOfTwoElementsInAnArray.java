package com.wa.leetcode;

/**
 * MaximumProductOfTwoElementsInAnArray
 * https://leetcode.cn/problems/maximum-product-of-two-elements-in-an-array/
 * 1464. 数组中两元素的最大乘积
 * 2022/8/26 10:48 上午
 *
 * @author wuao
 */
public class MaximumProductOfTwoElementsInAnArray {

    /*
    给你一个整数数组 nums，请你选择数组的两个不同下标 i 和 j，使 (nums[i]-1)*(nums[j]-1) 取得最大值。
    请你计算并返回该式的最大值。

    示例 1：
        输入：nums = [3,4,5,2]
        输出：12
        解释：如果选择下标 i=1 和 j=2（下标从 0 开始），则可以获得最大值，(nums[1]-1)*(nums[2]-1) = (4-1)*(5-1) = 3*4 = 12 。
    示例 2：
        输入：nums = [1,5,4,5]
        输出：16
        解释：选择下标 i=1 和 j=3（下标从 0 开始），则可以获得最大值 (5-1)*(5-1) = 16 。
    示例 3：
        输入：nums = [3,7]
        输出：12

    提示：
        2 <= nums.length <= 500
        1 <= nums[i] <= 10^3
    */

    public static void main(String[] args) {
        int[] nums = new int[]{3, 4, 5, 2};
        int[] nums2 = new int[]{1, 5, 4, 5};
        int[] nums3 = new int[]{3, 7};
        int[] nums4 = new int[]{10, 2, 5, 2};

        System.out.println(maxProduct(nums));
        System.out.println(maxProduct(nums2));
        System.out.println(maxProduct(nums3));
        System.out.println(maxProduct(nums4));
    }

    private static int maxProduct(int[] nums) {
        int a = -1, b = -1;
        for (int num : nums) {
            if (num >= b) {
                a = b;
                b = num;
            } else if (num > a) {
                a = num;
            }
        }
        return (a - 1) * (b - 1);
    }
}
