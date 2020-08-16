package com.wa.leetcode;

public class SingleNumberIII {
    public int[] singleNumber(int[] nums) {
        int tmp = 0;
        for (int num : nums)
            tmp ^= num;
        int diff = tmp & (-tmp);

        int a = 0;
        for (int num : nums) {
            if ((num & diff) != 0) {
                a ^= num;
            }
        }

        return new int[]{a, tmp ^ a};
    }
}
