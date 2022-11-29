package com.wa.leetcode;

/**
 * MinOperations
 * https://leetcode.cn/problems/minimum-changes-to-make-alternating-binary-string/
 * 1758. 生成交替二进制字符串的最少操作数
 * 2022/11/29 9:35 上午
 *
 * @author wuao
 */
public class MinOperations {

    /*
    给你一个仅由字符 '0' 和 '1' 组成的字符串 s 。一步操作中，你可以将任一 '0' 变成 '1' ，或者将 '1' 变成 '0' 。
    交替字符串 定义为：如果字符串中不存在相邻两个字符相等的情况，那么该字符串就是交替字符串。例如，字符串 "010" 是交替字符串，而字符串 "0100" 不是。
    返回使 s 变成 交替字符串 所需的 最少 操作数。

    示例 1：
        输入：s = "0100"
        输出：1
        解释：如果将最后一个字符变为 '1' ，s 就变成 "0101" ，即符合交替字符串定义。
    示例 2：
        输入：s = "10"
        输出：0
        解释：s 已经是交替字符串。
    示例 3：
        输入：s = "1111"
        输出：2
        解释：需要 2 步操作得到 "0101" 或 "1010" 。
    */

    public static void main(String[] args) {
        String s = "0100";
        String s2 = "10";
        String s3 = "1111";
        System.out.println(minOperations(s));
        System.out.println(minOperations(s2));
        System.out.println(minOperations(s3));
    }

    private static int minOperations(String s) {
        int cnt1 = 0, cnt2 = 0;
        int tmp1 = 0, tmp2 = 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != '0' + tmp1) {
                cnt1++;
            }
            if (c != '0' + tmp2) {
                cnt2++;
            }
            tmp1 ^= 1;
            tmp2 ^= 1;
        }
        return Math.min(cnt1, cnt2);
    }
}
