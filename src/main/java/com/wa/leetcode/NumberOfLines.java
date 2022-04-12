package com.wa.leetcode;

import java.util.Arrays;

/**
 * NumberOfLines
 * 2022-04-12 08:59
 *
 * @author wuao
 */
public class NumberOfLines {

    public static void main(String[] args) {
        int[] widths = new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        String S = "abcdefghijklmnopqrstuvwxyz";

        int[] widths2 = new int[]{4, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        String S2 = "bbbcccdddaaa";

        System.out.println(Arrays.toString(numberOfLines(widths, S)));
        System.out.println(Arrays.toString(numberOfLines(widths2, S2)));
    }

    private static int[] numberOfLines(int[] widths, String s) {
        int sum = 0, level = 0;
        for (int i = 0; i < s.length(); i++) {
            int width = widths[s.charAt(i) - 'a'];
            if (sum + width <= 100) {
                sum += width;
            } else {
                level++;
                sum = width;
            }
        }

        return new int[]{level + 1, sum};
    }
}
