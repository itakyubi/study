package com.wa.leetcode;

/**
 * MinimumDeletions
 * https://leetcode.cn/problems/minimum-deletions-to-make-string-balanced/
 * 1653. 使字符串平衡的最少删除次数
 * 2023/3/6 1:31 下午
 *
 * @author wuao
 */
public class MinimumDeletions {
    /*
    给你一个字符串s，它仅包含字符'a' 和'b' 。
    你可以删除s中任意数目的字符，使得s 平衡。当不存在下标对(i,j)满足i < j ，且s[i] = 'b' 的同时s[j]= 'a' ，此时认为 s 是 平衡 的。
    请你返回使 s平衡的 最少删除次数。

    示例 1：
        输入：s = "aababbab"
        输出：2
        解释：你可以选择以下任意一种方案：
        下标从 0 开始，删除第 2 和第 6 个字符（"aababbab" -> "aaabbb"），
        下标从 0 开始，删除第 3 和第 6 个字符（"aababbab" -> "aabbbb"）。
    示例 2：
        输入：s = "bbaaaaabb"
        输出：2
        解释：唯一的最优解是删除最前面两个字符。

    提示：
        1 <= s.length <= 105
        s[i]要么是'a' 要么是'b'。
    */

    public static void main(String[] args) {
        String s = "aababbab";
        String s2 = "bbaaaaabb";

        System.out.println(minimumDeletions(s));
        System.out.println(minimumDeletions(s2));
    }

    private static int minimumDeletions(String s) {
        // dp[i] 表示s[0:i]，最小删除次数
        // 如果s[i]=b，则不需要操作，dp[i]=dp[i-1]
        // 如果s[i]=a，有两种操作
        // 一种是保留当前的a，删除a前边的所有b，dp[i] = bCnt;
        // 一种是删除当前的a，dp[i]=dp[i-1]+1
        // 取其中最小的，dp[i]=min(dp[i-1]+1,bCnt)
        int[] dp = new int[s.length()];
        dp[0] = 0;
        int bCnt = s.charAt(0) == 'b' ? 1 : 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                dp[i] = Math.min(dp[i - 1] + 1, bCnt);
            } else {
                dp[i] = dp[i - 1];
                bCnt++;
            }
        }
        return dp[s.length() - 1];
    }
}
