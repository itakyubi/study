package com.wa.leetcode;

/**
 * MinimumOperationsToMakeTheArrayIncreasing
 * https://leetcode.cn/problems/minimum-operations-to-make-the-array-increasing/
 * 1827. 最少操作使数组递增
 * 2022/12/11 4:25 下午
 *
 * @author wuao
 */
public class MinimumOperationsToMakeTheArrayIncreasing {

    /*
    给你一个整数数组nums（下标从 0 开始）。每一次操作中，你可以选择数组中一个元素，并将它增加1。
    比方说，如果nums = [1,2,3]，你可以选择增加nums[1]得到nums = [1,3,3]。
    请你返回使 nums严格递增的 最少操作次数。
    我们称数组nums是 严格递增的，当它满足对于所有的0 <= i < nums.length - 1都有nums[i] < nums[i+1]。一个长度为 1的数组是严格递增的一种特殊情况。

    示例 1：
        输入：nums = [1,1,1]
        输出：3
        解释：你可以进行如下操作：
                1) 增加 nums[2] ，数组变为 [1,1,2] 。
                2) 增加 nums[1] ，数组变为 [1,2,2] 。
                3) 增加 nums[2] ，数组变为 [1,2,3] 。
    示例 2：
        输入：nums = [1,5,2,4,1]
        输出：14
    示例 3：
        输入：nums = [8]
        输出：0

    提示：
        1 <= nums.length <= 5000
        1 <= nums[i] <= 104
    */

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1};
        int[] nums2 = new int[]{1, 5, 2, 4, 1};
        int[] nums3 = new int[]{8};

        System.out.println(minOperations(nums));
        System.out.println(minOperations(nums2));
        System.out.println(minOperations(nums3));
    }

    private static int minOperations(int[] nums) {
        int res = 0, prev = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= prev) {
                res += prev - nums[i] + 1;
                prev = prev + 1;
            } else {
                prev = nums[i];
            }
        }
        return res;
    }
}
