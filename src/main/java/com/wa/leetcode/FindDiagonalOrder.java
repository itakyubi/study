package com.wa.leetcode;

import java.util.Arrays;

/**
 * FindDiagonalOrder
 * https://leetcode-cn.com/problems/diagonal-traverse/
 * 2022-03-04 13:42
 *
 * @author wuao
 */
public class FindDiagonalOrder {
    public static void main(String[] args) {
        int[][] mat = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] mat2 = new int[][]{{1, 2}, {3, 4}};

        System.out.println(Arrays.toString(findDiagonalOrder(mat)));
        System.out.println(Arrays.toString(findDiagonalOrder(mat2)));
    }


    private static int[] findDiagonalOrder(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        int total = rows * cols;
        int[] res = new int[total];
        int index = 0;
        int i = 0, j = 0;

        while (res[total - 1] == 0) {
            // 往右上走
            while (i >= 0 && j < cols) {
                res[index++] = mat[i][j];
                i--;
                j++;
            }
            if (j >= cols) { // 碰到右边界
                i = i + 2;
                j = j - 1;
            } else {  // 碰到上边界
                i = i + 1;
            }

            // 往左下走
            while (i < rows && j >= 0) {
                res[index++] = mat[i][j];
                i++;
                j--;
            }
            if (i >= rows) { // 碰到下边界
                i = i - 1;
                j = j + 2;
            } else { // 碰到左边界
                j = j + 1;
            }

        }

        return res;
    }
}
