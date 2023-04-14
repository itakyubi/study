package com.wa.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * MostFrequentEven
 * https://leetcode.cn/problems/most-frequent-even-element/
 * 2404. 出现最频繁的偶数元素
 * 2023/4/13 8:55 上午
 *
 * @author wuao
 */
public class MostFrequentEven {

    /*
    给你一个整数数组 nums ，返回出现最频繁的偶数元素。
    如果存在多个满足条件的元素，只需要返回 最小 的一个。如果不存在这样的元素，返回 -1 。

    示例 1：
        输入：nums = [0,1,2,2,4,4,1]
        输出：2
        解释：
        数组中的偶数元素为 0、2 和 4 ，在这些元素中，2 和 4 出现次数最多。
        返回最小的那个，即返回 2 。
    示例 2：
        输入：nums = [4,4,4,9,2,4]
        输出：4
        解释：4 是出现最频繁的偶数元素。
    示例 3：
        输入：nums = [29,47,21,41,13,37,25,7]
        输出：-1
        解释：不存在偶数元素。

    提示：
        1 <= nums.length <= 2000
        0 <= nums[i] <= 10^5
    */

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 2, 4, 4, 1};
        int[] nums2 = {4, 4, 4, 9, 2, 4};
        int[] nums3 = {29, 47, 21, 41, 13, 37, 25, 7};

        System.out.println(mostFrequentEven(nums));
        System.out.println(mostFrequentEven(nums2));
        System.out.println(mostFrequentEven(nums3));
    }

    private static int mostFrequentEven(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxCnt = 0, res = -1;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if ((num & 1) == 0) {
                if (map.get(num) > maxCnt || (map.get(num) == maxCnt && num < res)) {
                    res = num;
                    maxCnt = map.get(num);
                }
            }
        }
        return res;
    }
}