package com.wa.leetcode;

import java.util.Arrays;

/**
 * MissingRolls
 *
 * @author: wuao
 * @time: 2022/3/27 9:25
 **/
public class MissingRolls {

    public static void main(String[] args) {
        int[] rolls = new int[]{3, 2, 4, 3};
        int mean = 4, n = 2;

        int[] rolls2 = new int[]{1, 5, 6};
        int mean2 = 3, n2 = 4;

        int[] rolls3 = new int[]{1, 2, 3, 4};
        int mean3 = 6, n3 = 4;

        int[] rolls4 = new int[]{1};
        int mean4 = 3, n4 = 1;

        System.out.println(Arrays.toString(missingRolls(rolls, mean, n)));
        System.out.println(Arrays.toString(missingRolls(rolls2, mean2, n2)));
        System.out.println(Arrays.toString(missingRolls(rolls3, mean3, n3)));
        System.out.println(Arrays.toString(missingRolls(rolls4, mean4, n4)));
    }

    private static int[] missingRolls(int[] rolls, int mean, int n) {
        int sum = mean * (n + rolls.length);
        for (int roll : rolls) {
            sum -= roll;
        }

        if (sum < n || sum > 6 * n) {
            return new int[]{};
        }

        int[] res = new int[n];
        Arrays.fill(res, 1);
        sum -= n;
        for (int i = 0; i < n && sum > 0; i++) {
            int tmp = Math.min(sum, 5);
            res[i] += tmp;
            sum -= tmp;
        }

        return res;
    }
}
