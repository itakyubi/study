package com.wa.leetcode;

import java.util.Arrays;

/**
 * HeightChecker
 *
 * @author: wuao
 * @time: 2022/6/13 20:49
 **/
public class HeightChecker {

    public static void main(String[] args) {
        int[] heights = new int[]{1, 1, 4, 2, 1, 3};
        int[] heights2 = new int[]{5, 1, 2, 3, 4};
        int[] heights3 = new int[]{1, 2, 3, 4, 5};

        System.out.println(heightChecker(heights));
        System.out.println(heightChecker(heights2));
        System.out.println(heightChecker(heights3));
    }


    public static int heightChecker(int[] heights) {
        int[] tmp = heights.clone();
        Arrays.sort(tmp);

        int count = 0;
        for (int i = 0; i < heights.length; i++) {
            if (tmp[i] != heights[i]) {
                count++;
            }
        }

        return count;
    }

    public static int heightChecker2(int[] heights) {
        int[] arr = new int[101];
        for (int height : heights) {
            arr[height]++;
        }
        int count = 0;
        for (int i = 0, j = 0; i < arr.length; i++) {
            while (arr[i] > 0) {
                if (heights[j] != i) {
                    count++;
                }
                j++;
                arr[i]--;
            }
        }
        return count;
    }
}
