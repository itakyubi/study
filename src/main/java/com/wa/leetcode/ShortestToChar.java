package com.wa.leetcode;

import java.util.Arrays;

/**
 * ShortestToChar
 * https://leetcode-cn.com/problems/shortest-distance-to-a-character/
 * 2022-04-19 09:02
 *
 * @author wuao
 */
public class ShortestToChar {

    public static void main(String[] args) {
        String s = "loveleetcode";
        char c = 'e';
        String s2 = "aaab";
        char c2 = 'b';

        System.out.println(Arrays.toString(shortestToChar(s, c)));
        System.out.println(Arrays.toString(shortestToChar(s2, c2)));
    }

    private static int[] shortestToChar(String s, char c) {
        int n = s.length();
        int[] res = new int[n];
        for (int i = 0, idx = -n; i < n; i++) {
            if (s.charAt(i) == c) {
                idx = i;
            }
            res[i] = i - idx;
        }

        for (int i = n - 1, idx = 2 * n; i >= 0; i--) {
            if (s.charAt(i) == c) {
                idx = i;
            }
            res[i] = Math.min(res[i], idx - i);
        }
        return res;
    }
}
