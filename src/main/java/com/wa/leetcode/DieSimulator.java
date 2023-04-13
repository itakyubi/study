package com.wa.leetcode;

/**
 * DieSimulator
 * https://leetcode.cn/problems/dice-roll-simulation/
 * 1223. 掷骰子模拟
 * 2023/2/10 9:07 上午
 *
 * @author wuao
 */
public class DieSimulator {

    /*
    有一个骰子模拟器会每次投掷的时候生成一个 1 到 6 的随机数。
    不过我们在使用它时有个约束，就是使得投掷骰子时，连续 掷出数字i的次数不能超过rollMax[i]（i从 1 开始编号）。
    现在，给你一个整数数组rollMax和一个整数n，请你来计算掷n次骰子可得到的不同点数序列的数量。
    假如两个序列中至少存在一个元素不同，就认为这两个序列是不同的。由于答案可能很大，所以请返回 模10^9 + 7之后的结果。

    示例 1：
        输入：n = 2, rollMax = [1,1,2,2,2,3]
        输出：34
        解释：我们掷 2 次骰子，如果没有约束的话，共有 6 * 6 = 36 种可能的组合。但是根据 rollMax 数组，数字 1 和 2 最多连续出现一次，所以不会出现序列 (1,1) 和 (2,2)。因此，最终答案是 36-2 = 34。
    示例 2：
        输入：n = 2, rollMax = [1,1,1,1,1,1]
        输出：30
    示例 3：
        输入：n = 3, rollMax = [1,1,1,2,2,3]
        输出：181

    提示：
        1 <= n <= 5000
        rollMax.length == 6
        1 <= rollMax[i] <= 15
    */

    public static void main(String[] args) {
        int n = 2;
        int[] rollMax = new int[]{1, 1, 2, 2, 2, 3};

        int n2 = 2;
        int[] rollMax2 = new int[]{1, 1, 1, 1, 1, 1};

        int n3 = 3;
        int[] rollMax3 = new int[]{1, 1, 1, 2, 2, 3};

        int n4 = 4;
        int[] rollMax4 = new int[]{2, 1, 1, 3, 3, 2};

        int n5 = 5;
        int[] rollMax5 = new int[]{2, 1, 4, 4, 1, 1};

        System.out.println(dieSimulator(n, rollMax));
        System.out.println(dieSimulator(n2, rollMax2));
        System.out.println(dieSimulator(n3, rollMax3));
        System.out.println(dieSimulator(n4, rollMax4));
        System.out.println(dieSimulator(n5, rollMax5));
    }

    // 这个一维dp好像总会存在多减或者少减的问题，具体咋改也不知道
    // 而且在骰子次数变多后，修正次数会越来越多，效率下降明显
    private static int dieSimulator(int n, int[] rollMax) {
        // dp[i] 代表掷i次骰子不同的序列数量
        // dp[i] = dp[i-1]*6 - dp[i-rollMax[0]-1] - dp[i-rollMax[1]-1] ...
        // 上述表达式的含义是，掷i次骰子的序列数量=掷i-1次骰子的序列数量 - 第i次掷出的超出连续限制的数量
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] * 6;
            for (int j = 0; j < 6; j++) {
                int remain = i - rollMax[j] - 1;
                boolean flag = false;
                while (remain >= 0) {
                    if (flag) {
                        dp[i] += dp[remain];
                    } else {
                        dp[i] -= dp[remain];
                    }
                    remain--;
                    flag = !flag;
                }
            }
        }
        return dp[n];
    }

    // 官方题解，二维dp或者三维dp
    private static int dieSimulator2(int n, int[] rollMax) {
        return 0;
    }
}
