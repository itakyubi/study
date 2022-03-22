package com.wa.leetcode;

/**
 * WinnerOfGame
 * https://leetcode-cn.com/problems/remove-colored-pieces-if-both-neighbors-are-the-same-color/
 * 2022-03-22 09:16
 *
 * @author wuao
 */
public class WinnerOfGame {

    public static void main(String[] args) {
        String colors = "AAABABB";
        String colors2 = "AA";
        String colors3 = "ABBBBBBBAAA";

        System.out.println(winnerOfGame(colors));
        System.out.println(winnerOfGame(colors2));
        System.out.println(winnerOfGame(colors3));

    }

    private static boolean winnerOfGame(String colors) {
        int countA = 0, countB = 0;
        int start = 0;
        while (start < colors.length()) {
            char c = colors.charAt(start);
            int index = start + 1;
            while (index < colors.length() && colors.charAt(index) == c) {
                index++;
            }
            if (index - start >= 3) {
                if (c == 'A') {
                    countA += index - start - 2;
                } else {
                    countB += index - start - 2;
                }
            }
            start = index;
        }
        return countA - countB > 0;
    }
}
