package com.wa.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * CloseStrings
 * https://leetcode.cn/problems/determine-if-two-strings-are-close
 * 1657. 确定两个字符串是否接近
 *
 * @Date: 2023/11/30 8:21
 * @Author: wuao
 */
public class CloseStrings {

    /*
    如果可以使用以下操作从一个字符串得到另一个字符串，则认为两个字符串 接近 ：
    操作 1：交换任意两个 现有 字符。
    例如，abcde -> aecdb
    操作 2：将一个 现有 字符的每次出现转换为另一个 现有 字符，并对另一个字符执行相同的操作。
    例如，aacabb -> bbcbaa（所有 a 转化为 b ，而所有的 b 转换为 a ）
    你可以根据需要对任意一个字符串多次使用这两种操作。
    给你两个字符串，word1 和 word2 。如果 word1 和 word2 接近 ，就返回 true ；否则，返回 false 。

    示例 1：
        输入：word1 = "abc", word2 = "bca"
        输出：true
        解释：2 次操作从 word1 获得 word2 。
        执行操作 1："abc" -> "acb"
        执行操作 1："acb" -> "bca"
    示例 2：
        输入：word1 = "a", word2 = "aa"
        输出：false
        解释：不管执行多少次操作，都无法从 word1 得到 word2 ，反之亦然。
    示例 3：
        输入：word1 = "cabbba", word2 = "abbccc"
        输出：true
        解释：3 次操作从 word1 获得 word2 。
        执行操作 1："cabbba" -> "caabbb"
        执行操作 2："caabbb" -> "baaccc"
        执行操作 2："baaccc" -> "abbccc"
    示例 4：
        输入：word1 = "cabbba", word2 = "aabbss"
        输出：false
        解释：不管执行多少次操作，都无法从 word1 得到 word2 ，反之亦然。

    提示：
        1 <= word1.length, word2.length <= 105
        word1 和 word2 仅包含小写英文字母
    */

    public static void main(String[] args) {
        String word1 = "abc", word2 = "bca";
        String word12 = "a", word22 = "aa";
        String word13 = "cabbba", word23 = "abbccc";
        String word14 = "uau", word24 = "ssx";

        System.out.println(closeStrings(word1, word2));
        System.out.println(closeStrings(word12, word22));
        System.out.println(closeStrings(word13, word23));
        System.out.println(closeStrings(word14, word24));
    }

    public static boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < word1.length(); i++) {
            cnt1[word1.charAt(i) - 'a']++;
            cnt2[word2.charAt(i) - 'a']++;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            if ((cnt1[i] == 0 && cnt2[i] != 0) || (cnt1[i] != 0 && cnt2[i] == 0)) {
                return false;
            }

            map.put(cnt1[i], map.getOrDefault(cnt1[i], 0) + 1);
            map.put(cnt2[i], map.getOrDefault(cnt2[i], 0) - 1);
        }

        for (Integer val : map.values()) {
            if (val > 0) {
                return false;
            }
        }
        return true;
    }
}
