package com.wa.leetcode;

/**
 * GameOfLife
 * 2020-08-25 18:01
 *
 * @author wuao
 */
public class GameOfLife {

    public static void main(String[] args) {
        int[][] board = new int[][]{{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        gameOfLife(board);
    }

    private static int rows;
    private static int cols;

    public static void gameOfLife(int[][] board) {
        rows = board.length;
        cols = board[0].length;

        int[][] tmp = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(board[i], 0, tmp[i], 0, cols);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = getCellStatus(tmp, i, j);
            }
        }
    }

    private static int getCellStatus(int[][] tmp, int x, int y) {
        int liveCount = 0;

        int xStart = Math.max(0, x - 1);
        int xEnd = Math.min(x + 1, rows - 1);
        int yStart = Math.max(0, y - 1);
        int yEnd = Math.min(y + 1, cols - 1);

        for (int i = xStart; i <= xEnd; i++) {
            for (int j = yStart; j <= yEnd; j++) {
                if (!(i == x && j == y) && tmp[i][j] == 1)
                    liveCount++;
            }
        }

        if (tmp[x][y] == 1) {
            if (liveCount < 2 || liveCount > 3) {
                return 0;
            } else {
                return 1;
            }
        } else {
            if (liveCount == 3) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
