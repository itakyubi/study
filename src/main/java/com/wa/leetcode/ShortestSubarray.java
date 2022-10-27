package com.wa.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * ShortestSubarray
 * https://leetcode.cn/problems/shortest-subarray-with-sum-at-least-k/
 * 862. 和至少为 K 的最短子数组
 * 2022/10/26 6:50 下午
 *
 * @author wuao
 */
public class ShortestSubarray {
    /*
    给你一个整数数组 nums 和一个整数 k ，找出 nums 中和至少为 k 的 最短非空子数组 ，并返回该子数组的长度。如果不存在这样的 子数组 ，返回 -1 。
    子数组 是数组中 连续 的一部分。

    示例 1：
        输入：nums = [1], k = 1
        输出：1
    示例 2：
        输入：nums = [1,2], k = 4
        输出：-1
    示例 3：
        输入：nums = [2,-1,2], k = 3
        输出：3

    提示：
        1 <= nums.length <= 105
        -105 <= nums[i] <= 105
        1 <= k <= 109
    */

    public static void main(String[] args) {
        int[] nums = new int[]{1};
        int k = 1;
        int[] nums2 = new int[]{1, 2};
        int k2 = 4;
        int[] nums3 = new int[]{2, -1, 2};
        int k3 = 3;

        System.out.println(shortestSubarray(nums, k));
        System.out.println(shortestSubarray(nums2, k2));
        System.out.println(shortestSubarray(nums3, k3));
    }

    private static int shortestSubarray2(int[] nums, int k) {
        int n = nums.length;
        long[] preSums = new long[n + 1]; // 前缀和
        for (int i = 0; i < n; i++) {
            preSums[i + 1] = preSums[i] + nums[i];
        }

        int res = n + 1;
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i <= n; i++) {
            long curSum = preSums[i];
            while (!deque.isEmpty() && curSum - preSums[deque.peekFirst()] >= k) {
                res = Math.min(res, i - deque.pollFirst());
            }
            while (!deque.isEmpty() && curSum <= preSums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        return res < n + 1 ? res : -1;
    }

    private static int shortestSubarray(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= k) {
                    min = Math.min(min, j - i + 1);
                    break;
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
