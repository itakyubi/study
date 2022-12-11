package com.wa.leetcode;

import java.util.Arrays;

/**
 * MaxHeight
 * https://leetcode.cn/problems/maximum-height-by-stacking-cuboids/
 * 1691. 堆叠长方体的最大高度
 * 2022/12/11 4:38 下午
 *
 * @author wuao
 */
public class MaxHeight {

    /*
    给你 n 个长方体 cuboids ，其中第 i 个长方体的长宽高表示为 cuboids[i] = [widthi, lengthi, heighti]（下标从 0 开始）。请你从 cuboids 选出一个 子集 ，并将它们堆叠起来。
    如果 widthi <= widthj 且 lengthi <= lengthj 且 heighti <= heightj ，你就可以将长方体 i 堆叠在长方体 j 上。你可以通过旋转把长方体的长宽高重新排列，以将它放在另一个长方体上。
    返回 堆叠长方体cuboids 可以得到的 最大高度 。

    示例 1：
        输入：cuboids = [[50,45,20],[95,37,53],[45,23,12]]
        输出：190
        解释：
        第 1 个长方体放在底部，53x37 的一面朝下，高度为 95 。
        第 0 个长方体放在中间，45x20 的一面朝下，高度为 50 。
        第 2 个长方体放在上面，23x12 的一面朝下，高度为 45 。
        总高度是 95 + 50 + 45 = 190 。
    示例 2：
        输入：cuboids = [[38,25,45],[76,35,3]]
        输出：76
        解释：
        无法将任何长方体放在另一个上面。
        选择第 1 个长方体然后旋转它，使 35x3 的一面朝下，其高度为 76 。
    示例 3：
        输入：cuboids = [[7,11,17],[7,17,11],[11,7,17],[11,17,7],[17,7,11],[17,11,7]]
        输出：102
        解释：
        重新排列长方体后，可以看到所有长方体的尺寸都相同。
        你可以把 11x7 的一面朝下，这样它们的高度就是 17 。
        堆叠长方体的最大高度为 6 * 17 = 102 。

    提示：
        n == cuboids.length
        1 <= n <= 100
        1 <= widthi, lengthi, heighti <= 100
    */

    public static void main(String[] args) {
        int[][] cuboids = new int[][]{{50, 45, 20}, {95, 37, 53}, {45, 23, 12}};
        int[][] cuboids2 = new int[][]{{38, 25, 45}, {76, 35, 3}};
        int[][] cuboids3 = new int[][]{{7, 11, 17}, {7, 17, 11}, {11, 7, 17}, {11, 17, 7}, {17, 7, 11}, {17, 11, 7}};
        int[][] cuboids4 = new int[][]{{100, 75, 20}, {93, 99, 97}, {84, 12, 7}, {53, 21, 87}, {70, 100, 88}, {57, 32, 73}, {
                95, 86, 93}, {86, 94, 59}, {22, 55, 55}, {73, 77, 35}, {39, 51, 49}, {50, 83, 52}, {10, 8, 56}, {27, 89, 30}, {
                29, 82, 28}, {20, 62, 17}, {36, 64, 51}, {84, 30, 30}, {55, 34, 45}, {2, 48, 72}, {31, 19, 44}, {26, 33, 26}, {
                51, 65, 65}, {50, 69, 8}, {64, 37, 78}, {54, 97, 100}, {35, 23, 6}, {85, 9, 69}, {80, 16, 71}, {81, 40, 32}, {97, 62, 33}, {
                86, 46, 18}, {92, 67, 71}, {73, 98, 94}, {63, 77, 4}, {1, 84, 42}, {82, 90, 58}, {44, 18, 31}, {15, 11, 92}, {62, 1, 37}, {
                76, 40, 79}, {52, 36, 22}, {97, 13, 33}, {51, 82, 29}, {19, 93, 91}, {26, 92, 97}, {66, 46, 15}, {33, 65, 29}, {
                98, 98, 89}, {24, 5, 31}, {1, 76, 93}, {22, 95, 60}, {18, 49, 7}, {50, 5, 24}, {5, 21, 14}, {8, 73, 17}, {98, 61, 4}, {
                90, 38, 3}, {37, 75, 51}, {59, 92, 55}, {79, 15, 79}, {83, 40, 1}, {74, 10, 15}, {17, 42, 37}, {24, 35, 11}, {45, 1, 35}, {
                47, 9, 37}, {5, 83, 22}, {45, 34, 96}, {38, 18, 52}, {59, 24, 7}, {51, 47, 95}, {8, 90, 60}, {40, 89, 51}, {84, 59, 50}, {
                54, 6, 71}, {58, 74, 30}, {11, 57, 79}};

        System.out.println(maxHeight(cuboids));
        System.out.println(maxHeight(cuboids2));
        System.out.println(maxHeight(cuboids3));
        System.out.println(maxHeight(cuboids4));
    }

