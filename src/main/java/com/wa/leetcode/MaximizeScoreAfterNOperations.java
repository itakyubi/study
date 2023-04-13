package com.wa.leetcode;

import java.util.Arrays;

/**
 * MaximizeScoreAfterNOperations
 * https://leetcode.cn/problems/maximize-score-after-n-operations/
 * 1799. N 次操作后的最大分数和
 * 2022/12/22 6:42 下午
 *
 * @author wuao
 */
public class MaximizeScoreAfterNOperations {

    /*
    给你nums，它是一个大小为2 * n的正整数数组。你必须对这个数组执行 n次操作。
    在第i次操作时（操作编号从 1开始），你需要：
    选择两个元素x 和y。
    获得分数i * gcd(x, y)。
    将x和y 从nums中删除。
    请你返回 n次操作后你能获得的分数和最大为多少。
    函数gcd(x, y)是x 和y的最大公约数。

    示例 1：
        输入：nums = [1,2]
        输出：1
        解释：最优操作是：
                (1 * gcd(1, 2)) = 1
    示例 2：
        输入：nums = [3,4,6,8]
        输出：11
        解释：最优操作是：
                (1 * gcd(3, 6)) + (2 * gcd(4, 8)) = 3 + 8 = 11
    示例 3：
        输入：nums = [1,2,3,4,5,6]
        输出：14
        解释：最优操作是：
                (1 * gcd(1, 5)) + (2 * gcd(2, 4)) + (3 * gcd(3, 6)) = 1 + 4 + 9 = 14

    提示：
        1 <= n <= 7
        nums.length == 2 * n
        1 <= nums[i] <= 10^6
    */


    public static void main(String[] args) {
        int[] nums = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4, 6, 8};
        int[] nums3 = new int[]{1, 2, 3, 4, 5, 6};

        System.out.println(maxScore(nums));
        System.out.println(maxScore(nums2));
        System.out.println(maxScore(nums3));
    }

    private static int maxScore(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);

        int res = 0, cnt = nums.length / 2;
        for (int i = nums.length - 1; i >= 0; i--) {
            int max = 0, maxIndex = i;
            for (int j = i - 1; j >= 0; j--) {
                if (!visited[j]) {
                    int gcd = gcd(nums[i], nums[j]);
                    if (gcd > max) {
                        max = gcd;
                        maxIndex = j;
                    }
                }
            }
            visited[i] = true;
            visited[maxIndex] = true;
            res += cnt * max;
            cnt--;
        }
        return res;
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
