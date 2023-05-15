package com.wa.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * MaxEqualRowsAfterFlips
 * https://leetcode.cn/problems/flip-columns-for-maximum-number-of-equal-rows/
 * 1072. 按列翻转得到最大值等行数
 * 2023/5/15 6:23 PM
 *
 * @author wuao
 */
public class MaxEqualRowsAfterFlips {

    /*
    给定m x n矩阵matrix。
    你可以从中选出任意数量的列并翻转其上的每个单元格。（即翻转后，单元格的值从 0 变成 1，或者从 1 变为 0 。）
    返回 经过一些翻转后，行与行之间所有值都相等的最大行数。

    示例 1：
        输入：matrix = [[0,1],[1,1]]
        输出：1
        解释：不进行翻转，有 1 行所有值都相等。
    示例 2：
        输入：matrix = [[0,1],[1,0]]
        输出：2
        解释：翻转第一列的值之后，这两行都由相等的值组成。
    示例 3：
        输入：matrix = [[0,0,0],[0,0,1],[1,1,0]]
        输出：2
        解释：翻转前两列的值之后，后两行由相等的值组成。
            
    提示：
        m == matrix.length
        n == matrix[i].length
        1 <= m, n <= 300
        matrix[i][j] == 0 或1
    */

    public static void main(String[] args) {
        int[][] matrix = {{0, 1}, {1, 1}};
        int[][] matrix2 = {{0, 1}, {1, 0}};
        int[][] matrix3 = {{0, 0, 0}, {0, 0, 1}, {1, 1, 0}};

        System.out.println(maxEqualRowsAfterFlips(matrix));
        System.out.println(maxEqualRowsAfterFlips(matrix2));
        System.out.println(maxEqualRowsAfterFlips(matrix3));
    }


    public static int maxEqualRowsAfterFlips(int[][] matrix) {
        Map<String, Integer> map = new HashMap<>();
        int max = 0;
        for (int[] ints : matrix) {
            StringBuilder tmp = new StringBuilder();
            for (int j = 0; j < matrix[0].length; j++) {
                if (ints[0] == 1) {
                    tmp.append(ints[j] ^ 1);
                } else {
                    tmp.append(ints[j]);
                }
            }
            String key = tmp.toString();
            map.put(key, map.getOrDefault(key, 0) + 1);
            max = Math.max(max, map.get(key));
        }
        return max;
    }
}
