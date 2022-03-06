package com.wa.leetcode;

/**
 * SubSort
 *
 * @author: wuao
 * @time: 2022/2/14 20:11
 **/
public class SubSort {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};

        int[] res = subSort2(nums);
        System.out.println(res[0]);
        System.out.println(res[1]);
    }

    public static int[] subSort(int[] array) {
        int[] res = new int[]{-1, -1};
        int n = array.length;

        if (n == 0)
            return res;

        int min = array[n - 1];
        for (int i = n - 1; i >= 0; i--) {
            if (array[i] <= min) {
                min = array[i];
            } else {
                res[0] = i;
            }
        }

        int max = array[0];
        for (int i = 0; i < n; i++) {
            if (array[i] >= max) {
                max = array[i];
            } else {
                res[1] = i;
            }
        }

        return res;
    }

    public static int[] subSort2(int[] array) {
        int[] res = new int[]{-1, -1};
        int n = array.length;

        if (n == 0)
            return res;

        int min = array[n - 1];
        int max = array[0];
        for (int i = 0; i < n; i++) {
            if (array[i] >= max) {
                max = array[i];
            } else {
                res[1] = i;
            }

            if (array[n - 1 - i] <= min) {
                min = array[n - 1 - i];
            } else {
                res[0] = n - 1 - i;
            }
        }
        return res;
    }
}



