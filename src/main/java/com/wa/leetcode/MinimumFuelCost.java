package com.wa.leetcode;

import java.util.*;

/**
 * MinimumFuelCost
 * https://leetcode.cn/problems/minimum-fuel-cost-to-report-to-the-capita
 * 2477. 到达首都的最少油耗
 *
 * @Date: 2023/12/5 8:30
 * @Author: wuao
 */
public class MinimumFuelCost {

    /*
    给你一棵 n 个节点的树（一个无向、连通、无环图），每个节点表示一个城市，编号从 0 到 n - 1 ，且恰好有 n - 1 条路。
    0 是首都。给你一个二维整数数组 roads ，其中 roads[i] = [ai, bi] ，表示城市 ai 和 bi 之间有一条 双向路 。
    每个城市里有一个代表，他们都要去首都参加一个会议。
    每座城市里有一辆车。给你一个整数 seats 表示每辆车里面座位的数目。
    城市里的代表可以选择乘坐所在城市的车，或者乘坐其他城市的车。相邻城市之间一辆车的油耗是一升汽油。
    请你返回到达首都最少需要多少升汽油。

    示例 1：
        输入：roads = [[0,1],[0,2],[0,3]], seats = 5
        输出：3
        解释：
            - 代表 1 直接到达首都，消耗 1 升汽油。
            - 代表 2 直接到达首都，消耗 1 升汽油。
            - 代表 3 直接到达首都，消耗 1 升汽油。
        最少消耗 3 升汽油。
    示例 2：
        输入：roads = [[3,1],[3,2],[1,0],[0,4],[0,5],[4,6]], seats = 2
        输出：7
        解释：
            - 代表 2 到达城市 3 ，消耗 1 升汽油。
            - 代表 2 和代表 3 一起到达城市 1 ，消耗 1 升汽油。
            - 代表 2 和代表 3 一起到达首都，消耗 1 升汽油。
            - 代表 1 直接到达首都，消耗 1 升汽油。
            - 代表 5 直接到达首都，消耗 1 升汽油。
            - 代表 6 到达城市 4 ，消耗 1 升汽油。
            - 代表 4 和代表 6 一起到达首都，消耗 1 升汽油。
        最少消耗 7 升汽油。
    示例 3：
        输入：roads = [], seats = 1
        输出：0
        解释：没有代表需要从别的城市到达首都。

    提示：
        1 <= n <= 105
        roads.length == n - 1
        roads[i].length == 2
        0 <= ai, bi < n
        ai != bi
        roads 表示一棵合法的树。
        1 <= seats <= 105
    */

    public static void main(String[] args) {
        int[][] roads = {{0, 1}, {0, 2}, {0, 3}};
        int seats = 5;

        int[][] roads2 = {{3, 1}, {3, 2}, {1, 0}, {0, 4}, {0, 5}, {4, 6}};
        int seats2 = 2;

        int[][] roads3 = {};
        int seats3 = 1;

        int[][] roads4 = {{0, 1}, {0, 2}, {1, 3}, {1, 4}};
        int seats4 = 5;

        System.out.println(minimumFuelCost(roads, seats));
        System.out.println(minimumFuelCost(roads2, seats2));
        System.out.println(minimumFuelCost(roads3, seats3));
        System.out.println(minimumFuelCost(roads4, seats4));
    }

    private static long res;

    public static long minimumFuelCost(int[][] roads, int seats) {
        res = 0;
        int n = roads.length + 1;
        List<Integer>[] edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            edges[road[0]].add(road[1]);
            edges[road[1]].add(road[0]);
        }
        dfs(0, -1, seats, edges);
        return res;
    }

    private static int dfs(int cur, int parent, int seats, List<Integer>[] edges) {
        int nodeCnt = 1;
        for (int next : edges[cur]) {
            if (next != parent) {
                int cnt = dfs(next, cur, seats, edges);
                nodeCnt += cnt;
                // cnt/seats 向上取整等价于 (cnt + seats - 1) / seats
                res += (cnt + seats - 1) / seats;
            }
        }
        return nodeCnt;
    }
}
