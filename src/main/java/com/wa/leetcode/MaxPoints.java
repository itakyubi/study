package com.wa.leetcode;

/**
 * MaxPoints
 * 2022-02-15 09:16
 *
 * @author wuao
 */
public class MaxPoints {

    public static void main(String[] args) {
        int[][] nums = new int[][]{{1, 2, 3}, {1, 5, 1}, {3, 1, 1}};
        //int[][] nums2 = new int[][]{{1, 5}, {2, 3}, {4, 2}};
        //int[][] nums3 = new int[][]{{0}};
        //int[][] nums4 = new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        System.out.println(maxPoints2(nums));
        //System.out.println(maxPoints2(nums2));
        //System.out.println(maxPoints2(nums3));
        //System.out.println(maxPoints2(nums4));
    }


    public static long maxPoints(int[][] points) {
        int rows = points.length;
        int cols = points[0].length;
        long[][] dp = new long[rows][cols];

        long max = Long.MIN_VALUE;
        for (int i = 0; i < cols; i++) {
            dp[0][i] = points[0][i];
            max = Math.max(max, dp[0][i]);
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                long tmpMax = Long.MIN_VALUE;
                for (int k = 0; k < cols; k++) {
                    tmpMax = Math.max(tmpMax, dp[i - 1][k] - Math.abs(k - j));
                }
                dp[i][j] = points[i][j] + tmpMax;
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    public static long maxPoints2(int[][] points) {
        int rows = points.length;
        int cols = points[0].length;
        long[] dp = new long[cols];

        for (int i = 0; i < rows; i++) {
            long[] tmp = new long[cols];

            long tmpMax = Long.MIN_VALUE;
            for (int j = 0; j < cols; j++) {
                tmpMax = Math.max(tmpMax, dp[j] + j);
                tmp[j] = Math.max(tmp[j], points[i][j] + tmpMax - j);
            }
            printArray(tmp);


            tmpMax = Long.MIN_VALUE;
            for (int j = cols - 1; j >= 0; j--) {
                tmpMax = Math.max(tmpMax, dp[j] - j);
                tmp[j] = Math.max(tmp[j], points[i][j] + tmpMax + j);
            }

            printArray(tmp);

            dp = tmp;
        }

        long max = Long.MIN_VALUE;
        for (int i = 0; i < cols; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    private static void printArray(long[] nums) {
        for (long t : nums) {
            System.out.print(t);
            System.out.print(",");
        }
        System.out.println();
    }

}
