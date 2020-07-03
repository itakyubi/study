package com.wa.leetcode;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * com.wa.leetcode.NumIslands
 * 2020-07-02 16:17
 *
 * @author wuao
 */
public class NumIslands {

    private char[][] grid;
    private int row;
    private int col;

    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
            return 0;

        this.grid = grid;
        this.row = grid.length;
        this.col = grid[0].length;

        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    dfs(i, j);
                }
            }
        }
        return res;
    }

    private void dfs(int i, int j) {
        if (i > 0 && i < row && j > 0 && j < col && grid[i][j] == '1') {
            grid[i][j] = '0';
            dfs(i - 1, j);
            dfs(i + 1, j);
            dfs(i, j - 1);
            dfs(i, j + 1);
        }
    }

    public int numIslands2(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
            return 0;

        int row = grid.length;
        int col = grid[0].length;

        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    grid[i][j] = '0';

                    Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
                    queue.offer(new Pair<>(i, j));
                    while (!queue.isEmpty()) {
                        Pair<Integer, Integer> pair = queue.poll();
                        int r = pair.getKey();
                        int c = pair.getValue();

                        if (r - 1 >= 0 && grid[r - 1][c] == '1') {
                            grid[r - 1][c] = '0';
                            queue.offer(new Pair<>(r - 1, c));
                        }
                        if (r + 1 < row && grid[r + 1][c] == '1') {
                            grid[r + 1][c] = '0';
                            queue.offer(new Pair<>(r + 1, c));
                        }
                        if (c - 1 >= 0 && grid[r][c - 1] == '1') {
                            grid[r][c - 1] = '0';
                            queue.offer(new Pair<>(r, c - 1));
                        }
                        if (c + 1 < col && grid[r][c + 1] == '1') {
                            grid[r][c + 1] = '0';
                            queue.offer(new Pair<>(r, c + 1));
                        }
                    }
                }
            }
        }
        return res;
    }
}
