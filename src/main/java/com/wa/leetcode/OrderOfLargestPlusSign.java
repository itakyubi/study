package com.wa.leetcode;

import java.util.Arrays;

/**
 * OrderOfLargestPlusSign
 * https://leetcode.cn/problems/largest-plus-sign/
 * 764. 最大加号标志
 * 2022/11/9 6:44 下午
 *
 * @author wuao
 */
public class OrderOfLargestPlusSign {

    /*
    在一个 n x n 的矩阵 grid 中，除了在数组 mines 中给出的元素为 0，其他每个元素都为 1。mines[i] = [xi, yi]表示 grid[xi][yi] == 0
    返回  grid 中包含 1 的最大的 轴对齐 加号标志的阶数 。如果未找到加号标志，则返回 0 。
    一个 k 阶由 1 组成的 “轴对称”加号标志 具有中心网格 grid[r][c] == 1 ，以及4个从中心向上、向下、向左、向右延伸，长度为 k-1，由 1 组成的臂。注意，只有加号标志的所有网格要求为 1 ，别的网格可能为 0 也可能为 1 。

    示例 1：
        输入: n = 5, mines = [[4, 2]]
        输出: 2
        解释: 在上面的网格中，最大加号标志的阶只能是2。一个标志已在图中标出。
    示例 2：
        输入: n = 1, mines = [[0, 0]]
        输出: 0
        解释: 没有加号标志，返回 0 。

    提示：
        1 <= n <= 500
        1 <= mines.length <= 5000
        0 <= xi, yi < n
        每一对 (xi, yi) 都 不重复
    */

    public static void main(String[] args) {
        int n = 5;
        int[][] mines = new int[][]{{4, 2}};

        int n2 = 1;
        int[][] mines2 = new int[][]{{0, 0}};

        int n3 = 2;
        int[][] mines3 = new int[][]{{0, 0}, {0, 1}, {1, 0}};

        int n4 = 2;
        int[][] mines4 = new int[][]{{0, 1}, {1, 0}, {1, 1}};

        System.out.println(orderOfLargestPlusSign(n, mines));
        System.out.println(orderOfLargestPlusSign(n2, mines2));
        System.out.println(orderOfLargestPlusSign(n3, mines3));
        System.out.println(orderOfLargestPlusSign(n4, mines4));
    }

    private static int orderOfLargestPlusSign(int n, int[][] mines) {
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(grid[i], 1);
        }
        for (int[] mine : mines) {
            grid[mine[0]][mine[1]] = 0;
        }

        // 上下遍历
        int[][] upDown = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(upDown[i],Integer.MAX_VALUE);
        }
        for (int i = 0; i < n; i++) {
            upDown[0][i] = grid[0][i];
            upDown[n - 1][i] = grid[n - 1][i];
        }
        for (int col = 0; col < n; col++) {
            for (int row = 1; row < n; row++) {
                if (grid[row][col] == 0) {
                    upDown[row][col] = 0;
                } else {
                    upDown[row][col] = Math.min(upDown[row][col], upDown[row - 1][col] + 1);
                }
                if (grid[n - 1 - row][col] == 0) {
                    upDown[n - 1 - row][col] = 0;
                } else {
                    upDown[n - 1 - row][col] = Math.min(upDown[n - 1 - row][col], upDown[n - 1 - row + 1][col] + 1);
                }
            }
        }

        // 左右遍历
        int[][] leftRight = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(leftRight[i],Integer.MAX_VALUE);
        }
        for (int i = 0; i < n; i++) {
            leftRight[i][0] = grid[i][0];
            leftRight[i][n - 1] = grid[i][n - 1];
        }
        for (int row = 0; row < n; row++) {
            for (int col = 1; col < n; col++) {
                if (grid[row][col] == 0) {
                    leftRight[row][col] = 0;
                } else {
                    leftRight[row][col] = Math.min(leftRight[row][col], leftRight[row][col - 1] + 1);
                }
                if (grid[row][n - 1 - col] == 0) {
                    leftRight[row][n - 1 - col] = 0;
                } else {
                    leftRight[row][n - 1 - col] = Math.min(leftRight[row][n - 1 - col], leftRight[row][n - 1 - col + 1] + 1);
                }

            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, Math.min(upDown[i][j], leftRight[i][j]));
            }
        }
        return max;
    }
}
