package com.wa.leetcode;

/**
 * SuperPow
 * 2020-12-02 18:34
 *
 * @author wuao
 */
public class SuperPow {

    private static int mod = 1337;

    public static void main(String[] args) {
        System.out.println(superPow(2, new int[]{3}));
        System.out.println(superPow(2, new int[]{1, 0}));
        System.out.println(superPow(1, new int[]{4, 3, 3, 8, 5, 2}));
        System.out.println(superPow(2147483647, new int[]{2, 0, 0}));
    }

    public static int superPow(int a, int[] b) {
        return dfs(a, b, b.length - 1);
    }

    private static int dfs(int a, int[] b, int i) {
        if (i < 0)
            return 1;
        int pow1 = pow(a, b[i--]);
        int pow2 = pow(dfs(a, b, i), 10);
        return (pow1 * pow2) % mod;
    }

    private static int pow(int a, int b) {
        if (b <= 0)
            return 1;
        a %= mod;
        if ((b & 1) == 1) {
            return (a * pow(a, b - 1)) % mod;
        } else {
            int tmp = pow(a, b >> 1);
            return (tmp * tmp) % mod;
        }
    }
}
