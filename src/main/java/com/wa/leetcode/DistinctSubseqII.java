package com.wa.leetcode;

/**
 * DistinctSubseqII
 * https://leetcode.cn/problems/distinct-subsequences-ii/
 * 940. 不同的子序列 II
 * 2022/10/14 4:02 下午
 *
 * @author wuao
 */
public class DistinctSubseqII {

    /*
    给定一个字符串 s，计算 s 的 不同非空子序列 的个数。因为结果可能很大，所以返回答案需要对 10^9 + 7 取余 。
    字符串的 子序列 是经由原字符串删除一些（也可能不删除）字符但不改变剩余字符相对位置的一个新字符串。
    例如，"ace" 是 "abcde" 的一个子序列，但 "aec" 不是。

    示例 1：
        输入：s = "abc"
        输出：7
        解释：7 个不同的子序列分别是 "a", "b", "c", "ab", "ac", "bc", 以及 "abc"。
    示例 2：
        输入：s = "aba"
        输出：6
        解释：6 个不同的子序列分别是 "a", "b", "ab", "ba", "aa" 以及 "aba"。
    示例 3：
        输入：s = "aaa"
        输出：3
        解释：3 个不同的子序列分别是 "a", "aa" 以及 "aaa"。
            
    提示：
        1 <= s.length <= 2000
        s 仅由小写英文字母组成
    */

    public static void main(String[] args) {
        String s = "abc";
        String s2 = "aba";
        String s3 = "aaa";

        System.out.println(distinctSubseqII(s));
        System.out.println(distinctSubseqII(s2));
        System.out.println(distinctSubseqII(s3));
    }

    private static int distinctSubseqII(String s) {
        // dp[i]代表前i个字符的不同非空子序列的个数
        // dp[i] = dp[i-1] + newSubSeqCount - repeatSubsSeqCount
        // 前i-1个字符的不同非空子序列的个数 + 加上第i个字符后新增的非空子序列个数 - 加上第i个字符后重复的非空子序列个数
        // newSubSeqCount = dp[i-1]，在前i-1字符组成的非空子序列后加上第i个字符，即构成了新增的子序列
        // repeatSubsSeqCount = 上一次添加字符i时，新增的子序列个数
        // 以abcb为例
        // 未遍历，新增子序列""
        // 遍历到a，新增子序列a，重复子序列没有
        // 遍历到b，新增子序列b、ab，重复子序列没有
        // 遍历到c，新增子序列c、ac、bc、abc，重复子序列没有
        // 遍历到b，新增子序列b、ab、bb、abb、cb、acb、bcb、abcb，重复子序列b、ab（恰好为遍历到第一个b时新增的子序列个数）
        int[] dp = new int[s.length() + 1];
        int[] count = new int[26]; // 记录上一次遍历到字母时新增的子序列个数
        int mod = (int) 1e9 + 7;

        dp[0] = 1;
        for (int i = 0; i < s.length(); i++) {
            int prev = dp[i];
            dp[i + 1] = (((dp[i] + prev) % mod) - (count[s.charAt(i) - 'a'] % mod) + mod) % mod;
            count[s.charAt(i) - 'a'] = prev;
        }
        return dp[s.length()] - 1;
    }
}
