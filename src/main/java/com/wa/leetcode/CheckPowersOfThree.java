package com.wa.leetcode;

/**
 * CheckPowersOfThree
 * https://leetcode.cn/problems/check-if-number-is-a-sum-of-powers-of-three/
 * 1780. 判断一个数字是否可以表示成三的幂的和
 * 2022/12/9 3:49 下午
 *
 * @author wuao
 */
public class CheckPowersOfThree {

    /*
    给你一个整数n，如果你可以将n表示成若干个不同的三的幂之和，请你返回true，否则请返回 false。
    对于一个整数 y，如果存在整数 x满足 y == 3^x，我们称这个整数 y是三的幂。

    示例 1：
        输入：n = 12
        输出：true
        解释：12 = 3^1 + 3^2
    示例 2：
        输入：n = 91
        输出：true
        解释：91 = 3^0 + 3^2 + 3^4
    示例 3：
        输入：n = 21
        输出：false

    提示：
        1 <= n <= 10^7
    */

    public static void main(String[] args) {
        System.out.println(checkPowersOfThree2(12));
        System.out.println(checkPowersOfThree2(91));
        System.out.println(checkPowersOfThree2(21));
    }

    // 辗转相除法，转换成3进制，且每位三进制的系数只能是0和1，一旦出现2证明无法转换
    private static boolean checkPowersOfThree2(int n) {
        while (n > 0) {
            if (n % 3 == 2) {
                return false;
            }
            n /= 3;
        }
        return true;
    }

    private static boolean checkPowersOfThree(int n) {
        return helper(n, Integer.MAX_VALUE);
    }

    private static boolean helper(int n, int powerLimit) {
        if (n == 0 || (powerLimit == 0 && n == 1)) {
            return true;
        }
        if (powerLimit < 0 && n > 0) {
            return false;
        }

        int power = 0, sub = 1, m = n;
        while (m >= 3 && power < powerLimit) {
            power++;
            m /= 3;
            sub *= 3;
        }
        return helper(n - sub, power - 1);
    }
}
