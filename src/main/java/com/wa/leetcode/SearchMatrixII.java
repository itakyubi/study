package com.wa.leetcode;

public class SearchMatrixII {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        int rows = matrix.length, cols = matrix[0].length;
        int i = 0, j = cols - 1;
        while (i < rows && j >= 0) {
            if (target == matrix[i][j]) {
                return true;
            } else if (target > matrix[i][j]) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }
}
