package com.wa.leetcode;

public class UglyNumberII {
    public static void main(String[] args) {
        System.out.println(nthUglyNumber(10));
    }


    public static int nthUglyNumber(int n) {
        int[] nums = new int[n];
        nums[0] = 1;

        int idx2 = 0, idx3 = 0, idx5 = 0;
        for (int i = 1; i < n; i++) {
            int a = nums[idx2] * 2;
            int b = nums[idx3] * 3;
            int c = nums[idx5] * 5;

            nums[i] = Math.min(Math.min(a, b), c);

            if (nums[i] == a)
                idx2++;
            if (nums[i] == b)
                idx3++;
            if (nums[i] == c)
                idx5++;
        }

        return nums[n - 1];
    }
}
