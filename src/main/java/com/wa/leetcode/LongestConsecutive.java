package com.wa.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * LongestConsecutive
 * https://leetcode.cn/problems/longest-consecutive-sequence/
 * 128. 最长连续序列
 *
 * @author: wuao
 * @time: 2022/10/30 17:03
 **/
public class LongestConsecutive {

    /*
    给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
    请你设计并实现时间复杂度为 O(n) 的算法解决此问题。

    示例 1：
        输入：nums = [100,4,200,1,3,2]
        输出：4
        解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
    示例 2：
        输入：nums = [0,3,7,2,5,8,4,6,0,1]
        输出：9
             
    提示：
        0 <= nums.length <= 105
        -109 <= nums[i] <= 109
    */

    public static void main(String[] args) {
        int[] nums = new int[]{0, 3, 7, 2, 5, 8, 4, 6, 4, 0, 1};
        System.out.println(longestConsecutive(nums));
    }

    private static int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int num : nums) {
            if (!map.containsKey(num)){
                int left = map.getOrDefault(num - 1, 0);
                int right = map.getOrDefault(num + 1, 0);
                int length = left + right + 1;
                map.put(num, length);
                map.put(num - left, length);
                map.put(num + right, length);
                max = Math.max(max, length);
            }
        }
        return max;
    }
}
