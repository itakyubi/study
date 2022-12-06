package com.wa.leetcode;

import java.util.HashSet;

/**
 * NumDifferentIntegers
 * https://leetcode.cn/problems/number-of-different-integers-in-a-string/
 * 1805. 字符串中不同整数的数目
 * 2022/12/6 5:09 下午
 *
 * @author wuao
 */
public class NumDifferentIntegers {

    /*
    给你一个字符串 word ，该字符串由数字和小写英文字母组成。
    请你用空格替换每个不是数字的字符。例如，"a123bc34d8ef34" 将会变成 " 123  34 8  34" 。注意，剩下的这些整数为（相邻彼此至少有一个空格隔开）："123"、"34"、"8" 和 "34" 。
    返回对 word 完成替换后形成的 不同 整数的数目。
    只有当两个整数的 不含前导零 的十进制表示不同， 才认为这两个整数也不同。

    示例 1：
        输入：word = "a123bc34d8ef34"
        输出：3
        解释：不同的整数有 "123"、"34" 和 "8" 。注意，"34" 只计数一次。
    示例 2：
        输入：word = "leet1234code234"
        输出：2
    示例 3：
        输入：word = "a1b01c001"
        输出：1
        解释："1"、"01" 和 "001" 视为同一个整数的十进制表示，因为在比较十进制值时会忽略前导零的存在。

    提示：
        1 <= word.length <= 1000
        word 由数字和小写英文字母组成
    */

    public static void main(String[] args) {
        String word = "a123bc34d8ef34";
        String word2 = "leet1234code234";
        String word3 = "a1b01c001";

        System.out.println(numDifferentIntegers(word));
        System.out.println(numDifferentIntegers(word2));
        System.out.println(numDifferentIntegers(word3));
    }

    private static int numDifferentIntegers(String word) {
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < word.length(); i++) {
            StringBuilder sb = new StringBuilder();
            while (i < word.length() && word.charAt(i) >= '0' && word.charAt(i) <= '9') {
                sb.append(word.charAt(i));
                i++;
            }
            int index = 0;
            while (index < sb.length()) {
                if (sb.charAt(index) == '0') {
                    index++;
                } else {
                    break;
                }
            }
            if (sb.length() > 0) {
                set.add(sb.substring(index));
            }
        }
        return set.size();
    }
}
