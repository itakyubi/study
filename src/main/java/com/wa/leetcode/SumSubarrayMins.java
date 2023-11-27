package com.wa.leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * SumSubarrayMins
 * https://leetcode.cn/problems/sum-of-subarray-minimums
 * 907. 子数组的最小值之和
 *
 * @Date: 2023/11/27 8:34
 * @Author: wuao
 */
public class SumSubarrayMins {

    /*
    给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。
    由于答案可能很大，因此 返回答案模 10^9 + 7 。

    示例 1：
        输入：arr = [3,1,2,4]
        输出：17
        解释：
        子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
        最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
    示例 2：
        输入：arr = [11,81,94,43,3]
        输出：444

    提示：
        1 <= arr.length <= 3 * 10^4
        1 <= arr[i] <= 3 * 10^4
    */

    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 4};
        int[] arr2 = {11, 81, 94, 43, 3};
        int[] arr3 = {117, 1315, 1336, 4213, 5634, 6288, 7640, 8533, 9688, 10186, 10593, 11896, 13673,
                14707, 15484, 17429, 19639, 20416, 21375, 23601, 25800, 26485, 27893, 28026, 28695,
                29121, 28642, 28023, 27642, 26324, 23844, 22069, 21124, 20181, 18957, 15736, 15364,
                13749, 13612, 11062, 10319, 9755, 9367, 7977, 6463, 6049, 4886, 3071, 1331, 865};

        //System.out.println(sumSubarrayMins(arr));
        //System.out.println(sumSubarrayMins(arr2));
        System.out.println(sumSubarrayMins(arr3));
        System.out.println(sumSubarrayMins2(arr3));
    }

    public static int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();
        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                stack.pop();
            }
            left[i] = i - (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }

        stack.clear();
        int[] right = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                stack.pop();
            }
            right[i] = (stack.isEmpty() ? n : stack.peek()) - i;
            stack.push(i);
        }

        long res = 0;
        final int MOD = 1000000007;
        for (int i = 0; i < n; i++) {
            res = (res + (long) left[i] * right[i] * arr[i]) % MOD;
        }
        return (int) res;
    }

    // 超出内存限制
    public static int sumSubarrayMins2(int[] arr) {
        int n = arr.length;
        int[][] mins = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(mins[i], -1);
        }

        long res = 0;
        int mod = 1000000007;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int min = helper(arr, i, j, mins);
                res = ((min % mod) + res % mod) % mod;
            }
        }
        return (int) res;
    }

    private static int helper(int[] arr, int i, int j, int[][] mins) {
        if (mins[i][j] != -1) {
            return mins[i][j];
        }
        if (i == j) {
            mins[i][j] = arr[i];
            return arr[i];
        }

        int mid = (i + j) / 2;
        int leftMin = helper(arr, i, mid, mins);
        int rightMin = helper(arr, mid + 1, j, mins);
        mins[i][j] = Math.min(leftMin, rightMin);

        return mins[i][j];
    }
}
