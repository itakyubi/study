package com.wa.leetcode;

/**
 * ValidSquare
 * https://leetcode.cn/problems/valid-square/
 * 593. 有效的正方形
 * 2022/7/29 4:09 下午
 *
 * @author wuao
 */
public class ValidSquare {

    /*
    给定2D空间中四个点的坐标p1,p2,p3和p4，如果这四个点构成一个正方形，则返回 true 。
    点的坐标pi 表示为 [xi, yi] 。输入 不是 按任何顺序给出的。
    一个 有效的正方形 有四条等边和四个等角(90度角)。

    示例 1:
        输入: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
        输出: True
    示例 2:
        输入：p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,12]
        输出：false
    示例 3:
        输入：p1 = [1,0], p2 = [-1,0], p3 = [0,1], p4 = [0,-1]
        输出：true

    提示:
        p1.length == p2.length == p3.length == p4.length == 2
        -104<= xi, yi<= 104
    */

    public static void main(String[] args) {
        int[] p1 = new int[]{0, 0}, p2 = new int[]{1, 1}, p3 = new int[]{1, 0}, p4 = new int[]{0, 1};
        int[] p11 = new int[]{0, 0}, p22 = new int[]{1, 1}, p33 = new int[]{1, 0}, p44 = new int[]{0, 12};
        int[] p111 = new int[]{1, 0}, p222 = new int[]{-1, 0}, p333 = new int[]{0, 1}, p444 = new int[]{0, -1};

        System.out.println(validSquare(p1, p2, p3, p4));
        System.out.println(validSquare(p11, p22, p33, p44));
        System.out.println(validSquare(p111, p222, p333, p444));
    }

    private static boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        return valid(p1, p2, p3) && valid(p1, p2, p4) && valid(p1, p3, p4) && valid(p2, p3, p4);
    }

    private static boolean valid(int[] a, int[] b, int[] c) {
        int d1 = (a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]);
        int d2 = (a[0] - c[0]) * (a[0] - c[0]) + (a[1] - c[1]) * (a[1] - c[1]);
        int d3 = (c[0] - b[0]) * (c[0] - b[0]) + (c[1] - b[1]) * (c[1] - b[1]);
        return (d1 == d2 && d1 + d2 == d3 && d3 > d1)
                || (d1 == d3 && d1 + d3 == d2 && d2 > d1)
                || (d2 == d3 && d2 + d3 == d1 && d1 > d2);
    }
}
