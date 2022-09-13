package com.wa.leetcode;

/**
 * MaximumSwap
 * https://leetcode.cn/problems/maximum-swap/
 * 670. 最大交换
 * 2022/9/13 1:57 下午
 *
 * @author wuao
 */
public class MaximumSwap {

    /*
    给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。

    示例 1 :
        输入: 2736
        输出: 7236
        解释: 交换数字2和数字7。
    示例 2 :
        输入: 9973
        输出: 9973
        解释: 不需要交换。
    注意:
        给定数字的范围是[0, 10^8]
    */

    public static void main(String[] args) {
        int num = 2736;
        int num2 = 9973;
        int num3 = 10;
        int num4 = 98368;

        System.out.println(maximumSwap(num));
        System.out.println(maximumSwap(num2));
        System.out.println(maximumSwap(num3));
        System.out.println(maximumSwap(num4));
    }

    private static int maximumSwap(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        int maxIdx = chars.length - 1;
        int idx1 = -1, idx2 = -1;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] > chars[maxIdx]) {
                maxIdx = i;
            } else if (chars[i] < chars[maxIdx]) {
                idx1 = i;
                idx2 = maxIdx;
            }
        }
        if (idx1 >= 0) {
            char tmp = chars[idx1];
            chars[idx1] = chars[idx2];
            chars[idx2] = tmp;
        }

        return Integer.parseInt(new String(chars));
    }
}
