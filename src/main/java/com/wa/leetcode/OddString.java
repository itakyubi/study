package com.wa.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * OddString
 * https://leetcode.cn/problems/odd-string-difference/
 * 2451. 差值数组不同的字符串
 * 2023/5/25 8:52 AM
 *
 * @author wuao
 */
public class OddString {

    /*
    给你一个字符串数组 words，每一个字符串长度都相同，令所有字符串的长度都为 n。
    每个字符串words[i]可以被转化为一个长度为n - 1的差值整数数组difference[i]，其中对于0 <= j <= n - 2有difference[i][j] = words[i][j+1] - words[i][j]。
    注意两个字母的差值定义为它们在字母表中位置之差，也就是说'a'的位置是0，'b'的位置是1，'z'的位置是25。
    比方说，字符串"acb"的差值整数数组是[2 - 0, 1 - 2] = [2, -1]。
    words中所有字符串 除了一个字符串以外，其他字符串的差值整数数组都相同。你需要找到那个不同的字符串。
    请你返回words中差值整数数组不同的字符串。

    示例 1：
        输入：words = ["adc","wzy","abc"]
        输出："abc"
        解释：
            - "adc" 的差值整数数组是 [3 - 0, 2 - 3] = [3, -1] 。
            - "wzy" 的差值整数数组是 [25 - 22, 24 - 25]= [3, -1] 。
            - "abc" 的差值整数数组是 [1 - 0, 2 - 1] = [1, 1] 。
        不同的数组是 [1, 1]，所以返回对应的字符串，"abc"。
    示例 2：
        输入：words = ["aaa","bob","ccc","ddd"]
        输出："bob"
        解释：除了 "bob" 的差值整数数组是 [13, -13] 以外，其他字符串的差值整数数组都是 [0, 0] 。

    提示：
        3 <= words.length <= 100
        n == words[i].length
        2 <= n <= 20
        words[i]只含有小写英文字母。
    */

    public static void main(String[] args) {
        String[] words = {"adc", "wzy", "abc"};
        String[] words2 = {"aaa", "bob", "ccc", "ddd"};
        String[] words3 = {"ddd", "poo", "baa", "onn"};
        String[] words4 = {"abm", "bcn", "alm"};

        /*System.out.println(oddString(words));
        System.out.println(oddString(words2));
        System.out.println(oddString(words3));*/
        System.out.println(oddString(words4));
    }

    private static String oddString(String[] words) {
        Map<String, int[]> diffs = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < words[i].length() - 1; j++) {
                char c1 = words[i].charAt(j);
                char c2 = words[i].charAt(j + 1);
                sb.append(c2 - c1);
                sb.append(",");
            }
            String str = sb.toString();
            int[] tmp = diffs.getOrDefault(str, new int[]{0, i});
            tmp[0]++;
            diffs.put(str, tmp);
        }

        for (int[] value : diffs.values()) {
            if (value[0] == 1) {
                return words[value[1]];
            }
        }

        return "";
    }
}
