package com.wa.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * PacificAtlantic
 * 2022-04-27 18:53
 *
 * @author wuao
 */
public class PacificAtlantic {

    public static void main(String[] args) {
        int[][] heights = new int[][]{{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}};
        int[][] heights2 = new int[][]{{2, 1}, {1, 2}};

        PacificAtlantic pacificAtlantic = new PacificAtlantic();
        System.out.println(pacificAtlantic.pacificAtlantic(heights));
        System.out.println(pacificAtlantic.pacificAtlantic(heights2));
    }


    public PacificAtlantic() {
    }

    private int[][] heights;
    private boolean[][] visitedA, visitedP;
    private int rows, cols;

    private List<List<Integer>> pacificAtlantic(int[][] heights) {
        this.rows = heights.length;
        this.cols = heights[0].length;
        this.visitedA = new boolean[rows][cols];
        this.visitedP = new boolean[rows][cols];
        this.heights = heights;

        // 左边边界遍历
        for (int i = 0; i < rows; i++) {
            helper(i, 0, visitedA);
        }
        // 上边界遍历
        for (int j = 0; j < cols; j++) {
            helper(0, j, visitedA);
        }

        // 右边界遍历
        for (int i = 0; i < rows; i++) {
            helper(i, cols - 1, visitedP);
        }
        // 下边界遍历
        for (int j = 0; j < cols; j++) {
            helper(rows - 1, j, visitedP);
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (visitedA[i][j] && visitedP[i][j]) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    res.add(list);
                }
            }
        }
        return res;
    }

    private void helper(int i, int j, boolean[][] visited) {
        if (visited[i][j]) {
            return;
        }
        visited[i][j] = true;

        if (checkIndex(i - 1, j) && heights[i - 1][j] >= heights[i][j]) {
            helper(i - 1, j, visited);
        }
        if (checkIndex(i + 1, j) && heights[i + 1][j] >= heights[i][j]) {
            helper(i + 1, j, visited);
        }
        if (checkIndex(i, j - 1) && heights[i][j - 1] >= heights[i][j]) {
            helper(i, j - 1, visited);
        }
        if (checkIndex(i, j + 1) && heights[i][j + 1] >= heights[i][j]) {
            helper(i, j + 1, visited);
        }
    }

    private boolean checkIndex(int i, int j) {
        return i >= 0 && i < rows && j >= 0 && j < cols;
    }
}
