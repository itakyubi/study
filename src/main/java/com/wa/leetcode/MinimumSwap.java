package com.wa.leetcode;

/**
 * MinimumSwap
 * https://leetcode.cn/problems/minimum-swaps-to-make-strings-equal/
 * 1247. 交换字符使得字符串相同
 *
 * @author: wuao
 * @time: 2023/2/25 9:12
 **/
public class MinimumSwap {

   /*
    有两个长度相同的字符串 s1 和 s2，且它们其中 只含有 字符 "x" 和 "y"，你需要通过「交换字符」的方式使这两个字符串相同。
    每次「交换字符」的时候，你都可以在两个字符串中各选一个字符进行交换。
    交换只能发生在两个不同的字符串之间，绝对不能发生在同一个字符串内部。也就是说，我们可以交换 s1[i] 和 s2[j]，但不能交换 s1[i] 和 s1[j]。
    最后，请你返回使 s1 和 s2 相同的最小交换次数，如果没有方法能够使得这两个字符串相同，则返回 -1 。

    示例 1：
        输入：s1 = "xx", s2 = "yy"
        输出：1
        解释：
        交换 s1[0] 和 s2[1]，得到 s1 = "yx"，s2 = "yx"。
    示例 2：
        输入：s1 = "xy", s2 = "yx"
        输出：2
        解释：
        交换 s1[0] 和 s2[0]，得到 s1 = "yy"，s2 = "xx" 。
        交换 s1[0] 和 s2[1]，得到 s1 = "xy"，s2 = "xy" 。
        注意，你不能交换 s1[0] 和 s1[1] 使得 s1 变成 "yx"，因为我们只能交换属于两个不同字符串的字符。
    示例 3：
        输入：s1 = "xx", s2 = "xy"
        输出：-1
    示例 4：
        输入：s1 = "xxyyxyxyxx", s2 = "xyyxyxxxyx"
        输出：4

    提示：
        1 <= s1.length, s2.length <= 1000
        s1, s2 只包含 'x' 或 'y'。
    */

    public static void main(String[] args) {
        String s1 = "xx", s2 = "yy";
        String s12 = "xy", s22 = "yx";
        String s13 = "xx", s23 = "xy";
        String s14 = "xxyyxyxyxx", s24 = "xyyxyxxxyx";

        System.out.println(minimumSwap(s1, s2));
        System.out.println(minimumSwap(s12, s22));
        System.out.println(minimumSwap(s13, s23));
        System.out.println(minimumSwap(s14, s24));
    }

    // s1: xx , s2: yy 交换一次
    // s1: yy , s2: xx 交换一次
    // s1: xy , s2: yx 交换两次
    // s1: yx , s2: xy 交换两次
    // 所以统计对位是xy和yx的情况，xy和yx先内部消化，再互相交换
    // 最终的结果就是 xy/2 + yx/2 + xy%2 + yx%2
    // 特殊的xy和yx的个数一奇一偶，则无法互换
    private static int minimumSwap(String s1, String s2) {
        int xyCnt = 0, yxCnt = 0;
        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (c1 == 'x' && c2 == 'y') {
                xyCnt++;
            }
            if (c1 == 'y' && c2 == 'x') {
                yxCnt++;
            }
        }
        if (((xyCnt + yxCnt) & 1) == 1) {
            return -1;
        }
        return (xyCnt >> 1) + (yxCnt >> 1) + (xyCnt & 1) + (yxCnt & 1);
        //return xyCnt / 2 + yxCnt / 2 + xyCnt % 2 + yxCnt % 2;
    }
}
