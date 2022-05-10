package com.wa.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * FindLHS
 * 2022-05-10 14:11
 *
 * @author wuao
 */
public class FindLHS {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 2, 2, 5, 2, 3, 7};
        int[] nums2 = new int[]{1, 2, 3, 4};
        int[] nums3 = new int[]{1, 1, 1, 1};

        System.out.println(findLHS(nums)); // 5
        System.out.println(findLHS(nums2)); // 2
        System.out.println(findLHS(nums3)); // 0
    }

    private static int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int max = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (map.containsKey(entry.getKey() + 1)) {
                max = Math.max(max, map.get(entry.getKey() + 1) + entry.getValue());
            }
        }
        return max;
    }
}
