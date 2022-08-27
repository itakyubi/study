package com.wa.leetcode;

/**
 * LongestPalindrome
 * https://leetcode.cn/problems/longest-palindromic-substring/
 * 5. 最长回文子串
 * 2022/8/27 4:49 下午
 *
 * @author wuao
 */
public class LongestPalindrome {

    /*
    给你一个字符串 s，找到 s 中最长的回文子串。

    示例 1：
        输入：s = "babad"
        输出："bab"
        解释："aba" 同样是符合题意的答案。
    示例 2：
        输入：s = "cbbd"
        输出："bb"

    提示：
        1 <= s.length <= 1000
        s 仅由数字和英文字母组成
    */

    public static void main(String[] args) {
        String s = "babad";
        String s2 = "cbbd";

        System.out.println(longestPalindrome(s));
        System.out.println(longestPalindrome(s2));
    }

    private static String longestPalindrome(String s) {
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len = Math.max(helper(s, i, i), helper(s, i, i + 1));
            if (len > end - start + 1) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int helper(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
