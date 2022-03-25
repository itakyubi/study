package com.wa.leetcode;

/**
 * TrailingZeroes
 * https://leetcode-cn.com/problems/factorial-trailing-zeroes/
 * 2022-03-25 08:59
 *
 * @author wuao
 */
public class TrailingZeroes {

    public static void main(String[] args) {

        /*BigInteger num = BigInteger.valueOf(1);
        for (int i = 1; i <= 30; i++) {
            num = num.multiply(BigInteger.valueOf(i));
            System.out.println(i + ":" + num.toString());
        }*/


        int n = 3;
        int n2 = 5;
        int n3 = 0;
        int n4 = 30;

        System.out.println(trailingZeroes2(n));
        System.out.println(trailingZeroes2(n2));
        System.out.println(trailingZeroes2(n3));
        System.out.println(trailingZeroes2(n4));
    }

    private static int trailingZeroes2(int n) {
        int count = 0;
        while (n >= 5) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }

    private static int trailingZeroes(int n) {
        int count5 = 0;
        for (int i = 5; i <= n; i += 5) {
            int tmp = i;
            while (tmp % 5 == 0) {
                count5++;
                tmp /= 5;
            }
        }
        return count5;
    }
}
