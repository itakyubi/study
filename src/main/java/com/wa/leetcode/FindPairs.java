package com.wa.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * FindPairs
 * https://leetcode.cn/problems/k-diff-pairs-in-an-array/
 * 532. 数组中的 k-diff 数对
 * 2022-06-16 10:47
 *
 * @author wuao
 */
public class FindPairs {

    /*
    给定一个整数数组和一个整数 k，你需要在数组里找到 不同的 k-diff 数对，并返回不同的 k-diff 数对 的数目。
    这里将 k-diff 数对定义为一个整数对 (nums[i], nums[j])，并满足下述全部条件：
            0 <= i < j < nums.length
            |nums[i] - nums[j]| == k
    注意，|lombok.val| 表示 val 的绝对值。

    示例 1：
    输入：nums = [3, 1, 4, 1, 5], k = 2
    输出：2
    解释：数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
    尽管数组中有两个1，但我们只应返回不同的数对的数量。

    示例 2：
    输入：nums = [1, 2, 3, 4, 5], k = 1
    输出：4
    解释：数组中有四个 1-diff 数对, (1, 2), (2, 3), (3, 4) 和 (4, 5)。

    示例 3：
    输入：nums = [1, 3, 1, 5, 4], k = 0
    输出：1
    解释：数组中只有一个 0-diff 数对，(1, 1)。

    提示：
        1 <= nums.length <= 104
        -107 <= nums[i] <= 107
        0 <= k <= 107
    */

    public static void main(String[] args) {
        int[] nums = new int[]{3, 1, 4, 1, 5};
        int k = 2;

        int[] nums2 = new int[]{1, 2, 3, 4, 5};
        int k2 = 1;

        int[] nums3 = new int[]{1, 3, 1, 5, 4};
        int k3 = 0;

        System.out.println(findPairs2(nums, k));
        System.out.println(findPairs2(nums2, k2));
        System.out.println(findPairs2(nums3, k3));
    }

    private static int findPairs2(int[] nums, int k) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> res = new HashSet<>();
        for (int num : nums) {
            if (visited.contains(num - k)) {
                res.add(num - k);
            }
            if (visited.contains(num + k)) {
                res.add(num);
            }
            visited.add(num);
        }
        return res.size();
    }

    private static int findPairs(int[] nums, int k) {
        int count = 0;
        if (k == 0) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                if (map.containsKey(num)) {
                    if (map.get(num) == 1) {
                        count++;
                    }
                }
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
            return count;
        }

        Map<Integer, Node> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, new Node());
            }
            if (map.get(num).prev == Integer.MIN_VALUE && map.containsKey(num - k)) {
                count++;
                map.get(num).prev = num - k;
                map.get(num - k).next = num;
            }
            if (map.get(num).next == Integer.MAX_VALUE && map.containsKey(num + k)) {
                count++;
                map.get(num).next = num + k;
                map.get(num + k).prev = num;
            }
        }
        return count;
    }

    private static class Node {
        private int prev;
        private int next;

        Node() {
            prev = Integer.MIN_VALUE;
            next = Integer.MAX_VALUE;
        }
    }
}
