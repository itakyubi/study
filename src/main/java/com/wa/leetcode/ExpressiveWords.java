package com.wa.leetcode;

/**
 * ExpressiveWords
 * https://leetcode-cn.com/problems/expressive-words/
 * <p>
 * 2022-02-21 09:33
 *
 * @author wuao
 */
public class ExpressiveWords {

    public static void main(String[] args) {
        String s = "heeellooo";
        String[] ss = new String[]{"hello", "hi", "helo"};
        System.out.println(expressiveWords(s, ss));
    }

    private static int expressiveWords(String s, String[] words) {
        int count = 0;
        for (String word : words) {
            if (isExpressiveWord(s, word)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isExpressiveWord(String s, String word) {
        if (s.length() < word.length()){
            return false;
        }


        int i = 0, j = 0;
        while (i < s.length() && j < word.length()) {
            if (s.charAt(i) != word.charAt(j)) {
                return false;
            }

            char c = s.charAt(i);
            int c1 = 0, c2 = 0;
            while (i < s.length() && s.charAt(i) == c) {
                c1++;
                i++;
            }
            while (j < word.length() && word.charAt(j) == c) {
                c2++;
                j++;
            }

            if (c1 < c2) {
                return false;
            }
            if (c1 != c2 && c1 == 2) {
                return false;
            }

        }

        if (i != s.length()){
            return false;
        }

        return true;
    }
}
