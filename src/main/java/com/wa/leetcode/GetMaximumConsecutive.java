package com.wa.leetcode;

import java.util.Arrays;

/**
 * GetMaximumConsecutive
 * https://leetcode.cn/problems/maximum-number-of-consecutive-values-you-can-make/
 * 1798. 你能构造出连续值的最大数目
 *
 * @author: wuao
 * @time: 2023/2/4 16:08
 **/
public class GetMaximumConsecutive {

    /*
    给你一个长度为 n 的整数数组 coins ，它代表你拥有的 n 个硬币。第 i 个硬币的值为 coins[i] 。如果你从这些硬币中选出一部分硬币，它们的和为 x ，那么称，你可以 构造 出 x 。
    请返回从 0 开始（包括 0 ），你最多能 构造 出多少个连续整数。
    你可能有多个相同值的硬币。

    示例 1：
        输入：coins = [1,3]
        输出：2
        解释：你可以得到以下这些值：
            - 0：什么都不取 []
            - 1：取 [1]
        从 0 开始，你可以构造出 2 个连续整数。
    示例 2：
        输入：coins = [1,1,1,4]
        输出：8
        解释：你可以得到以下这些值：
            - 0：什么都不取 []
            - 1：取 [1]
            - 2：取 [1,1]
            - 3：取 [1,1,1]
            - 4：取 [4]
            - 5：取 [4,1]
            - 6：取 [4,1,1]
            - 7：取 [4,1,1,1]
        从 0 开始，你可以构造出 8 个连续整数。
    示例 3：
        输入：nums = [1,4,10,3,1]
        输出：20

    提示：
        coins.length == n
        1 <= n <= 4 * 10^4
        1 <= coins[i] <= 4 * 10^4
    */

    public static void main(String[] args) {
        int[] coins = new int[]{1, 3};
        int[] coins2 = new int[]{1, 1, 1, 4};
        int[] coins3 = new int[]{1, 4, 10, 3, 1};

        System.out.println(getMaximumConsecutive(coins));
        System.out.println(getMaximumConsecutive(coins2));
        System.out.println(getMaximumConsecutive(coins3));
    }

    private static int getMaximumConsecutive(int[] coins) {
        // 假设已经能构造出[0,x]的数
        // 新增一个数y后，则能构造出[0,x]和[y,x+y]
        // 如果y<=x+1，则两个区间能合并成[0,x+y]，继续上述步骤
        // 如果y>x+1，则两个区间不能合并，最多只能构造到x，程序结束
        // 初始区间为[0,0]，注意最后要算上0所以要+1
        int x = 0;
        Arrays.sort(coins);
        for (int coin : coins) {
            if (coin > x + 1) {
                break;
            }
            x += coin;
        }
        return x + 1;
    }
}
