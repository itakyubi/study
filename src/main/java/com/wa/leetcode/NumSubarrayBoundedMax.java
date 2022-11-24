package com.wa.leetcode;

/**
 * NumSubarrayBoundedMax
 * https://leetcode.cn/problems/number-of-subarrays-with-bounded-maximum/
 * 795. 区间子数组个数
 * 2022/11/24 5:04 下午
 *
 * @author wuao
 */
public class NumSubarrayBoundedMax {

    /*
    给你一个整数数组 nums 和两个整数：left 及 right 。找出 nums 中连续、非空且其中最大元素在范围 [left, right] 内的子数组，并返回满足条件的子数组的个数。
    生成的测试用例保证结果符合 32-bit 整数范围。

    示例 1：
        输入：nums = [2,1,4,3], left = 2, right = 3
        输出：3
        解释：满足条件的三个子数组：[2], [2, 1], [3]
    示例 2：
        输入：nums = [2,9,2,5,6], left = 2, right = 8
        输出：7

    提示：
        1 <= nums.length <= 10^5
        0 <= nums[i] <= 10^9
        0 <= left <= right <= 10^9
    */

    public static void main(String[] args) {
        int[] nums = new int[]{2, 1, 4, 3};
        int left = 2, right = 3;

        int[] nums2 = new int[]{2, 9, 2, 5, 6};
        int left2 = 2, right2 = 8;

        int[] nums3 = new int[]{73, 55, 36, 5, 55, 14, 9, 7, 72, 52};
        int left3 = 22, right3 = 69;

        System.out.println(numSubarrayBoundedMax(nums, left, right));
        System.out.println(numSubarrayBoundedMax(nums2, left2, right2));
        System.out.println(numSubarrayBoundedMax(nums3, left3, right3));
    }

    private static int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int n = nums.length, res = 0;
        // j表示i左边最靠近i，且满足left<=nums[j]<=right的下标
        // k表示i左边最靠近i，且满足nums[k]>right的下标
        // 则j-k，就是以nums[i]为右端，左端下标可取的数量
        int j = -1, k = -1;
        // 以nums[i]为右端，找左端的下标取值范围
        for (int i = 0; i < n; i++) {
            if (nums[i] >= left && nums[i] <= right) {
                j = i;
            }
            if (nums[i] > right) {
                k = i;
            }
            if (j > k) {
                res += j - k;
            }
        }
        return res;
    }
}
