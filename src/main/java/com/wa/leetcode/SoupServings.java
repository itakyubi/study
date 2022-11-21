package com.wa.leetcode;

/**
 * SoupServings
 * https://leetcode.cn/problems/soup-servings/
 * 808. 分汤
 * 2022/11/21 6:35 下午
 *
 * @author wuao
 */
public class SoupServings {
    /*
    有 A 和 B 两种类型 的汤。一开始每种类型的汤有 n 毫升。有四种分配操作：
    提供 100ml 的 汤A 和 0ml 的 汤B 。
    提供 75ml 的 汤A 和 25ml 的 汤B 。
    提供 50ml 的 汤A 和 50ml 的 汤B 。
    提供 25ml 的 汤A 和 75ml 的 汤B 。
    当我们把汤分配给某人之后，汤就没有了。每个回合，我们将从四种概率同为 0.25 的操作中进行分配选择。如果汤的剩余量不足以完成某次操作，我们将尽可能分配。
    当两种类型的汤都分配完时，停止操作。
    注意：不存在先分配 100 ml 汤B 的操作。
    需要返回的值： 汤A 先分配完的概率 +  汤A和汤B 同时分配完的概率 / 2。返回值在正确答案 10-5 的范围内将被认为是正确的。

    示例 1:
        输入: n = 50
        输出: 0.62500
        解释:如果我们选择前两个操作，A 首先将变为空。
        对于第三个操作，A 和 B 会同时变为空。
        对于第四个操作，B 首先将变为空。
        所以 A 变为空的总概率加上 A 和 B 同时变为空的概率的一半是 0.25 *(1 + 1 + 0.5 + 0)= 0.625。
    示例 2:
        输入: n = 100
        输出: 0.71875

    提示:
        0 <= n <= 10^9
    */

    public static void main(String[] args) {
        int n = 50;
        int n2 = 100;
        System.out.println(soupServings(n));
        System.out.println(soupServings(n2));
    }


    private static double soupServings(int n) {
        n = (int) Math.ceil((double) n / 25);
        if (n >= 179) {
            return 1.0;
        }
        double[][] visited = new double[n + 1][n + 1];
        return soupServings(n, n, visited);
    }

    private static double soupServings(int a, int b, double[][] visited) {
        if (a <= 0 && b <= 0) {
            return 0.5;
        }
        if (a <= 0) {
            return 1;
        }
        if (b <= 0) {
            return 0;
        }

        if (visited[a][b] == 0) {
            // 执行操作1
            double p1 = soupServings(a - 4, b, visited);
            // 执行操作2
            double p2 = soupServings(a - 3, b - 1, visited);
            // 执行操作3
            double p3 = soupServings(a - 2, b - 2, visited);
            // 执行操作4
            double p4 = soupServings(a - 1, b - 3, visited);

            visited[a][b] = (p1 + p2 + p3 + p4) / 4;
        }

        return visited[a][b];
    }

}
