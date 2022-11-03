package com.wa.leetcode;

/**
 * MaxRepeating
 * https://leetcode.cn/problems/maximum-repeating-substring/
 * 1668. 最大重复子字符串
 * 2022/11/3 5:02 下午
 *
 * @author wuao
 */
public class MaxRepeating {

    /*
    给你一个字符串 sequence ，如果字符串 word 连续重复 k 次形成的字符串是 sequence 的一个子字符串，那么单词 word 的 重复值为 k 。单词 word 的 最大重复值 是单词 word 在 sequence 中最大的重复值。如果 word 不是 sequence 的子串，那么重复值 k 为 0 。
    给你一个字符串 sequence 和 word ，请你返回 最大重复值 k 。

    示例 1：
        输入：sequence = "ababc", word = "ab"
        输出：2
        解释："abab" 是 "ababc" 的子字符串。
    示例 2：
        输入：sequence = "ababc", word = "ba"
        输出：1
        解释："ba" 是 "ababc" 的子字符串，但 "baba" 不是 "ababc" 的子字符串。
    示例 3：
        输入：sequence = "ababc", word = "ac"
        输出：0
        解释："ac" 不是 "ababc" 的子字符串。

    提示：
        1 <= sequence.length <= 100
        1 <= word.length <= 100
        sequence 和 word 都只包含小写英文字母。
    */

    public static void main(String[] args) {
        String sequence = "ababc", word = "ab";
        String sequence2 = "ababc", word2 = "ba";
        String sequence3 = "ababc", word3 = "ac";
        String sequence4 = "aaabaaaabaaabaaaabaaaabaaaabaaaaba", word4 = "aaaba";

        System.out.println(maxRepeating2(sequence, word));
        System.out.println(maxRepeating2(sequence2, word2));
        System.out.println(maxRepeating2(sequence3, word3));
        System.out.println(maxRepeating2(sequence4, word4));
    }

    private static int maxRepeating(String sequence, String word) {
        int max = 0;
        for (int i = 0; i < sequence.length(); i++) {
            int j = i, k = 0, count = 0;
            while (j < sequence.length() && sequence.charAt(j) == word.charAt(k)) {
                j++;
                k++;
                if (k == word.length()) {
                    k = 0;
                    count++;
                }
            }
            max = Math.max(max, count);
        }
        return max;
    }

    private static int maxRepeating2(String sequence, String word) {
        // dp[i]表示以第i个字符为结尾的最大重复值，记word.length=m
        // 如果以i为结尾的前m个字符与word相等，则dp[i] = dp[i-word.length] + 1
        // 如果不相等，则dp[i] = 0
        int[] dp = new int[sequence.length() + 1];
        int n = sequence.length(), m = word.length(), max = 0;
        for (int i = 0; i < n; i++) {
            if (i + 1 < m) {
                continue;
            }
            if (sequence.subSequence(i - m + 1, i + 1).equals(word)) {
                dp[i + 1] = dp[i - m + 1] + 1;
            }
            max = Math.max(max, dp[i + 1]);
        }
        return max;
    }
}
