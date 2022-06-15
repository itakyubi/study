package com.wa.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * SmallestDistancePair
 * https://leetcode.cn/problems/find-k-th-smallest-pair-distance/
 * 719. 找出第 K 小的数对距离
 * 2022-06-15 17:01
 *
 * @author wuao
 */
public class SmallestDistancePair {

    /*
    数对 (a,b) 由整数 a 和 b 组成，其数对距离定义为 a 和 b 的绝对差值。
    给你一个整数数组 nums 和一个整数 k ，数对由 nums[i] 和 nums[j] 组成且满足 0 <= i < j < nums.length 。返回 所有数对距离中 第 k 小的数对距离。

    示例 1：
    输入：nums = [1,3,1], k = 1
    输出：0
    解释：数对和对应的距离如下：
            (1,3) -> 2
            (1,1) -> 0
            (3,1) -> 2
    距离第 1 小的数对是 (1,1) ，距离为 0 。

    示例 2：
    输入：nums = [1,1,1], k = 2
    输出：0

    示例 3：
    输入：nums = [1,6,1], k = 3
    输出：5
             
    提示：
        n == nums.length
        2 <= n <= 104
        0 <= nums[i] <= 106
        1 <= k <= n * (n - 1) / 2
    */

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 1};
        int k = 1;

        int[] nums2 = new int[]{1, 1, 1};
        int k2 = 2;

        int[] nums3 = new int[]{1, 6, 1};
        int k3 = 3;

        System.out.println(smallestDistancePair3(nums, k));
        System.out.println(smallestDistancePair3(nums2, k2));
        System.out.println(smallestDistancePair3(nums3, k3));
    }

    private static int smallestDistancePair3(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int left = 0, right = nums[n - 1] - nums[0];
        int res = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            int i = 0, count = 0;
            for (int j = 0; j < n; j++) {
                while (nums[j] - nums[i] > mid) {
                    i++;
                }
                count += j - i;
            }

            if (count >= k) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    // 超出时间限制
    private static int smallestDistancePair2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int diff = Math.abs(nums[i] - nums[j]);
                map.put(diff, map.getOrDefault(diff, 0) + 1);
            }
        }
        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list);

        int count = 0;
        for (Integer integer : list) {
            count += map.get(integer);
            if (count >= k) {
                return integer;
            }
        }
        return -1;
    }

    // 超出内存限制
    private static int smallestDistancePair(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, Comparator.comparingInt(a -> a));

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                minHeap.offer(Math.abs(nums[i] - nums[j]));
            }
        }

        for (int i = 0; i < k - 1; i++) {
            minHeap.poll();
        }

        return minHeap.peek();
    }
}
