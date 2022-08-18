package com.wa.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * MaxEqualFreq
 * https://leetcode.cn/problems/maximum-equal-frequency/
 * 1224. 最大相等频率
 * 2022/8/18 4:04 下午
 *
 * @author wuao
 */
public class MaxEqualFreq {

    /*
    给你一个正整数数组nums，请你帮忙从该数组中找出能满足下面要求的 最长 前缀，并返回该前缀的长度：
    从前缀中 恰好删除一个 元素后，剩下每个数字的出现次数都相同。
    如果删除这个元素后没有剩余元素存在，仍可认为每个数字都具有相同的出现次数（也就是 0 次）。

    示例 1：
        输入：nums = [2,2,1,1,5,3,3,5]
        输出：7
        解释：对于长度为 7 的子数组 [2,2,1,1,5,3,3]，如果我们从中删去 nums[4] = 5，就可以得到 [2,2,1,1,3,3]，里面每个数字都出现了两次。
    示例 2：
        输入：nums = [1,1,1,2,2,2,3,3,3,4,4,4,5]
        输出：13

    提示：
        2 <= nums.length <= 105
        1 <= nums[i] <= 105
    */

    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 1, 1, 5, 3, 3, 5};
        int[] nums2 = new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5};
        int[] nums3 = new int[]{1, 1, 1, 2, 2, 2};
        int[] nums4 = new int[]{1, 2};
        int[] nums5 = new int[]{1, 1};

        System.out.println(maxEqualFreq(nums)); //7
        System.out.println(maxEqualFreq(nums2)); //13
        System.out.println(maxEqualFreq(nums3)); //5
        System.out.println(maxEqualFreq(nums4)); //2
        System.out.println(maxEqualFreq(nums5)); //2
    }

    private static int maxEqualFreq(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(); // key:数组元素，value:出现次数
        Map<Integer, Integer> countMap = new HashMap<>(); // key:元素出现次数，value:元素个数
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int oldCount = map.getOrDefault(nums[i], 0);
            int newCount = oldCount + 1;
            map.put(nums[i], newCount);

            if (oldCount != 0) {
                countMap.put(oldCount, countMap.get(oldCount) - 1);
                if (countMap.get(oldCount) == 0) {
                    countMap.remove(oldCount);
                }
            }
            countMap.put(newCount, countMap.getOrDefault(newCount, 0) + 1);

            if (countMap.size() == 1) {
                if (countMap.containsKey(1)) {
                    res = i + 1;
                    continue;
                }
                for (int key : countMap.keySet()) {
                    if (countMap.get(key) == 1) {
                        res = i + 1;
                        break;
                    }
                }
            } else if (countMap.size() == 2) {
                if (countMap.containsKey(1) && countMap.get(1) == 1) {
                    res = i + 1;
                    continue;
                }
                int small = Integer.MAX_VALUE, big = Integer.MIN_VALUE;
                for (int key : countMap.keySet()) {
                    small = Math.min(small, key);
                    big = Math.max(big, key);
                }
                if (big - small == 1 && countMap.get(big) == 1) {
                    res = i + 1;
                }
            }
        }
        return res;
    }
}
