package com.wa.leetcode;

/**
 * SecondHighest
 * https://leetcode.cn/problems/second-largest-digit-in-a-string/
 * 1796. 字符串中第二大的数字
 * 2022/12/3 4:56 下午
 *
 * @author wuao
 */
public class SecondHighest {

    /*
    给你一个混合字符串 s ，请你返回 s 中 第二大 的数字，如果不存在第二大的数字，请你返回 -1 。
    混合字符串 由小写英文字母和数字组成。

    示例 1：
        输入：s = "dfa12321afd"
        输出：2
        解释：出现在 s 中的数字包括 [1, 2, 3] 。第二大的数字是 2 。
    示例 2：
        输入：s = "abc1111"
        输出：-1
        解释：出现在 s 中的数字只包含 [1] 。没有第二大的数字。

    提示：
        1 <= s.length <= 500
        s 只包含小写英文字母和（或）数字。
    */

    public static void main(String[] args) {
        String s = "dfa12321afd";
        String s2 = "abc1111";

        System.out.println(secondHighest(s));
        System.out.println(secondHighest(s2));
    }

    private static int secondHighest(String s) {
        int max = -1, max2 = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                int tmp = c - '0';
                if (tmp > max) {
                    max2 = max;
                    max = tmp;
                } else if (tmp != max && tmp > max2) {
                    max2 = tmp;
                }
            }
        }
        return max2;
    }
}
