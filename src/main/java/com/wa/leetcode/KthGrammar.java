package com.wa.leetcode;

/**
 * KthGrammar
 * https://leetcode.cn/problems/k-th-symbol-in-grammar/
 * 779. 第K个语法符号
 * 2022/10/20 5:10 下午
 *
 * @author wuao
 */
public class KthGrammar {

    /*
    我们构建了一个包含 n 行(索引从 1 开始)的表。首先在第一行我们写上一个 0。接下来的每一行，将前一行中的0替换为01，1替换为10。
    例如，对于 n = 3 ，第 1 行是 0 ，第 2 行是 01 ，第3行是 0110 。
    给定行数n和序数 k，返回第 n 行中第 k个字符。（k从索引 1 开始）

    示例 1:
        输入: n = 1, k = 1
        输出: 0
        解释: 第一行：0
    示例 2:
        输入: n = 2, k = 1
        输出: 0
        解释:
            第一行: 0
            第二行: 01
    示例 3:
        输入: n = 2, k = 2
        输出: 1
        解释:
            第一行: 0
            第二行: 01

    提示:
        1 <= n <= 30
        1 <= k <= 2n - 1
    */

    public static void main(String[] args) {
        int n = 1, k = 1;
        int n2 = 2, k2 = 1;
        int n3 = 2, k3 = 2;

        System.out.println(kthGrammar(n, k));
        System.out.println(kthGrammar(n2, k2));
        System.out.println(kthGrammar(n3, k3));
    }

    private static int kthGrammar(int n, int k) {
        if (n == 1)
            return 0;
        int tmp = kthGrammar(n - 1, (k + 1) / 2); // 找上一行的父节点的值
        if (tmp == 1) {
            return (k & 1) == 0 ? 0 : 1;
        } else {
            return (k & 1) == 0 ? 1 : 0;
        }
    }
}
