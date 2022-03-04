package com.wa.leetcode;

/**
 * ThreeConsecutiveOdds
 * https://leetcode-cn.com/problems/three-consecutive-odds/
 * 2022-03-04 09:07
 *
 * @author wuao
 */
public class ThreeConsecutiveOdds {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 6, 4, 1};
        int[] arr2 = new int[]{1, 2, 34, 3, 4, 5, 7, 23, 12};
        int[] arr3 = new int[]{2};

        System.out.println(threeConsecutiveOdds(arr));
        System.out.println(threeConsecutiveOdds(arr2));
        System.out.println(threeConsecutiveOdds(arr3));
    }


    private static boolean threeConsecutiveOdds(int[] arr) {
        int count = 0;
        for (int a : arr) {
            if ((a & 1) == 0) {
                count = 0;
            } else {
                count++;
            }
            if (count == 3)
                return true;
        }
        return false;
    }
}
