package com.wa.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * FirstCompleteIndex
 * https://leetcode.cn/problems/first-completely-painted-row-or-column
 * 2661. 找出叠涂元素
 *
 * @Date: 2023/12/1 8:20
 * @Author: wuao
 */
public class FirstCompleteIndex {

    /*
    给你一个下标从 0 开始的整数数组 arr 和一个 m x n 的整数 矩阵 mat 。
    arr 和 mat 都包含范围 [1，m * n] 内的 所有 整数。
    从下标 0 开始遍历 arr 中的每个下标 i ，并将包含整数 arr[i] 的 mat 单元格涂色。
    请你找出 arr 中在 mat 的某一行或某一列上都被涂色且下标最小的元素，并返回其下标 i 。

    示例 1：
        输入：arr = [1,3,4,2], mat = [[1,4],[2,3]]
        输出：2
        解释：遍历如上图所示，arr[2] 在矩阵中的第一行或第二列上都被涂色。
    示例 2：
        输入：arr = [2,8,7,4,1,3,5,6,9], mat = [[3,2,5],[1,4,6],[8,7,9]]
        输出：3
        解释：遍历如上图所示，arr[3] 在矩阵中的第二列上都被涂色。

    提示：
        m == mat.length
        n = mat[i].length
        arr.length == m * n
        1 <= m, n <= 105
        1 <= m * n <= 105
        1 <= arr[i], mat[r][c] <= m * n
        arr 中的所有整数 互不相同
        mat 中的所有整数 互不相同
    */

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2};
        int[][] mat = {{1, 4}, {2, 3}};

        int[] arr2 = {2, 8, 7, 4, 1, 3, 5, 6, 9};
        int[][] mat2 = {{3, 2, 5}, {1, 4, 6}, {8, 7, 9}};

        int[] arr3 = {1, 4, 5, 2, 6, 3};
        int[][] mat3 = {{4, 3, 5}, {1, 2, 6}};

        System.out.println(firstCompleteIndex(arr, mat));
        System.out.println(firstCompleteIndex(arr2, mat2));
        System.out.println(firstCompleteIndex(arr3, mat3));
    }

    public static int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length, n = mat[0].length;
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map.put(mat[i][j], new int[]{i, j});
            }
        }

        int[] rowCnt = new int[m];
        int[] colCnt = new int[n];
        for (int i = 0; i < arr.length; i++) {
            int row = map.get(arr[i])[0];
            int col = map.get(arr[i])[1];
            rowCnt[row]++;
            colCnt[col]++;
            if (rowCnt[row] == n || colCnt[col] == m) {
                return i;
            }
        }
        return -1;
    }

}
