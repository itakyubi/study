package com.wa.interview;

/**
 * MaxLengthOfCommonSubstring
 * 两个字符串最长公共子串的长度
 * 2021-04-25 18:52
 *
 * @author wuao
 */
public class MaxLengthOfCommonSubstring {

    public static void main(String[] args) {
        System.out.println(getMaxLength("abgcde", "abgde"));
        System.out.println(getMaxLength("abcgde", "abgde"));
        System.out.println(getMaxLength("abcgdefes", "abcgdegdefes"));
        System.out.println(getMaxLength("12321", "32147"));
    }

    public static int getMaxLength(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0)
            return 0;

        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        int max = 0;
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = 0;
                }
                max = Math.max(max, dp[i + 1][j + 1]);
            }
        }
        return max;
    }
}
