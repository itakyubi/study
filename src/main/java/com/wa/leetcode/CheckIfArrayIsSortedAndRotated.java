package com.wa.leetcode;

/**
 * CheckIfArrayIsSortedAndRotated
 * https://leetcode.cn/problems/check-if-array-is-sorted-and-rotated/
 * 1752. 检查数组是否经排序和轮转得到
 * 2022/11/27 3:21 下午
 *
 * @author wuao
 */
public class CheckIfArrayIsSortedAndRotated {

    /*
    给你一个数组 nums 。nums 的源数组中，所有元素与 nums 相同，但按非递减顺序排列。
    如果 nums 能够由源数组轮转若干位置（包括 0 个位置）得到，则返回 true ；否则，返回 false 。
    源数组中可能存在 重复项 。
    注意：我们称数组 A 在轮转 x 个位置后得到长度相同的数组 B ，当它们满足 A[i] == B[(i+x) % A.length] ，其中 % 为取余运算。

    示例 1：
        输入：nums = [3,4,5,1,2]
        输出：true
        解释：[1,2,3,4,5] 为有序的源数组。
        可以轮转 x = 3 个位置，使新数组从值为 3 的元素开始：[3,4,5,1,2] 。
    示例 2：
        输入：nums = [2,1,3,4]
        输出：false
        解释：源数组无法经轮转得到 nums 。
    示例 3：
        输入：nums = [1,2,3]
        输出：true
        解释：[1,2,3] 为有序的源数组。
        可以轮转 x = 0 个位置（即不轮转）得到 nums 。

    提示：
        1 <= nums.length <= 100
        1 <= nums[i] <= 100
    */

    public static void main(String[] args) {
        int[] nums = new int[]{3, 4, 5, 1, 2};
        int[] nums2 = new int[]{2, 1, 3, 4};
        int[] nums3 = new int[]{1, 2, 3};

        System.out.println(check(nums));
        System.out.println(check(nums2));
        System.out.println(check(nums3));
    }

    private static boolean check(int[] nums) {
        boolean flag = false;
        int i = 0;
        while (i < nums.length - 1) {
            if (nums[i] <= nums[i + 1]) {
                i++;
            } else {
                if (!flag) {
                    i++;
                    flag = true;
                } else {
                    return false;
                }
            }
        }
        if (flag) {
            return nums[0] >= nums[nums.length - 1];
        }
        return true;

    }
}
