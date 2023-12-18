package com.wa.leetcode;

/**
 * PossibleToStamp
 * https://leetcode.cn/problems/stamping-the-grid
 * 2132. 用邮票贴满网格图
 *
 * @Date: 2023/12/14 11:04
 * @Author: wuao
 */
public class PossibleToStamp {

    /*
    给你一个 m x n 的二进制矩阵 grid ，每个格子要么为 0 （空）要么为 1 （被占据）。
    给你邮票的尺寸为 stampHeight x stampWidth 。我们想将邮票贴进二进制矩阵中，且满足以下 限制 和 要求 ：
    覆盖所有 空 格子。
    不覆盖任何 被占据 的格子。
    我们可以放入任意数目的邮票。
    邮票可以相互有 重叠 部分。
    邮票不允许 旋转 。
    邮票必须完全在矩阵 内 。
    如果在满足上述要求的前提下，可以放入邮票，请返回 true ，否则返回 false 。

    示例 1：
        输入：grid = [[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0]], stampHeight = 4, stampWidth = 3
        输出：true
        解释：我们放入两个有重叠部分的邮票（图中标号为 1 和 2），它们能覆盖所有与空格子。
    示例 2：
        输入：grid = [[1,0,0,0],[0,1,0,0],[0,0,1,0],[0,0,0,1]], stampHeight = 2, stampWidth = 2
        输出：false
        解释：没办法放入邮票覆盖所有的空格子，且邮票不超出网格图以外。

    提示：
        m == grid.length
        n == grid[r].length
        1 <= m, n <= 105
        1 <= m * n <= 2 * 105
        grid[r][c] 要么是 0 ，要么是 1 。
        1 <= stampHeight, stampWidth <= 105
    */

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}};
        int stampHeight = 4, stampWidth = 3;

        int[][] grid2 = new int[][]{{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        int stampHeight2 = 2, stampWidth2 = 2;

        int[][] grid3 = new int[][]{{0}, {0}, {0}, {0}, {0}, {0}};
        int stampHeight3 = 6, stampWidth3 = 1;

        int[][] grid4 = new int[][]{{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 0, 1}, {0, 0, 0, 1, 1}};
        int stampHeight4 = 2, stampWidth4 = 2;

        System.out.println(possibleToStamp(grid, stampHeight, stampWidth));
        System.out.println(possibleToStamp(grid2, stampHeight2, stampWidth2));
        System.out.println(possibleToStamp(grid3, stampHeight3, stampWidth3));
    }

    public static boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        int m = grid.length, n = grid[0].length;
        int[][][] cnt = new int[m][n][4];
        // 从左向右遍历
        for (int i = 0; i < m; i++) {
            cnt[i][0][0] = grid[i][0] == 0 ? 1 : 0;
            for (int j = 1; j < n; j++) {
                if (grid[i][j] == 1) {
                    cnt[i][j][0] = 0;
                } else {
                    cnt[i][j][0] = cnt[i][j - 1][0] + 1;
                }
            }
        }
        // 从右向左遍历
        for (int i = 0; i < m; i++) {
            cnt[i][n - 1][1] = grid[i][n - 1] == 0 ? 1 : 0;
            for (int j = n - 2; j >= 0; j--) {
                if (grid[i][j] == 1) {
                    cnt[i][j][1] = 0;
                } else {
                    cnt[i][j][1] = cnt[i][j + 1][1] + 1;
                }
            }
        }
        // 从上向下遍历
        for (int i = 0; i < n; i++) {
            cnt[0][i][2] = grid[0][i] == 0 ? 1 : 0;
            for (int j = 1; j < m; j++) {
                if (grid[j][i] == 1) {
                    cnt[j][i][2] = 0;
                } else {
                    cnt[j][i][2] = cnt[j - 1][i][2] + 1;
                }
            }
        }
        // 从下向上遍历
        for (int i = 0; i < n; i++) {
            cnt[m - 1][i][3] = grid[m - 1][i] == 0 ? 1 : 0;
            for (int j = m - 2; j >= 0; j--) {
                if (grid[j][i] == 1) {
                    cnt[j][i][3] = 0;
                } else {
                    cnt[j][i][3] = cnt[j + 1][i][3] + 1;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    continue;
                }
                if (cnt[i][j][0] + cnt[i][j][1] - 1 < stampWidth) {
                    return false;
                }
                if (cnt[i][j][2] + cnt[i][j][3] - 1 < stampHeight) {
                    return false;
                }
            }
        }
        return true;
    }
}
