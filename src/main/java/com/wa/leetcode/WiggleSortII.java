package com.wa.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * WiggleSortII
 * 2020-11-09 17:13
 *
 * @author wuao
 */
public class WiggleSortII {

    public static void main(String[] args) {
        int[] nums = new int[]{5, 3, 1, 2, 6, 7, 8, 5, 5};
        wiggleSort(nums);
        for (int num : nums) {
            System.out.print(num + ",");
        }
    }

    public static void wiggleSort(int[] nums) {
        List<Integer> front = new ArrayList<>();
        List<Integer> end = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i < nums.length / 2) {
                front.add(nums[i]);
            } else {
                end.add(nums[i]);
            }
        }

        int i = front.size() - 1, j = end.size() - 1, k = 0;
        while (k < nums.length) {
            if (j >= 0) {
                nums[k++] = end.get(j--);
            }
            if (i >= 0) {
                nums[k++] = front.get(i--);
            }
        }
    }

}
