package com.wa.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TwoSum
 * https://leetcode.cn/problems/two-sum/
 * 1. 两数之和
 * 2023/7/1 5:25 PM
 *
 * @author wuao
 */
public class TwoSum {

    /*
    给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
    你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
    你可以按任意顺序返回答案。

    示例 1：
        输入：nums = [2,7,11,15], target = 9
        输出：[0,1]
        解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
    示例 2：
        输入：nums = [3,2,4], target = 6
        输出：[1,2]
    示例 3：
        输入：nums = [3,3], target = 6
        输出：[0,1]

    提示：
        2 <= nums.length <= 104
        -109 <= nums[i] <= 109
        -109 <= target <= 109
        只会存在一个有效答案

    进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
    */

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        int[] nums2 = {3, 2, 4};
        int target2 = 6;

        int[] nums3 = {3, 3};
        int target3 = 6;

        System.out.println(Arrays.toString(twoSum(nums, target)));
        System.out.println(Arrays.toString(twoSum(nums2, target2)));
        System.out.println(Arrays.toString(twoSum(nums3, target3)));

    }

    private static int[] twoSum(int[] nums, int target) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> idxList = map.getOrDefault(nums[i], new ArrayList<>());
            idxList.add(i);
            map.put(nums[i], idxList);
        }

        for (int i = 0; i < nums.length; i++) {
            int other = target - nums[i];
            if (map.containsKey(other)) {
                List<Integer> idxList = map.get(other);
                if (other == nums[i]) {
                    if (idxList.size() > 1) {
                        return new int[]{idxList.get(0), idxList.get(1)};
                    }
                } else {
                    if (idxList.size() > 0) {
                        return new int[]{i, idxList.get(0)};
                    }
                }
            }
        }
        return new int[]{-1, -1};
    }
}
