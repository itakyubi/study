package com.wa.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * MinimumMoves
 * https://leetcode.cn/problems/minimum-moves-to-reach-target-with-rotations/
 * 1210. 穿过迷宫的最少移动次数
 * 2023/2/5 4:27 下午
 *
 * @author wuao
 */
public class MinimumMoves {
    /*
    你还记得那条风靡全球的贪吃蛇吗？
    我们在一个n*n的网格上构建了新的迷宫地图，蛇的长度为 2，也就是说它会占去两个单元格。蛇会从左上角（(0, 0)和(0, 1)）开始移动。我们用 0 表示空单元格，用 1 表示障碍物。蛇需要移动到迷宫的右下角（(n-1, n-2)和(n-1, n-1)）。
    每次移动，蛇可以这样走：
    如果没有障碍，则向右移动一个单元格。并仍然保持身体的水平／竖直状态。
    如果没有障碍，则向下移动一个单元格。并仍然保持身体的水平／竖直状态。
    如果它处于水平状态并且其下面的两个单元都是空的，就顺时针旋转 90 度。蛇从（(r, c)、(r, c+1)）移动到 （(r, c)、(r+1, c)）。
    如果它处于竖直状态并且其右面的两个单元都是空的，就逆时针旋转 90 度。蛇从（(r, c)、(r+1, c)）移动到（(r, c)、(r, c+1)）。
    返回蛇抵达目的地所需的最少移动次数。
    如果无法到达目的地，请返回-1。

    示例 1：
        输入：grid = [[0,0,0,0,0,1],
                      [1,1,0,0,1,0],
                      [0,0,0,0,1,1],
                      [0,0,1,0,1,0],
                      [0,1,1,0,0,0],
                      [0,1,1,0,0,0]]
        输出：11
        解释：
        一种可能的解决方案是 [右, 右, 顺时针旋转, 右, 下, 下, 下, 下, 逆时针旋转, 右, 下]。
    示例 2：
        输入：grid = [[0,0,1,1,1,1],
                      [0,0,0,0,1,1],
                      [1,1,0,0,0,1],
                      [1,1,1,0,0,1],
                      [1,1,1,0,0,1],
                      [1,1,1,0,0,0]]
        输出：9

    提示：
        2 <= n <= 100
        0 <= grid[i][j] <= 1
        蛇保证从空单元格开始出发。
    */

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {0, 0, 0, 0, 0, 1},
                {1, 1, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 1},
                {0, 0, 1, 0, 1, 0},
                {0, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 0, 0}};
        int[][] grid2 = new int[][]{
                {0, 0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1, 1},
                {1, 1, 0, 0, 0, 1},
                {1, 1, 1, 0, 0, 1},
                {1, 1, 1, 0, 0, 1},
                {1, 1, 1, 0, 0, 0}};

        System.out.println(minimumMoves(grid));
        System.out.println(minimumMoves(grid2));
    }

    private static int minimumMoves(int[][] grid) {
        int n = grid.length;
        int[][][] dist = new int[n][n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dist[i][j], -1);
            }
        }
        dist[0][0][0] = 0;

        // 因为是广度优先遍历，所以如果遍历到同一位置，一定是先遍历到的次数最少
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0}); // 前两位为蛇尾坐标，最后一位为蛇的状态，0代表水平，1代表垂直
        while (!queue.isEmpty()) {
            int[] state = queue.poll();
            int x = state[0], y = state[1], status = state[2];
            if (x == n - 1 && y == n - 2 && status == 0) {
                // 此时蛇已经到终点位置，直接返回
                return dist[x][y][0];
            }
            if (status == 0) {
                // 水平状态下有三种移动方式
                // 向右移动
                if (y + 2 < n && dist[x][y + 1][0] == -1 && grid[x][y + 2] == 0) {
                    dist[x][y + 1][0] = dist[x][y][0] + 1;
                    queue.offer(new int[]{x, y + 1, 0});
                }

                // 向下移动
                if (x + 1 < n && dist[x + 1][y][0] == -1 && grid[x + 1][y] == 0 && grid[x + 1][y + 1] == 0) {
                    dist[x + 1][y][0] = dist[x][y][0] + 1;
                    queue.offer(new int[]{x + 1, y, 0});
                }
                // 顺时针旋转
                if (x + 1 < n && y + 1 < n && dist[x][y][1] == -1 && grid[x + 1][y] == 0 && grid[x + 1][y + 1] == 0) {
                    dist[x][y][1] = dist[x][y][0] + 1;
                    queue.offer(new int[]{x, y, 1});
                }
            } else {
                // 垂直状态下有三种移动方式
                // 向右移动
                if (y + 1 < n && dist[x][y + 1][1] == -1 && grid[x][y + 1] == 0 && grid[x + 1][y + 1] == 0) {
                    dist[x][y + 1][1] = dist[x][y][1] + 1;
                    queue.offer(new int[]{x, y + 1, 1});
                }
                // 向下移动
                if (x + 2 < n && dist[x + 1][y][1] == -1 && grid[x + 2][y] == 0) {
                    dist[x + 1][y][1] = dist[x][y][1] + 1;
                    queue.offer(new int[]{x + 1, y, 1});
                }
                // 逆时针移动
                if (x + 1 < n && y + 1 < n && dist[x][y][0] == -1 && grid[x][y + 1] == 0 && grid[x + 1][y + 1] == 0) {
                    dist[x][y][0] = dist[x][y][1] + 1;
                    queue.offer(new int[]{x, y, 0});
                }
            }
        }
        return -1;
    }
}
