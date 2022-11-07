package com.wa.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * AmbiguousCoordinates
 * https://leetcode.cn/problems/ambiguous-coordinates/
 * 816. 模糊坐标
 * 2022/11/7 6:43 下午
 *
 * @author wuao
 */
public class AmbiguousCoordinates {

    /*
    我们有一些二维坐标，如 "(1, 3)" 或 "(2, 0.5)"，然后我们移除所有逗号，小数点和空格，得到一个字符串S。返回所有可能的原始字符串到一个列表中。
    原始的坐标表示法不会存在多余的零，所以不会出现类似于"00", "0.0", "0.00", "1.0", "001", "00.01"或一些其他更小的数来表示坐标。此外，一个小数点前至少存在一个数，所以也不会出现“.1”形式的数字。
    最后返回的列表可以是任意顺序的。而且注意返回的两个数字中间（逗号之后）都有一个空格。

    示例 1:
        输入: "(123)"
        输出: ["(1, 23)", "(12, 3)", "(1.2, 3)", "(1, 2.3)"]
    示例 2:
        输入: "(00011)"
        输出:  ["(0.001, 1)", "(0, 0.011)"]
        解释:
                0.0, 00, 0001 或 00.01 是不被允许的。
    示例 3:
        输入: "(0123)"
        输出: ["(0, 123)", "(0, 12.3)", "(0, 1.23)", "(0.1, 23)", "(0.1, 2.3)", "(0.12, 3)"]
    示例 4:
        输入: "(100)"
        输出: [(10, 0)]
        解释:
                1.0 是不被允许的。

    提示:
        4 <= S.length <= 12.
        S[0] = "(", S[S.length - 1] = ")", 且字符串 S 中的其他元素都是数字。
    */

    public static void main(String[] args) {
        String s = "(123)";
        String s2 = "(00011)";
        String s3 = "(0123)";
        String s4 = "(100)";

        System.out.println(ambiguousCoordinates(s));
        System.out.println(ambiguousCoordinates(s2));
        System.out.println(ambiguousCoordinates(s3));
        System.out.println(ambiguousCoordinates(s4));
    }


    private static List<String> ambiguousCoordinates(String s) {
        s = s.substring(1, s.length() - 1);
        List<String> res = new ArrayList<>();
        for (int i = 1; i < s.length(); i++) {
            String left = s.substring(0, i);
            String right = s.substring(i);

            List<String> possibleLeft = getPossibleString(left);
            if (possibleLeft.isEmpty()) {
                continue;
            }
            List<String> possibleRight = getPossibleString(right);
            if (possibleRight.isEmpty()) {
                continue;
            }
            for (String l : possibleLeft) {
                for (String r : possibleRight) {
                    res.add("(" + l + ", " + r + ")");
                }
            }
        }
        return res;
    }

    private static List<String> getPossibleString(String s) {
        List<String> res = new ArrayList<>();
        if (s.charAt(0) != '0' || s.equals("0")) {
            res.add(s);
        }
        if (s.charAt(s.length() - 1) == '0') {
            return res;
        }
        for (int i = 1; i < s.length(); i++) {
            // 首位为0，小数点不在第一个数之后则是不合法的
            if (s.charAt(0) == '0' && i != 1) {
                continue;
            }
            res.add(s.substring(0, i) + "." + s.substring(i));
        }
        return res;
    }
}
