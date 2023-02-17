package com.wa.leetcode;

/**
 * Largest1BorderedSquare
 * https://leetcode.cn/problems/largest-1-bordered-square/
 * 1139. 最大的以 1 为边界的正方形
 * 2023/2/17 8:54 上午
 *
 * @author wuao
 */
public class Largest1BorderedSquare {

    /*
    给你一个由若干 0 和 1 组成的二维网格grid，请你找出边界全部由 1 组成的最大 正方形 子网格，并返回该子网格中的元素数量。如果不存在，则返回 0。

    示例 1：
        输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
        输出：9
    示例 2：
        输入：grid = [[1,1,0,0]]
        输出：1

    提示：
        1 <= grid.length <= 100
        1 <= grid[0].length <= 100
        grid[i][j] 为0或1
    */

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        int[][] grid2 = new int[][]{{1, 1, 0, 0}};

        System.out.println(largest1BorderedSquare(grid));
        System.out.println(largest1BorderedSquare(grid2));
    }

    private static int largest1BorderedSquare(int[][] grid) {
        // 计算每个点的左边和上边的前缀和
        // 因为是从左上角开始遍历，如果从右下角开始遍历，则使用右边和下边的前缀和
        int row = grid.length, col = grid[0].length;
        int[][] lefts = new int[row + 1][col + 1];
        int[][] ups = new int[row + 1][col + 1];
        int maxBound = 0;
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (grid[i - 1][j - 1] == 1) {
                    // 点(i,j)左边和上边连续1的个数
                    // 类似于前缀和，但是如果某个点为0，则会将当前前缀和重置为0
                    lefts[i][j] = lefts[i][j - 1] + 1;
                    ups[i][j] = ups[i - 1][j] + 1;
                    // 取左边和上边最小的，作为正方形的边界上限
                    // 该边界长度即为正方形下边和右边的长度
                    int bound = Math.min(lefts[i][j], ups[i][j]);
                    // 检验以(i,j)为右下角，边长为bound的正方形，的上边和左边满足边长bound(即大于等于bound)
                    while (lefts[i - bound + 1][j] < bound || ups[i][j - bound + 1] < bound) {
                        bound--;
                    }
                    maxBound = Math.max(maxBound, bound);
                }
            }
        }
        return maxBound * maxBound;
    }
}
