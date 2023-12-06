package com.wa.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * MinimumTotalPrice
 * https://leetcode.cn/problems/minimize-the-total-price-of-the-trips
 * 2646. 最小化旅行的价格总和
 *
 * @Date: 2023/12/6 8:34
 * @Author: wuao
 */
public class MinimumTotalPrice {

    /*
    现有一棵无向、无根的树，树中有 n 个节点，按从 0 到 n - 1 编号。
    给你一个整数 n 和一个长度为 n - 1 的二维整数数组 edges ，
    其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条边。
    每个节点都关联一个价格。给你一个整数数组 price ，其中 price[i] 是第 i 个节点的价格。
    给定路径的 价格总和 是该路径上所有节点的价格之和。
    另给你一个二维整数数组 trips ，其中 trips[i] = [starti, endi]
    表示您从节点 starti 开始第 i 次旅行，并通过任何你喜欢的路径前往节点 endi 。
    在执行第一次旅行之前，你可以选择一些 非相邻节点 并将价格减半。
    返回执行所有旅行的最小价格总和。

    示例 1：
        输入：n = 4, edges = [[0,1],[1,2],[1,3]], price = [2,2,10,6], trips = [[0,3],[2,1],[2,3]]
        输出：23
        解释：
            上图表示将节点 2 视为根之后的树结构。第一个图表示初始树，第二个图表示选择节点 0 、2 和 3 并使其价格减半后的树。
            第 1 次旅行，选择路径 [0,1,3] 。路径的价格总和为 1 + 2 + 3 = 6 。
            第 2 次旅行，选择路径 [2,1] 。路径的价格总和为 2 + 5 = 7 。
            第 3 次旅行，选择路径 [2,1,3] 。路径的价格总和为 5 + 2 + 3 = 10 。
            所有旅行的价格总和为 6 + 7 + 10 = 23 。可以证明，23 是可以实现的最小答案。
    示例 2：
        输入：n = 2, edges = [[0,1]], price = [2,2], trips = [[0,0]]
        输出：1
        解释：
            上图表示将节点 0 视为根之后的树结构。第一个图表示初始树，第二个图表示选择节点 0 并使其价格减半后的树。
            第 1 次旅行，选择路径 [0] 。路径的价格总和为 1 。
            所有旅行的价格总和为 1 。可以证明，1 是可以实现的最小答案。

    提示：
        1 <= n <= 50
        edges.length == n - 1
        0 <= ai, bi <= n - 1
        edges 表示一棵有效的树
        price.length == n
        price[i] 是一个偶数
        1 <= price[i] <= 1000
        1 <= trips.length <= 100
        0 <= starti, endi <= n - 1
    */

    public static void main(String[] args) {
        int n = 4;
        int[][] edges = {{0, 1}, {1, 2}, {1, 3}};
        int[] price = {2, 2, 10, 6};
        int[][] trips = {{0, 3}, {2, 1}, {2, 3}};

        int n2 = 2;
        int[][] edges2 = {{0, 1}};
        int[] price2 = {2, 2};
        int[][] trips2 = {{0, 0}};

        System.out.println(minimumTotalPrice(n, edges, price, trips));
        System.out.println(minimumTotalPrice(n2, edges2, price2, trips2));
    }

    // 1、计算节点出现次数和价格总和
    //  1.1 cnt[i]记录节点i在所有路径中出现的次数
    //  1.2 以start为根节点，当节点i子树中包含end时，cnt[i]++
    //  1.3 所有旅行的总价格=sum(cnt[i]*price[i]), 0<= i <n
    // 2、计算最小价格总和
    //  2.1 dp[i][0]代表以节点i为根节点的子树，且节点i保持价格时的最小价格总和
    //  2.2 dp[i][1]代表以节点i为根节点的子树，且节点i价格减半时的最小价格总和
    //  2.3 dp[i][0] = sum(min(dp[j][0], dp[j][1]))，其中j为节点i的直接子节点，含义就是节点i价格不减半时，节点j价格可以保持也可以减半
    //  2.4 dp[i][1] = sum(min(dp[j][0]))，其中j为节点i的直接子节点，含义就是节点i价格减半时，节点j价格只能保持
    //  2.5 最终min(dp[0][0], dp[0][1])即为结果
    public static int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        List<Integer>[] nexts = new List[n];
        for (int i = 0; i < n; i++) {
            nexts[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            nexts[edge[0]].add(edge[1]);
            nexts[edge[1]].add(edge[0]);
        }


        int[] cnt = new int[n];
        for (int[] trip : trips) {
            dfs(trip[0], -1, trip[1], nexts, cnt);
        }

        int[] pair = dp(0, -1, price, nexts, cnt);
        return Math.min(pair[0], pair[1]);
    }

    /**
     * @param node   当前节点
     * @param parent 父节点
     * @param end    结束节点
     * @param nexts  相邻节点数组
     * @param cnt    节点出现次数数组
     * @return 是否包含节点end
     */
    private static boolean dfs(int node, int parent, int end, List<Integer>[] nexts, int[] cnt) {
        if (node == end) {
            cnt[node]++;
            return true;
        }

        for (int next : nexts[node]) {
            if (next == parent) {
                // 遍历回父节点了
                continue;
            }

            if (dfs(next, node, end, nexts, cnt)) {
                cnt[node]++;
                return true;
            }
        }
        return false;
    }

    /**
     * @param node   当前节点
     * @param parent 父节点
     * @param price  节点价格数组
     * @param nexts  相邻节点数组
     * @param cnt    节点出现次数数组
     * @return 最小价格总和数组，[0]代表当前节点保持价格后的最小价格总和，[1]代表当前节点价格减半后的最小价格总和
     */
    private static int[] dp(int node, int parent, int[] price, List<Integer>[] nexts, int[] cnt) {
        int[] res = {price[node] * cnt[node], price[node] * cnt[node] / 2};
        for (int next : nexts[node]) {
            if (next == parent) {
                continue;
            }
            int[] pair = dp(next, node, price, nexts, cnt);
            res[0] += Math.min(pair[0], pair[1]);
            res[1] += pair[0];
        }
        return res;
    }
}
