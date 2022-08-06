package com.wa.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * StringMatching
 * https://leetcode.cn/problems/string-matching-in-an-array/
 * 1408. 数组中的字符串匹配
 *
 * @author: wuao
 * @time: 2022/8/6 15:41
 **/
public class StringMatching {

    /*
    给你一个字符串数组 words ，数组中的每个字符串都可以看作是一个单词。请你按 任意 顺序返回 words 中是其他单词的子字符串的所有单词。
    如果你可以删除 words[j] 最左侧和/或最右侧的若干字符得到 word[i] ，那么字符串 words[i] 就是 words[j] 的一个子字符串。

    示例 1：
        输入：words = ["mass","as","hero","superhero"]
        输出：["as","hero"]
        解释："as" 是 "mass" 的子字符串，"hero" 是 "superhero" 的子字符串。
                ["hero","as"] 也是有效的答案。
    示例 2：
        输入：words = ["leetcode","et","code"]
        输出：["et","code"]
        解释："et" 和 "code" 都是 "leetcode" 的子字符串。
    示例 3：
        输入：words = ["blue","green","bu"]
        输出：[]
             
    提示：
        1 <= words.length <= 100
        1 <= words[i].length <= 30
        words[i] 仅包含小写英文字母。
        题目数据 保证 每个 words[i] 都是独一无二的。
    */

    public static void main(String[] args) {
        String[] words = new String[]{"mass", "as", "hero", "superhero"};
        String[] words2 = new String[]{"leetcode", "et", "code"};
        String[] words3 = new String[]{"blue", "green", "bu"};

        System.out.println(stringMatching(words));
        System.out.println(stringMatching(words2));
        System.out.println(stringMatching(words3));
    }

    private static List<String> stringMatching(String[] words) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (j != i && words[j].contains(words[i])) {
                    res.add(words[i]);
                    break;
                }
            }
        }
        return res;
    }
}
