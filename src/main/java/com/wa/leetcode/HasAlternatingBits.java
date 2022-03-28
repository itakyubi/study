package com.wa.leetcode;

/**
 * HasAlternatingBits
 * https://leetcode-cn.com/problems/binary-number-with-alternating-bits/
 * 2022-03-28 09:14
 *
 * @author wuao
 */
public class HasAlternatingBits {

    public static void main(String[] args) {
        int n = 5;
        int n2 = 7;
        int n3 = 11;

        System.out.println(hasAlternatingBits(n));
        System.out.println(hasAlternatingBits(n2));
        System.out.println(hasAlternatingBits(n3));
    }


    private static boolean hasAlternatingBits(int n) {
        int prev = n & 1;
        n = n >> 1;
        while (n > 0) {
            int cur = n & 1;
            if (cur == prev) {
                return false;
            }
            prev = cur;
            n = n >> 1;
        }
        return true;
    }
}
