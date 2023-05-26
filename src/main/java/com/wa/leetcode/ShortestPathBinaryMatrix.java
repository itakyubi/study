package com.wa.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * ShortestPathBinaryMatrix
 * https://leetcode.cn/problems/shortest-path-in-binary-matrix/
 * 1091. 二进制矩阵中的最短路径
 * 2023/5/26 9:14 AM
 *
 * @author wuao
 */
public class ShortestPathBinaryMatrix {

    /*
    给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径 的长度。如果不存在这样的路径，返回 -1 。
    二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角 单元格（即，(n - 1, n - 1)）的路径，该路径同时满足下述要求：
    路径途经的所有单元格都的值都是 0 。
    路径中所有相邻的单元格应当在 8 个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。
    畅通路径的长度 是该路径途经的单元格总数。

    示例 1：
        输入：grid = [[0,1],[1,0]]
        输出：2
    示例 2：
        输入：grid = [[0,0,0],[1,1,0],[1,1,0]]
        输出：4
    示例 3：
        输入：grid = [[1,0,0],[1,1,0],[1,1,0]]
        输出：-1

    提示：
        n == grid.length
        n == grid[i].length
        1 <= n <= 100
        grid[i][j] 为 0 或 1
    */

    public static void main(String[] args) {
        int[][] grid = {{0, 1}, {1, 0}};
        int[][] grid2 = {{0, 0, 0}, {1, 1, 0}, {1, 1, 0}};
        int[][] grid3 = {{1, 0, 0}, {1, 1, 0}, {1, 1, 0}};
        int[][] grid4 = {{0, 1, 1, 0, 0, 0}, {0, 1, 0, 1, 1, 0}, {0, 1, 1, 0, 1, 0}, {0, 0, 0, 1, 1, 0}, {1, 1, 1, 1, 1, 0}, {1, 1, 1, 1, 1, 0}};

        System.out.println(shortestPathBinaryMatrix2(grid));
        System.out.println(shortestPathBinaryMatrix2(grid2));
        System.out.println(shortestPathBinaryMatrix2(grid3));
        System.out.println(shortestPathBinaryMatrix2(grid4));
    }

    // 只考虑了往右、下、右下走的情况，还有往左、上、左上、右上、左下的情况
    private static int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] == 1) {
            return -1;
        }

        int n = grid.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        dp[0][0] = 1;
        for (int i = 1; i < n; i++) {
            if (grid[i][0] == 0 && dp[i - 1][0] != Integer.MAX_VALUE) {
                dp[i][0] = dp[i - 1][0] + 1;
            }
        }
        for (int i = 1; i < n; i++) {
            if (grid[0][i] == 0 && dp[0][i - 1] != Integer.MAX_VALUE) {
                dp[0][i] = dp[0][i - 1] + 1;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] == 0) {
                    // dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1],dp[i-1][j-1]) + 1;
                    if (dp[i - 1][j] != Integer.MAX_VALUE || dp[i][j - 1] != Integer.MAX_VALUE || dp[i - 1][j - 1] != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    }
                }
            }
        }

        if (dp[n - 1][n - 1] == Integer.MAX_VALUE) {
            return -1;
        }
        return dp[n - 1][n - 1];
    }

    // bfs
    private static int shortestPathBinaryMatrix2(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        int res = 0;
        while (!queue.isEmpty()) {
            res++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] tmp = queue.poll();
                int x = tmp[0], y = tmp[1];
                if (x == n - 1 && y == n - 1) {
                    return res;
                }

                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        int x1 = x + dx;
                        int y1 = y + dy;
                        if (x1 >= 0 && x1 < n && y1 >= 0 && y1 < n && grid[x1][y1] != 1) {
                            grid[x1][y1] = 1;
                            queue.offer(new int[]{x1, y1});
                        }
                    }
                }
            }
        }

        return -1;
    }


}
