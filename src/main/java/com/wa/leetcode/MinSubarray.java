package com.wa.leetcode;

/**
 * MinSubarray
 * https://leetcode.cn/problems/make-sum-divisible-by-p/
 * 1590. 使数组和能被 P 整除
 * 2023/3/10 9:09 上午
 *
 * @author wuao
 */
public class MinSubarray {

    /*
    给你一个正整数数组nums，请你移除 最短子数组（可以为 空），使得剩余元素的 和能被 p整除。 不允许将整个数组都移除。
    请你返回你需要移除的最短子数组的长度，如果无法满足题目要求，返回 -1。
    子数组定义为原数组中连续的一组元素。

    示例 1：
        输入：nums = [3,1,4,2], p = 6
        输出：1
        解释：nums 中元素和为 10，不能被 p 整除。我们可以移除子数组 [4] ，剩余元素的和为 6 。
    示例 2：
        输入：nums = [6,3,5,2], p = 9
        输出：2
        解释：我们无法移除任何一个元素使得和被 9 整除，最优方案是移除子数组 [5,2] ，剩余元素为 [6,3]，和为 9 。
    示例3：
        输入：nums = [1,2,3], p = 3
        输出：0
        解释：和恰好为 6 ，已经能被 3 整除了。所以我们不需要移除任何元素。
    示例 4：
        输入：nums = [1,2,3], p = 7
        输出：-1
        解释：没有任何方案使得移除子数组后剩余元素的和被 7 整除。
    示例 5：
        输入：nums = [1000000000,1000000000,1000000000], p = 3
        输出：0

    提示：
        1 <= nums.length <= 105
        1 <= nums[i] <= 109
        1 <= p <= 109
    */

    public static void main(String[] args) {
        int[] nums = new int[]{3, 1, 4, 2};
        int p = 6;

        int[] nums2 = new int[]{6, 3, 5, 2};
        int p2 = 9;

        int[] nums3 = new int[]{1, 2, 3};
        int p3 = 3;

        int[] nums4 = new int[]{1, 2, 3};
        int p4 = 7;

        int[] nums5 = new int[]{1000000000, 1000000000, 1000000000};
        int p5 = 3;

        System.out.println(minSubarray(nums, p));
        System.out.println(minSubarray(nums2, p2));
        System.out.println(minSubarray(nums3, p3));
        System.out.println(minSubarray(nums4, p4));
        System.out.println(minSubarray(nums5, p5));
    }

    private static int minSubarray(int[] nums, int p) {
        // 滑动窗口 or 前缀和
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % p == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE, l = 0, r = 0;
        long tmp = 0;
        while (l < nums.length && r < nums.length) {
            if ((sum - tmp) % p == 0) {
                min = Math.min(min, r - l);
                tmp -= nums[l];
                l++;
            } else {
                tmp += nums[r];
                r++;
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
