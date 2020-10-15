package com.wa.leetcode;

/**
 * SuperUglyNumber
 * 2020-10-15 18:35
 *
 * @author wuao
 */
public class SuperUglyNumber {

    public static void main(String[] args) {
        System.out.println(nthSuperUglyNumber(12, new int[]{2, 7, 13, 19}));
    }

    public static int nthSuperUglyNumber(int n, int[] primes) {
        int[] nums = new int[n];
        nums[0] = 1;

        int[] idxs = new int[primes.length];
        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < idxs.length; j++) {
                min = Math.min(min, nums[idxs[j]] * primes[j]);
            }

            nums[i] = min;

            for (int j = 0; j < idxs.length; j++) {
                if (min == nums[idxs[j]] * primes[j]) {
                    idxs[j]++;
                }
            }
        }

        return nums[n - 1];
    }
}
