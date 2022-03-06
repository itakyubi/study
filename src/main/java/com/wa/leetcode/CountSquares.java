package com.wa.leetcode;

/**
 * CountSquares
 * 2022-02-17 09:01
 *
 * @author wuao
 */
public class CountSquares {

    public static void main(String[] args) {
        int[][] nums = new int[][]{{0, 1, 1, 1}, {1, 1, 1, 1}, {0, 1, 1, 1}};
        int[][] nums2 = new int[][]{{1, 0, 1}, {1, 1, 0}, {1, 1, 0}};
        System.out.println(countSquares(nums));
        System.out.println(countSquares(nums2));
    }

    private static int countSquares(int[][] matrix) {
        int count = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j];
                } else if (matrix[i][j] == 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
                count += dp[i][j];
            }
        }
        return count;
    }


}
