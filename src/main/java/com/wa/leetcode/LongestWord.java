package com.wa.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * LongestWord
 * https://leetcode-cn.com/problems/longest-word-in-dictionary/
 * 2022-03-17 08:58
 *
 * @author wuao
 */
public class LongestWord {

    public static void main(String[] args) {
        String[] words = new String[]{"w", "wo", "wor", "worl", "world"};
        String[] words2 = new String[]{"a", "banana", "app", "appl", "ap", "apply", "apple"};

        System.out.println(longestWord(words));
        System.out.println(longestWord(words2));
    }

    private static String longestWord(String[] words) {
        Arrays.sort(words, String::compareTo);

        int max = 0;
        String res = "";
        Set<String> set = new HashSet<>();
        for (String word : words) {
            if (word.length() == 1 || set.contains(word.substring(0, word.length() - 1))) {
                if (word.length() > max) {
                    max = word.length();
                    res = word;
                }
                set.add(word);
            }
        }

        return res;
    }
}
