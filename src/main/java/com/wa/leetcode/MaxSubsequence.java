package com.wa.leetcode;

import cn.hutool.core.lang.Pair;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * MaxSubsequence
 * https://leetcode.cn/problems/find-subsequence-of-length-k-with-the-largest-sum/
 * 2099. 找到和最大的长度为 K 的子序列
 * 2023/7/7 9:13 AM
 *
 * @author wuao
 */
public class MaxSubsequence {

    /*
    给你一个整数数组nums和一个整数k。你需要找到nums中长度为 k的 子序列，且这个子序列的和最大。
    请你返回 任意 一个长度为k的整数子序列。
    子序列定义为从一个数组里删除一些元素后，不改变剩下元素的顺序得到的数组。

    示例 1：
        输入：nums = [2,1,3,3], k = 2
        输出：[3,3]
        解释：
        子序列有最大和：3 + 3 = 6 。
    示例 2：
        输入：nums = [-1,-2,3,4], k = 3
        输出：[-1,3,4]
        解释：
        子序列有最大和：-1 + 3 + 4 = 6 。
    示例 3：
        输入：nums = [3,4,3,3], k = 2
        输出：[3,4]
        解释：
        子序列有最大和：3 + 4 = 7 。
        另一个可行的子序列为 [4, 3] 。

    提示：
        1 <= nums.length <= 1000
        -105<= nums[i] <= 105
        1 <= k <= nums.length
    */

    public static void main(String[] args) {
        int[] nums = {2, 1, 3, 3};
        int k = 2;

        int[] nums2 = {-1, -2, 3, 4};
        int k2 = 3;

        int[] nums3 = {3, 4, 3, 3};
        int k3 = 2;

        System.out.println(Arrays.toString(maxSubsequence(nums, k)));
        System.out.println(Arrays.toString(maxSubsequence(nums2, k2)));
        System.out.println(Arrays.toString(maxSubsequence(nums3, k3)));
    }

    private static int[] maxSubsequence(int[] nums, int k) {
        // 找到最大的k个数
        PriorityQueue<Pair<Integer, Integer>> priorityQueue = new PriorityQueue<>((o1, o2) -> o2.getKey() - o1.getKey());
        for (int i = 0; i < nums.length; i++) {
            priorityQueue.offer(new Pair<>(nums[i], i));
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < k; i++) {
            set.add(priorityQueue.poll().getValue());
        }

        int[] res = new int[k];
        for (int i = 0, j = 0; i < nums.length; i++) {
            if (set.contains(i)) {
                res[j] = nums[i];
                j++;
            }
        }
        return res;
    }
}
