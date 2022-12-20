package com.wa.leetcode;

import java.util.Arrays;

/**
 * MinimumSize
 * https://leetcode.cn/problems/minimum-limit-of-balls-in-a-bag/
 * 1760. 袋子里最少数目的球
 * 2022/12/20 4:40 下午
 *
 * @author wuao
 */
public class MinimumSize {

    /*
    给你一个整数数组nums，其中nums[i]表示第i个袋子里球的数目。同时给你一个整数maxOperations。

    你可以进行如下操作至多maxOperations次：

    选择任意一个袋子，并将袋子里的球分到2 个新的袋子中，每个袋子里都有 正整数个球。
    比方说，一个袋子里有5个球，你可以把它们分到两个新袋子里，分别有 1个和 4个球，或者分别有 2个和 3个球。
    你的开销是单个袋子里球数目的 最大值，你想要 最小化开销。

    请你返回进行上述操作后的最小开销。

            

    示例 1：
        输入：nums = [9], maxOperations = 2
        输出：3
        解释：
                - 将装有 9 个球的袋子分成装有 6 个和 3 个球的袋子。[9] -> [6,3] 。
                - 将装有 6 个球的袋子分成装有 3 个和 3 个球的袋子。[6,3] -> [3,3,3] 。
        装有最多球的袋子里装有 3 个球，所以开销为 3 并返回 3 。
    示例 2：
            输入：nums = [2,4,8,2], maxOperations = 4
            输出：2
            解释：
                    - 将装有 8 个球的袋子分成装有 4 个和 4 个球的袋子。[2,4,8,2] -> [2,4,4,4,2] 。
                    - 将装有 4 个球的袋子分成装有 2 个和 2 个球的袋子。[2,4,4,4,2] -> [2,2,2,4,4,2] 。
                    - 将装有 4 个球的袋子分成装有 2 个和 2 个球的袋子。[2,2,2,4,4,2] -> [2,2,2,2,2,4,2] 。
                    - 将装有 4 个球的袋子分成装有 2 个和 2 个球的袋子。[2,2,2,2,2,4,2] -> [2,2,2,2,2,2,2,2] 。
            装有最多球的袋子里装有 2 个球，所以开销为 2 并返回 2 。
    示例 3：
        输入：nums = [7,17], maxOperations = 2
        输出：7

    提示：
        1 <= nums.length <= 105
        1 <= maxOperations, nums[i] <= 109
    */

    public static void main(String[] args) {
        int[] nums = new int[]{9};
        int maxOperations = 2;

        int[] nums2 = new int[]{2, 4, 8, 2};
        int maxOperations2 = 4;

        int[] nums3 = new int[]{7, 17};
        int maxOperations3 = 2;

        int[] nums4 = new int[]{431, 922, 158, 60, 192, 14, 788, 146, 788, 775, 772, 792, 68, 143, 376, 375, 877, 516, 595, 82, 56, 704, 160, 403, 713, 504, 67, 332, 26};
        int maxOperations4 = 80;

        System.out.println(minimumSize2(nums, maxOperations));
        System.out.println(minimumSize2(nums2, maxOperations2));
        System.out.println(minimumSize2(nums3, maxOperations3));
        System.out.println(minimumSize2(nums4, maxOperations4));
    }

    private static int minimumSize2(int[] nums, int maxOperations) {
        int left = 1, right = 1;
        for (int num : nums) {
            if (num > right) {
                right = num;
            }
        }

        int res = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            long ops = 0;
            for (int num : nums) {
                ops += (num - 1) / mid;
            }
            if (ops <= maxOperations) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    // 内存超出限制
    private static int minimumSize(int[] nums, int maxOperations) {
        Arrays.sort(nums);
        int[] nums2 = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nums2[nums.length - i - 1] = nums[i];
        }
        nums = nums2;


        int n = nums.length;
        int[][] dp = new int[n + 1][maxOperations + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE >> 1);
        }
        dp[1][0] = Integer.MAX_VALUE >> 1;
        for (int i = 1; i <= maxOperations; i++) {
            dp[1][i] = helper(nums[0], i);
        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= maxOperations; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 0; k <= j; k++) {
                    min = Math.min(min, Math.max(dp[i][k], helper(nums[i], j - k)));
                }
                dp[i + 1][j] = min;
            }
        }

        return dp[n][maxOperations];
    }

    private static int helper(int num, int maxOperations) {
        if (maxOperations < 0) {
            return Integer.MAX_VALUE >> 1;
        }
        return num / (maxOperations + 1) + num % (maxOperations + 1);
    }
}
