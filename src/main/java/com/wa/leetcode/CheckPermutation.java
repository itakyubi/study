package com.wa.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * CheckPermutation
 * https://leetcode.cn/problems/check-permutation-lcci/
 * 面试题 01.02. 判定是否互为字符重排
 * 2022/9/27 3:47 下午
 *
 * @author wuao
 */
public class CheckPermutation {

    /*
    给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。

    示例 1：
        输入: s1 = "abc", s2 = "bca"
        输出: true
    示例 2：
        输入: s1 = "abc", s2 = "bad"
        输出: false
    说明：
        0 <= len(s1) <= 100
        0 <= len(s2) <= 100
    */

    public static void main(String[] args) {
        String s1 = "abc", s2 = "bca";
        String s11 = "abc", s22 = "bad";

        System.out.println(CheckPermutation(s1, s2));
        System.out.println(CheckPermutation(s11, s22));
    }

    private static boolean CheckPermutation(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < s2.length(); i++) {
            char c = s2.charAt(i);
            int count = map.getOrDefault(c, 0);
            if (count == 0)
                return false;
            if (count == 1) {
                map.remove(c);
                continue;
            }
            map.put(c, count - 1);
        }
        return map.size() == 0;
    }
}
