package com.wa.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * EqualPairs
 * https://leetcode.cn/problems/equal-row-and-column-pairs/
 * 2352. 相等行列对
 * 2023/6/6 9:02 AM
 *
 * @author wuao
 */
public class EqualPairs {

    /*
    给你一个下标从 0 开始、大小为 n x n 的整数矩阵 grid ，返回满足 Ri 行和 Cj 列相等的行列对 (Ri, Cj) 的数目。
    如果行和列以相同的顺序包含相同的元素（即相等的数组），则认为二者是相等的。

    示例 1：
        输入：grid = [[3,2,1],[1,7,6],[2,7,7]]
        输出：1
        解释：存在一对相等行列对：
            - (第 2 行，第 1 列)：[2,7,7]
    示例 2：
        输入：grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
        输出：3
        解释：存在三对相等行列对：
            - (第 0 行，第 0 列)：[3,1,2,2]
            - (第 2 行, 第 2 列)：[2,4,2,2]
            - (第 3 行, 第 2 列)：[2,4,2,2]
            
    提示：
        n == grid.length == grid[i].length
        1 <= n <= 200
        1 <= grid[i][j] <= 105
    */

    public static void main(String[] args) {
        int[][] grid = {{3, 2, 1}, {1, 7, 6}, {2, 7, 7}};
        int[][] grid2 = {{3, 1, 2, 2}, {1, 4, 4, 5}, {2, 4, 2, 2}, {2, 4, 2, 2}};

        System.out.println(equalPairs(grid));
        System.out.println(equalPairs(grid2));
    }


    private static int equalPairs(int[][] grid) {
        Map<String, Integer> rows = new HashMap<>();
        Map<String, Integer> cols = new HashMap<>();
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            StringBuilder sbRow = new StringBuilder();
            StringBuilder sbCol = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sbRow.append(grid[i][j]);
                sbRow.append(",");

                sbCol.append(grid[j][i]);
                sbCol.append(",");
            }
            String strRow = sbRow.toString();
            String strCol = sbCol.toString();
            rows.put(strRow, rows.getOrDefault(strRow, 0) + 1);
            cols.put(strCol, cols.getOrDefault(strCol, 0) + 1);
        }

        int res = 0;
        for (Map.Entry<String, Integer> entry : rows.entrySet()) {
            if (cols.containsKey(entry.getKey())) {
                res += entry.getValue() * cols.get(entry.getKey());
            }
        }
        return res;
    }
}
