package com.wa.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * CountConsistentStrings
 * https://leetcode.cn/problems/count-the-number-of-consistent-strings/
 * 1684. 统计一致字符串的数目
 * 2022/11/8 5:28 下午
 *
 * @author wuao
 */
public class CountConsistentStrings {

    /*
    给你一个由不同字符组成的字符串 allowed 和一个字符串数组 words 。如果一个字符串的每一个字符都在 allowed 中，就称这个字符串是 一致字符串 。
    请你返回 words 数组中 一致字符串 的数目。

    示例 1：
        输入：allowed = "ab", words = ["ad","bd","aaab","baa","badab"]
        输出：2
        解释：字符串 "aaab" 和 "baa" 都是一致字符串，因为它们只包含字符 'a' 和 'b' 。
    示例 2：
        输入：allowed = "abc", words = ["a","b","c","ab","ac","bc","abc"]
        输出：7
        解释：所有字符串都是一致的。
    示例 3：
        输入：allowed = "cad", words = ["cc","acd","b","ba","bac","bad","ac","d"]
        输出：4
        解释：字符串 "cc"，"acd"，"ac" 和 "d" 是一致字符串。

    提示：
        1 <= words.length <= 104
        1 <= allowed.length <= 26
        1 <= words[i].length <= 10
        allowed 中的字符 互不相同 。
        words[i] 和 allowed 只包含小写英文字母。
    */

    public static void main(String[] args) {
        String allowed = "ab";
        String[] words = new String[]{"ad", "bd", "aaab", "baa", "badab"};

        String allowed2 = "abc";
        String[] words2 = new String[]{"a", "b", "c", "ab", "ac", "bc", "abc"};

        String allowed3 = "cad";
        String[] words3 = new String[]{"cc", "acd", "b", "ba", "bac", "bad", "ac", "d"};

        System.out.println(countConsistentStrings(allowed, words));
        System.out.println(countConsistentStrings(allowed2, words2));
        System.out.println(countConsistentStrings(allowed3, words3));
    }

    private static int countConsistentStrings(String allowed, String[] words) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < allowed.length(); i++) {
            set.add(allowed.charAt(i));
        }

        int res = 0;
        for (String word : words) {
            boolean flag = true;
            for (int i = 0; i < word.length(); i++) {
                if (!set.contains(word.charAt(i))) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res++;
            }
        }
        return res;
    }
}
