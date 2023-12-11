package com.wa.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * MinimumEffortPath
 * https://leetcode.cn/problems/path-with-minimum-effort
 * 1631. 最小体力消耗路径
 *
 * @Date: 2023/12/11 9:21
 * @Author: wuao
 */
public class MinimumEffortPath {

    /*
    你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，
    其中 heights[row][col] 表示格子 (row, col) 的高度。一开始你在最左上角的格子 (0, 0) ，
    且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。
    你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
    一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
    请你返回从左上角走到右下角的最小 体力消耗值 。

    示例 1：
        输入：heights = [[1,2,2],[3,8,2],[5,3,5]]
        输出：2
        解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
        这条路径比路径 [1,2,2,2,5] 更优，因为另一条路径差值最大值为 3 。
    示例 2：
        输入：heights = [[1,2,3],[3,8,4],[5,3,5]]
        输出：1
        解释：路径 [1,2,3,4,5] 的相邻格子差值绝对值最大为 1 ，比路径 [1,3,5,3,5] 更优。
    示例 3：
        输入：heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
        输出：0
        解释：上图所示路径不需要消耗任何体力。

    提示：
        rows == heights.length
        columns == heights[i].length
        1 <= rows, columns <= 100
        1 <= heights[i][j] <= 10^6
    */

    public static void main(String[] args) {
        int[][] heights = new int[][]{{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
        int[][] heights2 = new int[][]{{1, 2, 3}, {3, 8, 4}, {5, 3, 5}};
        int[][] heights3 = new int[][]{{1, 2, 1, 1, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 1, 1, 2, 1}};

        System.out.println(minimumEffortPath(heights));
        System.out.println(minimumEffortPath(heights2));
        System.out.println(minimumEffortPath(heights3));
    }

    public static int minimumEffortPath(int[][] heights) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m = heights.length, n = heights[0].length;
        int left = 0, right = 999999, res = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{0, 0});
            boolean[] seen = new boolean[m * n];
            seen[0] = true;
            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                int x = cell[0], y = cell[1];
                for (int i = 0; i < dirs.length; i++) {
                    int nx = x + dirs[i][0];
                    int ny = y + dirs[i][1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && !seen[nx * n + ny] && Math.abs(heights[x][y] - heights[nx][ny]) <= mid) {
                        queue.offer(new int[]{nx, ny});
                        seen[nx * n + ny] = true;
                    }
                }
            }
            if (seen[m * n - 1]) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

}
