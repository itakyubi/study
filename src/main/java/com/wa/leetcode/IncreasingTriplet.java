package com.wa.leetcode;

/**
 * IncreasingTriplet
 * 2020-11-16 17:03
 *
 * @author wuao
 */
public class IncreasingTriplet {
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3)
            return false;

        int small = Integer.MAX_VALUE, mid = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= small) {
                small = num;
            } else if (num <= mid) {
                mid = num;
            } else {
                return true;
            }
        }
        return false;
    }
}
