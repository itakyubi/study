package com.wa.leetcode;

/**
 * MaxValue2
 * https://leetcode.cn/problems/li-wu-de-zui-da-jie-zhi-lcof/
 * 剑指 Offer 47. 礼物的最大价值
 * 2023/3/8 9:13 上午
 *
 * @author wuao
 */
public class MaxValue2 {

    /*
    在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
    你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
    给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？

    示例 1:
        输入:
            [
             [1,3,1],
             [1,5,1],
             [4,2,1]
            ]
        输出: 12
        解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物

    提示：
        0 < grid.length <= 200
        0 < grid[0].length <= 200
    */

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};

        System.out.println(maxValue(grid));
    }

    private static int maxValue(int[][] grid) {
        // res[i][j] 记为取[i,j]位置的礼物后的最大价值
        // res[i][j] = max(res[i-1][j],res[i][j-1]) + grid[i][j];
        int row = grid.length, col = grid[0].length;
        int[][] res = new int[row][col];
        res[0][0] = grid[0][0];
        for (int i = 1; i < row; i++) {
            res[i][0] = res[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < col; j++) {
            res[0][j] = res[0][j - 1] + grid[0][j];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                res[i][j] = Math.max(res[i - 1][j], res[i][j - 1]) + grid[i][j];
            }
        }
        return res[row - 1][col - 1];
    }
}
