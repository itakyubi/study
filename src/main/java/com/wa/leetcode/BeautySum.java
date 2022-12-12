package com.wa.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * BeautySum
 * https://leetcode.cn/problems/sum-of-beauty-of-all-substrings/
 * 1781. 所有子字符串美丽值之和
 * 2022/12/12 6:26 下午
 *
 * @author wuao
 */
public class BeautySum {

    /*
    一个字符串的 美丽值定义为：出现频率最高字符与出现频率最低字符的出现次数之差。
    比方说，"abaacc"的美丽值为3 - 1 = 2。
    给你一个字符串s，请你返回它所有子字符串的美丽值之和。

    示例 1：
        输入：s = "aabcb"
        输出：5
        解释：美丽值不为零的字符串包括 ["aab","aabc","aabcb","abcb","bcb"] ，每一个字符串的美丽值都为 1 。
    示例 2：
        输入：s = "aabcbaa"
        输出：17
            
    提示：
        1 <= s.length <= 500
        s只包含小写英文字母。
    */

    public static void main(String[] args) {
        String s = "aabcb";
        String s2 = "aabcbaa";
        String s3 = "xzvfsppsjfbxdwkqe";

        System.out.println(beautySum2(s));
        System.out.println(beautySum2(s2));
        System.out.println(beautySum2(s3));
    }

    private static int beautySum2(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            int[] cnts = new int[26];
            cnts[s.charAt(i) - 'a']++;
            for (int j = i + 1; j < s.length(); j++) {
                cnts[s.charAt(j) - 'a']++;

                int min = s.length(), max = 0;
                for (int v : cnts) {
                    if (v > 0) {
                        min = Math.min(min, v);
                        max = Math.max(max, v);
                    }
                }
                res += max - min;
            }
        }
        return res;
    }

    private static int beautySum(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            Map<Character, Integer> map = new HashMap<>();
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            for (int j = i + 1; j < s.length(); j++) {
                map.put(s.charAt(j), map.getOrDefault(s.charAt(j), 0) + 1);

                if (map.size() < 2) {
                    continue;
                }

                int min = s.length(), max = 0;
                for (int v : map.values()) {
                    min = Math.min(min, v);
                    max = Math.max(max, v);
                }
                res += max - min;
            }
        }
        return res;
    }


}
