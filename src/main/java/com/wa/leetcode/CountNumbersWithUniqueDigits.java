package com.wa.leetcode;

/**
 * CountNumbersWithUniqueDigits
 * 2022-04-11 09:08
 *
 * @author wuao
 */
public class CountNumbersWithUniqueDigits {

    public static void main(String[] args) {
        int n = 2;
        int n2 = 0;

        System.out.println(countNumbersWithUniqueDigits(n));
        System.out.println(countNumbersWithUniqueDigits(n2));
    }

    private static int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 10;
        }
        int res = 10, cur = 9;
        for (int i = 0; i < n - 1; i++) {
            cur *= 9 - i;
            res += cur;
        }
        return res;
    }
}
