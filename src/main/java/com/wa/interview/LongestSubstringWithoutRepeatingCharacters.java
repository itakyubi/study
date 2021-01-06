package com.wa.interview;

/**
 * LongestSubstringWithoutRepeatingCharacters
 * 字符串中最长的没有重读字符的子串
 * 2021-01-06 17:25
 *
 * @author wuao
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        System.out.println(getLongestSubstring("bbbbb"));
        System.out.println(getLongestSubstring("abcabcbb"));
        System.out.println(getLongestSubstring("abcdba"));
        System.out.println(getLongestSubstring("abcdefedcbao"));
    }

    private static String getLongestSubstring(String s) {
        int[] map = new int[128];
        int res = 0, l = 0, r = 0;
        for (int i = 0, j = 0; j < s.length(); j++) {
            i = Math.max(map[s.charAt(j)], i);
            if (j - i + 1 > res) {
                res = j - i + 1;
                l = i;
                r = j + 1;
            }
            map[s.charAt(j)] = j + 1;
        }
        return s.substring(l, r);
    }
}
