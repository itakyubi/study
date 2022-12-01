package com.wa.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * RestoreIpAddresses
 * https://leetcode.cn/problems/restore-ip-addresses/
 * 93. 复原 IP 地址
 * 2022/12/1 7:39 下午
 *
 * @author wuao
 */
public class RestoreIpAddresses {

    /*
    有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
    例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
    给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。

    示例 1：
        输入：s = "25525511135"
        输出：["255.255.11.135","255.255.111.35"]
    示例 2：
        输入：s = "0000"
        输出：["0.0.0.0"]
    示例 3：
        输入：s = "101023"
        输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]

    提示：
        1 <= s.length <= 20
        s 仅由数字组成
    */

    public static void main(String[] args) {
        String s = "25525511135";
        String s2 = "0000";
        String s3 = "101023";

        System.out.println(restoreIpAddresses(s));
        System.out.println(restoreIpAddresses(s2));
        System.out.println(restoreIpAddresses(s3));
    }

    private static List<String> restoreIpAddresses(String s) {
        List<String> list = new ArrayList<>();
        helper(s, 4, new StringBuilder(), list);
        return list;
    }

    private static boolean helper(String s, int split, StringBuilder res, List<String> list) {
        if (split == 1) {
            if (valid(s)) {
                res.append(s);
                list.add(res.toString());
                return true;
            } else {
                return false;
            }
        }

        for (int i = 1; i < s.length(); i++) {
            if (valid(s.substring(0, i))) {
                res.append(s.substring(0, i));
                res.append(".");
                if (helper(s.substring(i), split - 1, res, list)) {
                    res.delete(res.length() - s.length() - 1, res.length());
                } else {
                    res.delete(res.length() - i - 1, res.length());
                }
            }
        }
        return false;
    }

    private static boolean valid(String s) {
        return s.length() == 1 || (!s.startsWith("0") && s.length() <= 3 && Integer.parseInt(s) <= 255);
    }
}
