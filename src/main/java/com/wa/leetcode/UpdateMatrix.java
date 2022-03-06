package com.wa.leetcode;

import java.util.Arrays;

/**
 * UpdateMatrix
 * https://leetcode-cn.com/problems/2bCMpM/
 * <p>
 * 2022-02-23 09:03
 *
 * @author wuao
 */
public class UpdateMatrix {

    public static void main(String[] args) {
        int[][] mat = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int[][] mat2 = new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};

        System.out.println(Arrays.deepToString(updateMatrix(mat)));
        System.out.println(Arrays.deepToString(updateMatrix(mat2)));
    }

    private static int[][] updateMatrix(int[][] mat) {
        int[][] dp = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = mat.length + mat[0].length;
                }
            }
        }

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] != 0) {
                    if (i > 0) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                    }
                    if (j > 0) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                    }
                }
            }
        }

        for (int i = mat.length - 1; i >= 0; i--) {
            for (int j = mat[0].length - 1; j >= 0; j--) {
                if (mat[i][j] != 0) {
                    if (i < mat.length - 1) {
                        dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);
                    }
                    if (j < mat[0].length - 1) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1);
                    }
                }
            }
        }

        return dp;
    }
}
