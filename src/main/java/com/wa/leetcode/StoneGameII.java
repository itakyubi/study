package com.wa.leetcode;

/**
 * StoneGameII
 * https://leetcode.cn/problems/stone-game-ii/
 * 1140. 石子游戏 II
 * 2023/2/22 8:56 上午
 *
 * @author wuao
 */
public class StoneGameII {

    /*
    爱丽丝和鲍勃继续他们的石子游戏。许多堆石子排成一行，每堆都有正整数颗石子piles[i]。游戏以谁手中的石子最多来决出胜负。
    爱丽丝和鲍勃轮流进行，爱丽丝先开始。最初，M = 1。
    在每个玩家的回合中，该玩家可以拿走剩下的前X堆的所有石子，其中1 <= X <= 2M。然后，令M = max(M, X)。
    游戏一直持续到所有石子都被拿走。
    假设爱丽丝和鲍勃都发挥出最佳水平，返回爱丽丝可以得到的最大数量的石头。

    示例 1：
        输入：piles = [2,7,9,4,4]
        输出：10
        解释：如果一开始Alice取了一堆，Bob取了两堆，然后Alice再取两堆。爱丽丝可以得到2 + 4 + 4 = 10堆。如果Alice一开始拿走了两堆，那么Bob可以拿走剩下的三堆。在这种情况下，Alice得到2 + 7 = 9堆。返回10，因为它更大。
    示例 2:
        输入：piles = [1,2,3,4,5,100]
        输出：104

    提示：
        1 <= piles.length <= 100
        1 <= piles[i]<= 10^4
    */

    public static void main(String[] args) {
        int[] piles = new int[]{2, 7, 9, 4, 4};
        int[] piles2 = new int[]{1, 2, 3, 4, 5, 100};

        System.out.println(stoneGameII(piles));
        System.out.println(stoneGameII(piles2));
    }

    // 后缀和+动态规划
    private static int stoneGameII(int[] piles) {
        int n = piles.length;
        // 求后缀和，sum[i] 代表[i,n-1]区间的石子数的和
        int[] sum = new int[n];
        sum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            sum[i] = sum[i + 1] + piles[i];
        }

        // dp[i][j] 表示剩余[i,n-1]且m=j时，先取的人能够获得的最多的石子数
        // 如果i+2j>=n，表示先取的人可以拿走剩下所有石子，dp[i][j] = sum[i]
        // 如果i+2j<n，表示先取的人没法拿走身下所有石子，必须给下一个人剩下最少的石子
        // 此时，dp[i][j] = max(sum[i] - dp[i+x][max(j,x)])，其中1<=x<=2j
        int[][] dp = new int[n][n + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j <= n; j++) {
                if (i + 2 * j >= n) {
                    dp[i][j] = sum[i];
                } else {
                    for (int x = 1; x <= 2 * j; x++) {
                        dp[i][j] = Math.max(dp[i][j], sum[i] - dp[i + x][Math.max(j, x)]);
                    }
                }
            }
        }
        return dp[0][1];
    }

}
