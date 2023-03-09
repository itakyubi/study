package com.wa.leetcode;

/**
 * MinimumRecolors
 * https://leetcode.cn/problems/minimum-recolors-to-get-k-consecutive-black-blocks/
 * 2379. 得到 K 个黑块的最少涂色次数
 * 2023/3/9 9:05 上午
 *
 * @author wuao
 */
public class MinimumRecolors {

    /*
    给你一个长度为 n下标从 0开始的字符串blocks，blocks[i]要么是'W'要么是'B'，表示第i块的颜色。
    字符'W' 和'B'分别表示白色和黑色。
    给你一个整数k，表示想要连续黑色块的数目。
    每一次操作中，你可以选择一个白色块将它 涂成黑色块。
    请你返回至少出现 一次连续 k个黑色块的 最少操作次数。

    示例 1：
        输入：blocks = "WBBWWBBWBW", k = 7
        输出：3
        解释：
        一种得到 7 个连续黑色块的方法是把第 0 ，3 和 4 个块涂成黑色。
        得到 blocks = "BBBBBBBWBW" 。
        可以证明无法用少于 3 次操作得到 7 个连续的黑块。
        所以我们返回 3 。
    示例 2：
        输入：blocks = "WBWBBBW", k = 2
        输出：0
        解释：
        不需要任何操作，因为已经有 2 个连续的黑块。
        所以我们返回 0 。

    提示：
        n == blocks.length
        1 <= n <= 100
        blocks[i]要么是'W'，要么是'B' 。
        1 <= k <= n
    */

    public static void main(String[] args) {
        String blocks = "WBBWWBBWBW";
        int k = 7;

        String blocks2 = "WBWBBBW";
        int k2 = 2;

        String blocks3 = "BWBBWWBBBWBWWWBWWBBWBWBBWBB";
        int k3 = 11;

        String blocks4 = "BWWWBB";
        int k4 = 6;

        System.out.println(minimumRecolors(blocks, k));
        System.out.println(minimumRecolors(blocks2, k2));
        System.out.println(minimumRecolors(blocks3, k3));
        System.out.println(minimumRecolors(blocks4, k4));
    }

    private static int minimumRecolors(String blocks, int k) {
        // 滑动窗口，计算长度为k的窗口中B的个数，记为cnt
        // 最大个数记为max，max = Math.max(cnt)
        // 则最少操作次数为 k-max;
        int max = 0, cnt = 0;
        for (int i = 0; i < blocks.length(); i++) {
            char c = blocks.charAt(i);
            if (i < k) {
                cnt += blackCnt(c);
            } else {
                cnt = cnt + blackCnt(c) - blackCnt(blocks.charAt(i - k));
            }
            max = Math.max(max, cnt);
            // 提前结束
            if (max == k) {
                return 0;
            }
        }
        return k - max;
    }

    private static int blackCnt(char c) {
        return c == 'B' ? 1 : 0;
    }

}
