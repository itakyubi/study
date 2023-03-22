package com.wa.leetcode;

import java.util.Arrays;

/**
 * BestTeamScore
 * https://leetcode.cn/problems/best-team-with-no-conflicts/
 * 1626. 无矛盾的最佳球队
 * 2023/3/22 9:28 上午
 *
 * @author wuao
 */
public class BestTeamScore {

    /*
    假设你是球队的经理。对于即将到来的锦标赛，你想组合一支总体得分最高的球队。球队的得分是球队中所有球员的分数 总和 。
    然而，球队中的矛盾会限制球员的发挥，所以必须选出一支 没有矛盾 的球队。如果一名年龄较小球员的分数 严格大于 一名年龄较大的球员，则存在矛盾。同龄球员之间不会发生矛盾。
    给你两个列表 scores 和 ages，其中每组 scores[i] 和 ages[i] 表示第 i 名球员的分数和年龄。请你返回 所有可能的无矛盾球队中得分最高那支的分数 。

    示例 1：
        输入：scores = [1,3,5,10,15], ages = [1,2,3,4,5]
        输出：34
        解释：你可以选中所有球员。
    示例 2：
        输入：scores = [4,5,6,5], ages = [2,1,2,1]
        输出：16
        解释：最佳的选择是后 3 名球员。注意，你可以选中多个同龄球员。
    示例 3：
        输入：scores = [1,2,3,5], ages = [8,9,10,1]
        输出：6
        解释：最佳的选择是前 3 名球员。

    提示：
        1 <= scores.length, ages.length <= 1000
        scores.length == ages.length
        1 <= scores[i] <= 106
        1 <= ages[i] <= 1000
    */

    public static void main(String[] args) {
        int[] scores = {1, 3, 5, 10, 15}, ages = {1, 2, 3, 4, 5};
        int[] scores2 = {4, 5, 6, 5}, ages2 = {2, 1, 2, 1};
        int[] scores3 = {1, 2, 3, 5}, ages3 = {8, 9, 10, 1};
        int[] scores4 = {9, 2, 8, 8, 2}, ages4 = {4, 1, 3, 3, 5};

        System.out.println(bestTeamScore(scores, ages));
        System.out.println(bestTeamScore(scores2, ages2));
        System.out.println(bestTeamScore(scores3, ages3));
        System.out.println(bestTeamScore(scores4, ages4));
    }

    private static int bestTeamScore(int[] scores, int[] ages) {
        // 无矛盾 = (年龄小的球员的分数 <= 年龄大的球员的分数)
        // 先按年龄从小到大排序
        // dp[i] 代表选择第i个球员后的最大分数
        // dp[i] = max(dp[k]) + score[i]
        // 其中k满足，score[k]<=score[i]

        int n = scores.length;
        int[][] grid = new int[n][2];
        for (int i = 0; i < n; i++) {
            grid[i][0] = scores[i];
            grid[i][1] = ages[i];
        }
        Arrays.sort(grid, (a, b) -> {
            if (a[1] != b[1]) {
                return a[1] - b[1];
            } else {
                return a[0] - b[0];
            }
        });

        int[] dp = new int[n + 1];
        int max = 0;
        for (int i = 0; i < n; i++) {
            dp[i + 1] = grid[i][0];
            for (int j = 0; j < i; j++) {
                if (grid[j][0] <= grid[i][0]) {
                    dp[i + 1] = Math.max(dp[i + 1], dp[j + 1] + grid[i][0]);
                }
            }
            max = Math.max(max, dp[i + 1]);
        }
        return max;
    }
}
