package com.wa.leetcode;

/**
 * NthMagicalNumber
 * https://leetcode.cn/problems/nth-magical-number/
 * 878. 第 N 个神奇数字
 * 2022/11/22 2:54 下午
 *
 * @author wuao
 */
public class NthMagicalNumber {

    /*
    一个正整数如果能被 a 或 b 整除，那么它是神奇的。
    给定三个整数 n , a , b ，返回第 n 个神奇的数字。因为答案可能很大，所以返回答案 对 10^9 + 7 取模 后的值。

    示例 1：
        输入：n = 1, a = 2, b = 3
        输出：2
    示例 2：
        输入：n = 4, a = 2, b = 3
        输出：6

    提示：
        1 <= n <= 109
        2 <= a, b <= 4 * 10^4
    */

    public static void main(String[] args) {
        int n = 1, a = 2, b = 3;
        int n2 = 4, a2 = 2, b2 = 3;
        int n3 = 1000000000, a3 = 40000, b3 = 40000;
        int n4 = 1000000000, a4 = 39999, b4 = 40000;

        System.out.println(nthMagicalNumber2(n, a, b)); // 2
        System.out.println(nthMagicalNumber2(n2, a2, b2)); // 6
        System.out.println(nthMagicalNumber2(n3, a3, b3)); // 999720007
        System.out.println(nthMagicalNumber2(n4, a4, b4)); // 999860007
    }

    // 超时
    private static int nthMagicalNumber(int n, int a, int b) {
        long ac = 1, bc = 1, mod = (long) 1e9 + 7;
        while (n > 0) {
            n--;
            if (a * ac < b * bc) {
                if (n == 0) {
                    return (int) (((long) a * ac) % mod);
                }
                ac++;
            } else if (a * ac > b * bc) {
                if (n == 0) {
                    return (int) (((long) b * bc) % mod);
                }
                bc++;
            } else {
                if (n == 0) {
                    return (int) (((long) a * ac) % mod);
                }
                ac++;
                bc++;
            }
        }
        return 0;
    }

    private static int nthMagicalNumber2(int n, int a, int b) {
        // 令f(x)表示小于等于x的「神奇数字」的个数
        // f(x) = Math.floor(x/a) + Math.floor(x/b) - Math.floor(x/lcm(a,b)), 其中lcm表示最小公倍数
        // 通过二分查找，找到第n个神奇数字
        long left = Math.min(a, b), right = (long) n * Math.min(a, b);
        int c = lcm(a, b);
        while (left < right) {
            long mid = (left + right) / 2;
            long count = mid / a + mid / b - mid / c;
            if (count >= n) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return (int) ((right) % (1e9 + 7));
    }

    private static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
