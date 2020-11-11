package com.wa.utils;

/**
 * Utils
 * 2020-11-11 17:10
 *
 * @author wuao
 */
public class Utils {

    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
