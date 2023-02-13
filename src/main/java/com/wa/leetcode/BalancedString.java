package com.wa.leetcode;

/**
 * BalancedString
 * https://leetcode.cn/problems/replace-the-substring-for-balanced-string/
 * 1234. 替换子串得到平衡字符串
 * 2023/2/13 3:31 下午
 *
 * @author wuao
 */
public class BalancedString {

    /*
    有一个只含有'Q', 'W', 'E','R'四种字符，且长度为 n的字符串。
    假如在该字符串中，这四个字符都恰好出现n/4次，那么它就是一个「平衡字符串」。
    给你一个这样的字符串 s，请通过「替换一个子串」的方式，使原字符串 s 变成一个「平衡字符串」。
    你可以用和「待替换子串」长度相同的任何 其他字符串来完成替换。
    请返回待替换子串的最小可能长度。
    如果原字符串自身就是一个平衡字符串，则返回 0。

    示例 1：
        输入：s = "QWER"
        输出：0
        解释：s 已经是平衡的了。
    示例 2：
        输入：s = "QQWE"
        输出：1
        解释：我们需要把一个 'Q' 替换成 'R'，这样得到的 "RQWE" (或 "QRWE") 是平衡的。
    示例 3：
        输入：s = "QQQW"
        输出：2
        解释：我们可以把前面的 "QQ" 替换成 "ER"。
    示例 4：
        输入：s = "QQQQ"
        输出：3
        解释：我们可以替换后 3 个 'Q'，使 s = "QWER"。
            
    提示：
        1 <= s.length <= 10^5
        s.length是4的倍数
        s中只含有'Q', 'W', 'E','R'四种字符
    */

    public static void main(String[] args) {
        String s = "QWER";
        String s2 = "QQWE";
        String s3 = "QQQW";
        String s4 = "QQQQ";
        String s5 = "WWEQERQWQWWRWWERQWEQ";
        String s6 = "WQWRQQQW";

        /*System.out.println(balancedString(s));
        System.out.println(balancedString(s2));
        System.out.println(balancedString(s3));
        System.out.println(balancedString(s4));
        System.out.println(balancedString(s5));*/
        System.out.println(balancedString(s6));
    }

    private static int balancedString(String s) {
        // 滑动窗口，由于只能替换一个子串，所以用l和r记录替换子串的起止位置
        // 每个字母出现n/4次，当l和r以外的每种字母出现次数都<=n/4时，此时[l,r]范围就是要替换的子串
        // 如果求最长平衡子串，则[l,r]的次数统计与本题是相反的
        int cnt = s.length() / 4;
        int[] cnts = new int[4];
        for (int i = 0; i < s.length(); i++) {
            cnts[getIndex(s.charAt(i))]++;
        }

        int res = s.length();
        int l = 0, r = 0;
        while (l < s.length()) {
            while (r < s.length() && check(cnts, cnt)) {
                cnts[getIndex(s.charAt(r))]--;
                r++;
            }
            if (check(cnts, cnt)) {
                break;
            }
            res = Math.min(res, r - l);
            cnts[getIndex(s.charAt(l))]++;
            l++;
        }
        return res;
    }

    private static boolean check(int[] cnts, int cnt) {
        return cnts[0] > cnt || cnts[1] > cnt || cnts[2] > cnt || cnts[3] > cnt;
    }

    private static int getIndex(char c) {
        switch (c) {
            case 'Q':
                return 0;
            case 'W':
                return 1;
            case 'E':
                return 2;
            case 'R':
                return 3;
        }
        return -1;
    }
}
