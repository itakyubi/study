package com.wa.leetcode;

/**
 * FindLUSlength
 * https://leetcode.cn/problems/longest-uncommon-subsequence-ii/
 * 522. 最长特殊序列 II
 * 2022-06-27 09:13
 *
 * @author wuao
 */
public class FindLUSlength {

    /*
    给定字符串列表 strs ，返回其中 最长的特殊序列 。如果最长特殊序列不存在，返回 -1 。
    特殊序列 定义如下：该序列为某字符串 独有的子序列（即不能是其他字符串的子序列）。
    s 的 子序列可以通过删去字符串 s 中的某些字符实现。
    例如，"abc" 是 "aebdc" 的子序列，因为您可以删除"aebdc"中的下划线字符来得到 "abc" 。"aebdc"的子序列还包括"aebdc"、 "aeb" 和 "" (空字符串)。
             
    示例 1：
        输入: strs = ["aba","cdc","eae"]
        输出: 3
    示例 2:
        输入: strs = ["aaa","aaa","aa"]
        输出: -1

    提示:
        2 <= strs.length <= 50
        1 <= strs[i].length <= 10
        strs[i] 只包含小写英文字母
    */

    public static void main(String[] args) {
        String[] strs = new String[]{"aba", "cdc", "eae"};
        String[] strs2 = new String[]{"aaa", "aaa", "aa"};
        String[] strs3 = new String[]{"aabbcc", "aabbcc", "cb"};

        System.out.println(findLUSlength(strs));
        System.out.println(findLUSlength(strs2));
        System.out.println(findLUSlength(strs3));
    }


    private static int findLUSlength(String[] strs) {
        int res = -1;
        for (int i = 0; i < strs.length; i++) {
            boolean flag = true;
            for (int j = 0; j < strs.length; j++) {
                if (i != j && isSubSeq(strs[i], strs[j])) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res = Math.max(res, strs[i].length());
            }
        }
        return res;
    }

    private static boolean isSubSeq(String s1, String s2) {
        int i = 0, j = 0;
        while (i < s1.length() && j < s2.length()) {
            if (s1.charAt(i) == s2.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == s1.length();
    }
}