    private static int maxHeight2(int[][] cuboids) {
        // 将每个立方体的长宽高按照从小到大的顺序排列，第三个元素为高
        for (int[] cuboid : cuboids) {
            Arrays.sort(cuboid);
        }

        // 将立方体数组按照第三个元素、第二个元素、第一个元素从大到小的顺序排列
        Arrays.sort(cuboids, (a, b) -> {
            if (a[2] > b[2]) {
                return -1;
            } else if (a[2] < b[2]) {
                return 1;
            } else if (a[1] > b[1]) {
                return -1;
            } else if (a[1] < b[1]) {
                return 1;
            } else {
                return Integer.compare(b[0], a[0]);
            }
        });

        // dp[i] 代表放置第i个立方体后，最大高度
        // dp[i] = max(dp[j]) + cuboids[i][0], 其中 0<=j<i && cuboids[j][0] <= cuboids[i][0]
        // && cuboids[j][1] <= cuboids[i][1] && cuboids[j][2] <= cuboids[i][2]
        int[] dp = new int[cuboids.length + 1];
        for (int i = 0; i < cuboids.length; i++) {
            dp[i + 1] = cuboids[i][2];
            for (int j = i - 1; j >= 0; j--) {
                if (cuboids[j][0] >= cuboids[i][0] && cuboids[j][1] >= cuboids[i][1] && cuboids[j][2] >= cuboids[i][2]) {
                    dp[i + 1] = Math.max(dp[i + 1], dp[j + 1] + cuboids[i][2]);
                }
            }
        }

        int max = 0;
        for (int j : dp) {
            max = Math.max(max, j);
        }
        return max;
    }

    // 优化代码
    private static int maxHeight(int[][] cuboids) {
        // 将每个立方体的长宽高按照从小到大的顺序排列，第三个元素为高
        for (int[] cuboid : cuboids) {
            Arrays.sort(cuboid);
        }

        // 将立方体数组按照第三个元素、第二个元素、第一个元素从大到小的顺序排列
        Arrays.sort(cuboids, (a, b) -> {
            if (a[2] != b[2]) {
                return b[2] - a[2];
            }
            if (a[1] != b[1]) {
                return b[1] - a[1];
            }
            return b[0] - a[0];
        });
        //Arrays.sort(cuboids, (a, b) -> a[2] != b[2] ? b[2] - a[2] : a[1] != b[1] ? b[1] - a[1] : b[0] - a[0]);

        // dp[i] 代表放置第i个立方体后，最大高度
        // dp[i] = max(dp[j]) + cuboids[i][0], 其中 0<=j<i && cuboids[j][0] <= cuboids[i][0]
        // && cuboids[j][1] <= cuboids[i][1] && cuboids[j][2] <= cuboids[i][2]
        int max = 0;
        int[] dp = new int[cuboids.length + 1];
        for (int i = 0; i < cuboids.length; i++) {
            dp[i + 1] = cuboids[i][2];
            for (int j = i - 1; j >= 0; j--) {
                if (cuboids[j][0] >= cuboids[i][0] && cuboids[j][1] >= cuboids[i][1] && cuboids[j][2] >= cuboids[i][2]) {
                    dp[i + 1] = Math.max(dp[i + 1], dp[j + 1] + cuboids[i][2]);
                }
            }
            max = Math.max(max, dp[i + 1]);
        }
        return max;
    }
}
