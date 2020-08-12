package com.wa.leetcode;

public class ProductExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] left = new int[len];
        int[] right = new int[len];
        int[] res = new int[len];

        left[0] = 1;
        right[len - 1] = 1;
        for (int i = 1; i < len; i++)
            left[i] = nums[i - 1] * left[i - 1];
        for (int i = len - 2; i >= 0; i--)
            right[i] = nums[i + 1] * right[i + 1];

        for (int i = 0; i < len; i++)
            res[i] = left[i] * right[i];
        return res;
    }

    public int[] productExceptSelf2(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];

        res[0] = 1;
        for (int i = 1; i < len; i++)
            res[i] = nums[i - 1] * res[i - 1];

        int tmp = 1;
        for (int i = len - 1; i >= 0; i--){
            res[i] *= tmp;
            tmp *= nums[i];
        }
        return res;
    }
}
