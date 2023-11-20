package com.wa.leetcode;

import cn.hutool.core.lang.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * com.wa.leetcode.LadderLength
 *
 * @desc:
 * @author: wuao <wuao@baidu.com>
 * @time: 2020-01-09 17:43
 */
public class LadderLength {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int len = beginWord.length();
        HashMap<String, List<String>> dict = new HashMap<>();
        wordList.forEach(word -> {
            for (int i = 0; i < len; i++) {
                String newWord = word.substring(0, i) + "*" + word.substring(i + 1, len);
                List<String> words = dict.getOrDefault(newWord, new ArrayList<>());
                words.add(word);
                dict.put(newWord, words);
            }
        });

        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        Map<String, Boolean> visited = new HashMap<>();
        queue.add(new Pair(beginWord, 1));
        visited.put(beginWord, true);

        while (!queue.isEmpty()) {
            Pair<String, Integer> node = queue.poll();
            String word = node.getKey();
            int l = node.getValue();
            for (int i = 0; i < len; i++) {
                String newWord = word.substring(0, i) + "*" + word.substring(i + 1, len);
                for (String w : dict.getOrDefault(newWord, new ArrayList<>())) {
                    if (w.equals(endWord)) {
                        return l + 1;
                    }
                    if (!visited.containsKey(w)) {
                        visited.put(w, true);
                        queue.add(new Pair(w, l + 1));
                    }
                }
            }
        }

        return 0;
    }
}
