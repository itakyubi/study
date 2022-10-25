package com.wa.leetcode;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ShortestBridge
 * https://leetcode.cn/problems/shortest-bridge/
 * 934. 最短的桥
 * 2022/10/25 6:15 下午
 *
 * @author wuao
 */
public class ShortestBridge {

    /*
    给你一个大小为 n x n 的二元矩阵 grid ，其中 1 表示陆地，0 表示水域。
    岛 是由四面相连的 1 形成的一个最大组，即不会与非组内的任何其他 1 相连。grid 中 恰好存在两座岛 。
    你可以将任意数量的 0 变为 1 ，以使两座岛连接起来，变成 一座岛 。
    返回必须翻转的 0 的最小数目。

    示例 1：
        输入：grid = [[0,1],[1,0]]
        输出：1
    示例 2：
        输入：grid = [[0,1,0],[0,0,0],[0,0,1]]
        输出：2
    示例 3：
        输入：grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
        输出：1

    提示：
        n == grid.length == grid[i].length
        2 <= n <= 100
        grid[i][j] 为 0 或 1
        grid 中恰有两个岛
    */

    public static void main(String[] args) {
        int[][] grid = new int[][]{{0, 1}, {1, 0}};
        int[][] grid2 = new int[][]{{0, 1, 0}, {0, 0, 0}, {0, 0, 1}};
        int[][] grid3 = new int[][]{{1, 1, 1, 1, 1}, {1, 0, 0, 0, 1}, {1, 0, 1, 0, 1}, {1, 0, 0, 0, 1}, {1, 1, 1, 1, 1}};

        System.out.println(shortestBridge(grid));
        System.out.println(shortestBridge(grid2));
        System.out.println(shortestBridge(grid3));
    }

    private static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static int shortestBridge(int[][] grid) {
        int n = grid.length;
        // 最外边的两层循环在找到第一个1时就会结束，因为一定会在本次循环中找到结果
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 找到其中一个岛的某个位置
                if (grid[i][j] != 1)
                    continue;

                // 标记第一个岛
                Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
                dfs(grid, i, j, queue);

                // 一圈一圈向外扩展，当扩展到1时说明到达第2个岛，此时扩展圈数就是结果
                int res = 0;
                while (!queue.isEmpty()) {
                    int size = queue.size();
                    for (int k = 0; k < size; k++) {
                        Pair<Integer, Integer> pair = queue.poll();
                        for (int[] dir : dirs) {
                            int x = pair.getKey() + dir[0];
                            int y = pair.getValue() + dir[1];
                            if (x >= 0 && x < n && y >= 0 && y < n) {
                                if (grid[x][y] == 1) {
                                    return res;
                                } else if (grid[x][y] == 0) {
                                    queue.offer(new Pair<>(x, y));
                                    grid[x][y] = -1;
                                }
                            }
                        }
                    }
                    res++;
                }
            }
        }
        return 0;
    }

    private static void dfs(int[][] grid, int i, int j, Queue<Pair<Integer, Integer>> queue) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid.length || grid[i][j] != 1) {
            return;
        }
        queue.offer(new Pair<>(i, j));
        grid[i][j] = -1;
        for (int[] dir : dirs) {
            dfs(grid, i + dir[0], j + dir[1], queue);
        }
    }
}
