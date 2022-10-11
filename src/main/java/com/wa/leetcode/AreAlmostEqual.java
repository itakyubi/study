package com.wa.leetcode;

/**
 * AreAlmostEqual
 * <p>
 * https://leetcode.cn/problems/check-if-one-string-swap-can-make-strings-equal/
 * 1790. 仅执行一次字符串交换能否使两个字符串相等
 * 2022/10/11 6:36 下午
 *
 * @author wuao
 */
public class AreAlmostEqual {

    /*
    给你长度相等的两个字符串 s1 和 s2 。一次 字符串交换 操作的步骤如下：选出某个字符串中的两个下标（不必不同），并交换这两个下标所对应的字符。
    如果对 其中一个字符串 执行 最多一次字符串交换 就可以使两个字符串相等，返回 true ；否则，返回 false 。

    示例 1：
        输入：s1 = "bank", s2 = "kanb"
        输出：true
        解释：例如，交换 s2 中的第一个和最后一个字符可以得到 "bank"
    示例 2：
        输入：s1 = "attack", s2 = "defend"
        输出：false
        解释：一次字符串交换无法使两个字符串相等
    示例 3：
        输入：s1 = "kelb", s2 = "kelb"
        输出：true
        解释：两个字符串已经相等，所以不需要进行字符串交换
    示例 4：
        输入：s1 = "abcd", s2 = "dcba"
        输出：false

    提示：
        1 <= s1.length, s2.length <= 100
        s1.length == s2.length
        s1 和 s2 仅由小写英文字母组成
    */

    public static void main(String[] args) {
        String s1 = "bank", s2 = "kanb";
        String s12 = "attack", s22 = "defend";
        String s13 = "kelb", s23 = "kelb";
        String s14 = "abcd", s24 = "dcba";

        System.out.println(areAlmostEqual(s1, s2));
        System.out.println(areAlmostEqual(s12, s22));
        System.out.println(areAlmostEqual(s13, s23));
        System.out.println(areAlmostEqual(s14, s24));
    }

    private static boolean areAlmostEqual(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;
        int count = 0, idx1 = -1, idx2 = -1;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                count++;
                if (count > 2)
                    return false;
                if (idx1 == -1) {
                    idx1 = i;
                } else {
                    idx2 = i;
                }
            }
        }
        return count == 0 || count == 2 && s1.charAt(idx1) == s2.charAt(idx2) && s1.charAt(idx2) == s2.charAt(idx1);
    }
}
