package com.wa.leetcode;

/**
 * MaxRotateFunction
 * https://leetcode-cn.com/problems/rotate-function/
 * 2022-04-22 09:07
 *
 * @author wuao
 */
public class MaxRotateFunction {

    public static void main(String[] args) {
        int[] nums = new int[]{4, 3, 2, 6};
        int[] nums2 = new int[]{100};

        System.out.println(maxRotateFunction(nums));
        System.out.println(maxRotateFunction(nums2));
    }

    private static int maxRotateFunction(int[] nums) {
        // F[k+1] - F[k] = sum(nums) - n*nums[n-(k+1)]
        int sum = 0, n = nums.length;
        int[] f = new int[n];
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            f[0] += i * nums[i];
        }

        int max = f[0];
        for (int i = 1; i < n; i++) {
            f[i] = f[i - 1] + sum - n * nums[n - i];
            max = Math.max(max, f[i]);
        }
        return max;
    }
}
