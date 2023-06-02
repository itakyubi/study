package com.wa.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * VowelStrings
 * https://leetcode.cn/problems/count-vowel-strings-in-ranges/
 * 2559. 统计范围内的元音字符串数
 * 2023/6/2 9:11 AM
 *
 * @author wuao
 */
public class VowelStrings {

    /*
    给你一个下标从 0 开始的字符串数组 words 以及一个二维整数数组 queries 。
    每个查询 queries[i] = [li, ri] 会要求我们统计在 words 中下标在 li 到 ri 范围内（包含 这两个值）并且以元音开头和结尾的字符串的数目。
    返回一个整数数组，其中数组的第 i 个元素对应第 i 个查询的答案。
    注意：元音字母是 'a'、'e'、'i'、'o' 和 'u' 。

    示例 1：
        输入：words = ["aba","bcb","ece","aa","e"], queries = [[0,2],[1,4],[1,1]]
        输出：[2,3,0]
        解释：以元音开头和结尾的字符串是 "aba"、"ece"、"aa" 和 "e" 。
        查询 [0,2] 结果为 2（字符串 "aba" 和 "ece"）。
        查询 [1,4] 结果为 3（字符串 "ece"、"aa"、"e"）。
        查询 [1,1] 结果为 0 。
        返回结果 [2,3,0] 。
    示例 2：
        输入：words = ["a","e","i"], queries = [[0,2],[0,1],[2,2]]
        输出：[3,2,1]
        解释：每个字符串都满足这一条件，所以返回 [3,2,1] 。

    提示：
        1 <= words.length <= 105
        1 <= words[i].length <= 40
        words[i] 仅由小写英文字母组成
        sum(words[i].length) <= 3 * 105
        1 <= queries.length <= 105
        0 <= queries[j][0] <= queries[j][1] <words.length
    */

    public static void main(String[] args) {
        String[] words = {"aba", "bcb", "ece", "aa", "e"};
        int[][] queries = {{0, 2}, {1, 4}, {1, 1}};

        String[] words2 = {"a", "e", "i"};
        int[][] queries2 = {{0, 2}, {0, 1}, {2, 2}};

        System.out.println(Arrays.toString(vowelStrings(words, queries)));
        System.out.println(Arrays.toString(vowelStrings(words2, queries2)));
    }

    private static int[] vowelStrings(String[] words, int[][] queries) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

        // 前缀和
        int[] vowelCnt = new int[words.length];
        int cnt = 0;
        for (int i = 0; i < words.length; i++) {
            if (vowels.contains(words[i].charAt(0)) && vowels.contains(words[i].charAt(words[i].length() - 1))) {
                cnt++;
            }
            vowelCnt[i] = cnt;
        }

        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            if (queries[i][0] == 0) {
                res[i] = vowelCnt[queries[i][1]];
            } else {
                res[i] = vowelCnt[queries[i][1]] - vowelCnt[queries[i][0] - 1];
            }
        }
        return res;

    }

    // timeout
    private static int[] vowelStrings2(String[] words, int[][] queries) {
        boolean[] isVowel = new boolean[words.length];
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

        for (int i = 0; i < words.length; i++) {
            if (vowels.contains(words[i].charAt(0)) && vowels.contains(words[i].charAt(words[i].length() - 1))) {
                isVowel[i] = true;
            }
        }

        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            for (int j = queries[i][0]; j <= queries[i][1]; j++) {
                res[i] += isVowel[j] ? 1 : 0;
            }
        }
        return res;
    }


}
