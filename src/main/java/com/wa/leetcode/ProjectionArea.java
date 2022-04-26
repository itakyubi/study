package com.wa.leetcode;

/**
 * ProjectionArea
 * 2022-04-26 17:00
 *
 * @author wuao
 */
public class ProjectionArea {

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1, 2}, {3, 4}};
        int[][] grid2 = new int[][]{{2}};
        int[][] grid3 = new int[][]{{1, 0}, {0, 2}};

        System.out.println(projectionArea(grid));
        System.out.println(projectionArea(grid2));
        System.out.println(projectionArea(grid3));
    }

    private static int projectionArea(int[][] grid) {
        int countXY = 0, countXZ = 0, countYZ = 0;
        int[] maxYZ = new int[grid[0].length];

        for (int x = 0; x < grid.length; x++) {
            int max = 0;
            for (int y = 0; y < grid[0].length; y++) {
                if (grid[x][y] != 0) {
                    countXY++;
                }
                max = Math.max(max, grid[x][y]);
                maxYZ[y] = Math.max(maxYZ[y], grid[x][y]);
            }
            countXZ += max;
        }

        for (int max : maxYZ) {
            countYZ += max;
        }

        return countXY + countXZ + countYZ;
    }
}
