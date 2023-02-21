package com.wa.leetcode;

/**
 * MinTaps
 * https://leetcode.cn/problems/minimum-number-of-taps-to-open-to-water-a-garden/
 * 1326. 灌溉花园的最少水龙头数目
 * 2023/2/21 9:02 上午
 *
 * @author wuao
 */
public class MinTaps {

    /*
    在 x 轴上有一个一维的花园。花园长度为n，从点0开始，到点n结束。
    花园里总共有n + 1 个水龙头，分别位于[0, 1, ..., n] 。
    给你一个整数n和一个长度为n + 1 的整数数组ranges，其中ranges[i] （下标从 0 开始）表示：如果打开点i处的水龙头，可以灌溉的区域为[i - ranges[i], i + ranges[i]]。
    请你返回可以灌溉整个花园的最少水龙头数目。如果花园始终存在无法灌溉到的地方，请你返回-1。

    示例 1：
        输入：n = 5, ranges = [3,4,1,1,0,0]
        输出：1
        解释：
        点 0 处的水龙头可以灌溉区间 [-3,3]
        点 1 处的水龙头可以灌溉区间 [-3,5]
        点 2 处的水龙头可以灌溉区间 [1,3]
        点 3 处的水龙头可以灌溉区间 [2,4]
        点 4 处的水龙头可以灌溉区间 [4,4]
        点 5 处的水龙头可以灌溉区间 [5,5]
        只需要打开点 1 处的水龙头即可灌溉整个花园 [0,5] 。
    示例 2：
        输入：n = 3, ranges = [0,0,0,0]
        输出：-1
        解释：即使打开所有水龙头，你也无法灌溉整个花园。

    提示：
        1 <= n <= 104
        ranges.length == n + 1
        0 <= ranges[i] <= 100
    */

    public static void main(String[] args) {
        int n = 5;
        int[] ranges = new int[]{3, 4, 1, 1, 0, 0};

        // 要覆盖的是整个花园，这个用例只覆盖了水龙头那个点，所以返回-1
        int n2 = 3;
        int[] ranges2 = new int[]{0, 0, 0, 0};

        System.out.println(minTaps(n, ranges));
        System.out.println(minTaps(n2, ranges2));
    }

    private static int minTaps(int n, int[] ranges) {
        // 找到每个位置被某个水龙头覆盖后，该水龙头最右能覆盖的位置
        int[] rights = new int[n + 1];
        for (int i = 0; i < ranges.length; i++) {
            int start = Math.max(0, i - ranges[i]);
            int end = Math.min(n, i + ranges[i]);
            rights[start] = Math.max(rights[start], end);
        }

        // 遍历每一个节点，遍历到节点i时，寻找节点i左边节点能到达的最右值，记为last
        // pre记录上一个被覆盖的区间的右端点
        int res = 0, last = 0, pre = 0;
        for (int i = 0; i < n; i++) {
            last = Math.max(last, rights[i]);
            // 如果i==last，说明当前节点i到下一个节点i+1一定无法覆盖
            // 否则当前节点i的最右值，一定不是i
            if (i == last) {
                return -1;
            }
            // 说明已经走完一个区间，需要走下一个区间
            if (i == pre) {
                res++;
                pre = last;
            }
        }
        return res;
    }

}
