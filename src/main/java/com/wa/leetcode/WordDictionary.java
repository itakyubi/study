package com.wa.leetcode;

import com.wa.model.TireNode;

/**
 * WordDictionary
 * 2020-07-09 15:14
 *
 * @author wuao
 */
public class WordDictionary {

    private TireNode root;

    /**
     * Initialize your data structure here.
     */
    public WordDictionary() {
        root = new TireNode();
    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
        TireNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!node.containsKey(c)) {
                node.put(c, new TireNode());
            }
            node = node.get(c);
        }
        node.setEnd();
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        return searchDfs(word, root);
    }

    public boolean searchDfs(String word, TireNode root) {
        TireNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c == '.') {
                for (int j = 0; j < 26; j++) {
                    char cc = (char) ('a' + j);
                    if (node.containsKey(cc) && searchDfs(word.substring(i + 1), node.get(cc))) {
                        return true;
                    }
                }
                return false;
            } else {
                if (node.containsKey(c)) {
                    node = node.get(c);
                } else {
                    return false;
                }
            }
        }
        return node.isEnd();
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
