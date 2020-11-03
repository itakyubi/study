package com.wa.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * MaximumProductOfWordLengths
 * 2020-11-03 17:31
 *
 * @author wuao
 */
public class MaximumProductOfWordLengths {
    public int maxProduct(String[] words) {
        int len = words.length;
        int[] bitmasks = new int[len];

        for (int i = 0; i < len; i++) {
            int bitmask = 0;
            for (int j = 0; j < words[i].length(); j++)
                bitmask |= 1 << (words[i].charAt(j) - 'a');
            bitmasks[i] = bitmask;
        }

        int max = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if ((bitmasks[i] & bitmasks[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }

    public int maxProduct2(String[] words) {
        Map<Integer, Integer> bitMap = new HashMap<>();

        for (String word : words) {
            int bitmask = 0;
            for (int j = 0; j < word.length(); j++)
                bitmask |= 1 << (word.charAt(j) - 'a');
            bitMap.put(bitmask, Math.max(bitMap.getOrDefault(bitmask, 0), word.length()));
        }

        int max = 0;
        for (int x : bitMap.keySet()) {
            for (int y : bitMap.keySet()) {
                if ((x & y) == 0) {
                    max = Math.max(max, bitMap.get(x) * bitMap.get(y));
                }
            }
        }
        return max;
    }


}
