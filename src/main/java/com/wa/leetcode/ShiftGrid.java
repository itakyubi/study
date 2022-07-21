package com.wa.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * ShiftGrid
 * https://leetcode.cn/problems/shift-2d-grid/
 * 1260. 二维网格迁移
 * 2022-07-21 15:16
 *
 * @author wuao
 */
public class ShiftGrid {

    /*
    给你一个 m 行 n 列的二维网格 grid 和一个整数 k。你需要将 grid 迁移 k 次。
    每次「迁移」操作将会引发下述活动：
    位于 grid[i][j] 的元素将会移动到 grid[i][j + 1]。
    位于 grid[i][n - 1] 的元素将会移动到 grid[i + 1][0]。
    位于 grid[m - 1][n - 1] 的元素将会移动到 grid[0][0]。
    请你返回 k 次迁移操作后最终得到的 二维网格。

    示例 1：
        输入：grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
        输出：[[9,1,2],[3,4,5],[6,7,8]]
    示例 2：
        输入：grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
        输出：[[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
    示例 3：
        输入：grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
        输出：[[1,2,3],[4,5,6],[7,8,9]]
             
    提示：
        m == grid.length
        n == grid[i].length
        1 <= m <= 50
        1 <= n <= 50
        -1000 <= grid[i][j] <= 1000
        0 <= k <= 100
    */

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int k = 1;
        int[][] grid2 = new int[][]{{3, 8, 1, 9}, {19, 7, 2, 5}, {4, 6, 11, 10}, {12, 0, 21, 13}};
        int k2 = 4;
        int[][] grid3 = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int k3 = 9;

        System.out.println(shiftGrid(grid, k));
        System.out.println(shiftGrid(grid2, k2));
        System.out.println(shiftGrid(grid3, k3));
    }

    private static List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int rows = grid.length, cols = grid[0].length;
        List<List<Integer>> res = new ArrayList<>(rows);
        for (int i = 0; i < rows; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                list.add(0);
            }
            res.add(list);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int newCol = (j + (k % cols)) % cols;
                int newRow = (i + k / cols + (j + (k % cols)) / cols) % rows;
                List<Integer> row = res.get(newRow);
                row.set(newCol, grid[i][j]);
            }
        }
        return res;
    }
}
