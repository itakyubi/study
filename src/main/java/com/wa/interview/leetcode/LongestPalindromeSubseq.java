package com.wa.interview.leetcode;

/**
 * LongestPalindromeSubseq
 * 2023/4/24 6:50 下午
 *
 * @author wuao
 */
public class LongestPalindromeSubseq {

    public static void main(String[] args) {
        String s = "bbbab";

        System.out.println(longestPalindromeSubseq(s));
    }


    private static int longestPalindromeSubseq(String s) {
        // dp[i][j]=k，代表s[i:j]范围内的最长回文子序列的长度为k
        // 当s[i]==s[j], dp[i][j] = dp[i+1,j-1]+2
        // 当s[i]!=s[j], dp[i][j] = max(dp[i+1,j], dp[i,j-1])
        // 画个矩阵表示就是从左下角网右上角计算
        // 某个点的值依赖其下方和左方的两个点
        // 从最下边一行开始往右遍历，遍历完一行遍历上一行
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
