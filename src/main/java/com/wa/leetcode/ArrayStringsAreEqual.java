package com.wa.leetcode;

/**
 * ArrayStringsAreEqual
 * https://leetcode.cn/problems/check-if-two-string-arrays-are-equivalent/
 * 1662. 检查两个字符串数组是否相等
 * 2022/11/1 6:51 下午
 *
 * @author wuao
 */
public class ArrayStringsAreEqual {

    /*
    给你两个字符串数组 word1 和 word2 。如果两个数组表示的字符串相同，返回 true ；否则，返回 false 。
    数组表示的字符串是由数组中的所有元素 按顺序 连接形成的字符串。

    示例 1：
        输入：word1 = ["ab", "c"], word2 = ["a", "bc"]
        输出：true
        解释：
        word1 表示的字符串为 "ab" + "c" -> "abc"
        word2 表示的字符串为 "a" + "bc" -> "abc"
        两个字符串相同，返回 true
    示例 2：
        输入：word1 = ["a", "cb"], word2 = ["ab", "c"]
        输出：false
    示例 3：
        输入：word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
        输出：true

    提示：
        1 <= word1.length, word2.length <= 103
        1 <= word1[i].length, word2[i].length <= 103
        1 <= sum(word1[i].length), sum(word2[i].length) <= 103
        word1[i] 和 word2[i] 由小写字母组成
    */

    public static void main(String[] args) {
        String[] word1 = new String[]{"ab", "c"}, word2 = new String[]{"a", "bc"};

        System.out.println(arrayStringsAreEqual(word1, word2));
    }

    private static boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int i1 = 0, i11 = 0, i2 = 0, i22 = 0;
        while (i1 < word1.length && i2 < word2.length) {
            if (word1[i1].charAt(i11) == word2[i2].charAt(i22)) {
                i11++;
                i22++;
            } else {
                return false;
            }
            if (i11 == word1[i1].length()) {
                i1++;
                i11 = 0;
            }
            if (i22 == word2[i2].length()) {
                i2++;
                i22 = 0;
            }
        }
        return i1 == word1.length && i2 == word2.length;
    }
}
