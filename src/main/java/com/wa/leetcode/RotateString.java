package com.wa.leetcode;

/**
 * RotateString
 * https://leetcode-cn.com/problems/rotate-string/
 * 2022-04-07 09:06
 *
 * @author wuao
 */
public class RotateString {

    public static void main(String[] args) {
        String s = "abcde", goal = "cdeab";
        String s2 = "abcde", goal2 = "abced";

        System.out.println(rotateString(s, goal));
        System.out.println(rotateString(s2, goal2));
    }

    private static boolean rotateString(String s, String goal) {
        if (s.length() != goal.length())
            return false;

        char start = goal.charAt(0);
        for (int i = 0; i < s.length(); i++) {
            if (start == s.charAt(i) && goal.equals(s.substring(i) + s.substring(0, i))) {
                return true;
            }
        }
        return false;
    }
}
