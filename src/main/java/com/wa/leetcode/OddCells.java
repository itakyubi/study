package com.wa.leetcode;

/**
 * OddCells
 * https://leetcode.cn/problems/cells-with-odd-values-in-a-matrix/
 * 1252. 奇数值单元格的数目
 * 2022-07-12 15:58
 *
 * @author wuao
 */
public class OddCells {

    /*
    给你一个 m x n 的矩阵，最开始的时候，每个单元格中的值都是 0。
    另有一个二维索引数组 indices，indices[i] = [ri, ci] 指向矩阵中的某个位置，其中 ri 和 ci 分别表示指定的行和列（从 0 开始编号）。
    对 indices[i] 所指向的每个位置，应同时执行下述增量操作：
    ri 行上的所有单元格，加 1 。
    ci 列上的所有单元格，加 1 。
    给你 m、n 和 indices 。请你在执行完所有 indices 指定的增量操作后，返回矩阵中 奇数值单元格 的数目。

    示例 1：
        输入：m = 2, n = 3, indices = [[0,1],[1,1]]
        输出：6
        解释：最开始的矩阵是 [[0,0,0],[0,0,0]]。
            第一次增量操作后得到 [[1,2,1],[0,1,0]]。
            最后的矩阵是 [[1,3,1],[1,3,1]]，里面有 6 个奇数。
    示例 2：
        输入：m = 2, n = 2, indices = [[1,1],[0,0]]
        输出：0
        解释：最后的矩阵是 [[2,2],[2,2]]，里面没有奇数。

    提示：
        1 <= m, n <= 50
        1 <= indices.length <= 100
        0 <= ri < m
        0 <= ci < n
 
    进阶：你可以设计一个时间复杂度为 O(n + m + indices.length) 且仅用 O(n + m) 额外空间的算法来解决此问题吗？
    */

    public static void main(String[] args) {
        int m = 2, n = 3;
        int[][] indices = new int[][]{{0, 1}, {1, 1}};

        int m2 = 2, n2 = 2;
        int[][] indices2 = new int[][]{{1, 1}, {0, 0}};

        System.out.println(oddCells(m, n, indices));
        System.out.println(oddCells(m2, n2, indices2));
    }

    private static int oddCells(int m, int n, int[][] indices) {
        int[] rows = new int[m];
        int[] cols = new int[n];

        for (int[] index : indices) {
            rows[index[0]]++;
            cols[index[1]]++;
        }

        int colOddCount = 0;
        for (int col : cols) {
            if ((col & 1) == 1) {
                colOddCount++;
            }
        }

        int rowOddCount = 0;
        for (int row : rows) {
            if ((row & 1) == 1) {
                rowOddCount++;
            }
        }

        return rowOddCount * (n - colOddCount) + (m - rowOddCount) * colOddCount;
    }
}
