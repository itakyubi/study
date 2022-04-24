package com.wa.leetcode;

/**
 * BinaryGap
 * 2022-04-24 09:33
 *
 * @author wuao
 */
public class BinaryGap {

    public static void main(String[] args) {
        int n = 22;
        int n2 = 8;
        int n3 = 5;
        int n4 = 6;
        System.out.println(binaryGap(n));
        System.out.println(binaryGap(n2));
        System.out.println(binaryGap(n3));
        System.out.println(binaryGap(n4));
    }

    private static int binaryGap2(int n) {
        int last = -1, max = 0;
        for (int i = 0; n > 0; i++) {
            if ((n & 1) == 1) {
                if (last != -1) {
                    max = Math.max(max, i - last);
                }
                last = i;
            }
            n >>= 1;
        }
        return max;
    }

    private static int binaryGap(int n) {
        int max = 0;
        while (n > 0) {
            while (n > 0 && (n & 1) != 1) {
                n = n >> 1;
            }
            n = n >> 1;
            int count = 0;
            while (n > 0 && (n & 1) != 1) {
                count++;
                n = n >> 1;
            }

            if ((n & 1) == 1) {
                max = Math.max(max, count + 1);
            }
        }
        return max;
    }
}
