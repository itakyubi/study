package com.wa.leetcode;

import java.util.*;

/**
 * FindSubstring
 * https://leetcode.cn/problems/substring-with-concatenation-of-all-words/
 * 30. 串联所有单词的子串
 *
 * @author: wuao
 * @time: 2022/6/23 20:36
 **/
public class FindSubstring {

    /*
    给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
    注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。

    示例 1：
        输入：s = "barfoothefoobarman", words = ["foo","bar"]
        输出：[0,9]
        解释：
            从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
            输出的顺序不重要, [9,0] 也是有效答案。
    示例 2：
        输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
        输出：[]
    示例 3：
        输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
        输出：[6,9,12]

    提示：
        1 <= s.length <= 104
        s 由小写英文字母组成
        1 <= words.length <= 5000
        1 <= words[i].length <= 30
        words[i] 由小写英文字母组成
    */

    public static void main(String[] args) {
        String s = "barfoothefoobarman";
        String[] words = new String[]{"foo", "bar"};

        String s2 = "wordgoodgoodgoodbestword";
        String[] words2 = new String[]{"word", "good", "best", "word"};

        String s3 = "barfoofoobarthefoobarman";
        String[] words3 = new String[]{"bar", "foo", "the"};

        String s4 = "wordgoodgoodgoodbestword";
        String[] words4 = new String[]{"word","good","best","good"};

        /*System.out.println(findSubstring(s, words));
        System.out.println(findSubstring(s2, words2));
        System.out.println(findSubstring(s3, words3));*/
        System.out.println(findSubstring(s4, words4));
    }

    private static Map<String, Integer> wordMap;
    private static int[] visited;
    private static int wordLength;

    private static List<Integer> findSubstring(String s, String[] words) {
        if (words == null || words.length == 0)
            return new ArrayList<>();

        int wordCount = words.length;
        wordLength = words[0].length();
        wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }
        visited = new int[s.length()];
        Arrays.fill(visited, -1);

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i <= s.length() - wordLength; i++) {
            if (helper(s, i, wordCount, new HashMap<>())) {
                res.add(i);
                visited[i] = 1;
            } else {
                visited[i] = 0;
            }
        }
        return res;
    }

    private static boolean helper(String s, int start, int count, Map<String, Integer> wordCountMap) {
        if (count == 0)
            return true;
        if (start > s.length() - wordLength)
            return false;
        if (visited[start] == 0)
            return false;
        if (visited[start] == 1)
            return true;

        String word = s.substring(start, start + wordLength);
        if (!wordMap.containsKey(word) || wordMap.get(word) <= wordCountMap.getOrDefault(word, 0)) {
            return false;
        } else {
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
            return helper(s, start + wordLength, count - 1, wordCountMap);
        }
    }
}
