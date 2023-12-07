package com.wa.leetcode;

import java.util.*;

/**
 * MinReorder
 * https://leetcode.cn/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero
 * 1466. 重新规划路线
 *
 * @Date: 2023/12/7 8:44
 * @Author: wuao
 */
public class MinReorder {

    /*
    n 座城市，从 0 到 n-1 编号，其间共有 n-1 条路线。因此，要想在两座不同城市之间旅行
    只有唯一一条路线可供选择（路线网形成一颗树）。去年，交通运输部决定重新规划路线，以改变交通拥堵的状况。
    路线用 connections 表示，其中 connections[i] = [a, b] 表示从城市 a 到 b 的一条有向路线。
    今年，城市 0 将会举办一场大型比赛，很多游客都想前往城市 0 。
    请你帮助重新规划路线方向，使每个城市都可以访问城市 0 。返回需要变更方向的最小路线数。
    题目数据 保证 每个城市在重新规划路线方向后都能到达城市 0 。

    示例 1：
        输入：n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
        输出：3
        解释：更改以红色显示的路线的方向，使每个城市都可以到达城市 0 。
    示例 2：
        输入：n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
        输出：2
        解释：更改以红色显示的路线的方向，使每个城市都可以到达城市 0 。
    示例 3：
        输入：n = 3, connections = [[1,0],[2,0]]
        输出：0

    提示：
        2 <= n <= 5 * 10^4
        connections.length == n-1
        connections[i].length == 2
        0 <= connections[i][0], connections[i][1] <= n-1
        connections[i][0] != connections[i][1]
    */

    public static void main(String[] args) {
        int n = 6;
        int[][] connections = {{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}};

        int n2 = 5;
        int[][] connections2 = {{1, 0}, {1, 2}, {3, 2}, {3, 4}};

        int n3 = 3;
        int[][] connections3 = {{1, 0}, {2, 0}};

        System.out.println(minReorder3(n, connections));
        System.out.println(minReorder3(n2, connections2));
        System.out.println(minReorder3(n3, connections3));
    }

    // BFS
    public static int minReorder(int n, int[][] connections) {
        // 无向邻接表
        List<Integer>[] adjacent = new List[n];
        // 有向邻接表
        Set<Integer>[] next = new Set[n];

        for (int i = 0; i < n; i++) {
            adjacent[i] = new ArrayList<>();
            next[i] = new HashSet<>();
        }

        for (int[] c : connections) {
            adjacent[c[0]].add(c[1]);
            adjacent[c[1]].add(c[0]);
            next[c[0]].add(c[1]);
        }


        int res = 0;
        boolean[] visit = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            visit[cur] = true;
            for (int adj : adjacent[cur]) {
                if (visit[adj]) {
                    continue;
                }
                queue.offer(adj);
                if (!next[adj].contains(cur)) {
                    res++;
                }
            }
        }
        return res;
    }

    // 内存优化
    public static int minReorder2(int n, int[][] connections) {
        // 邻接表
        List<int[]>[] adjacent = new List[n];
        for (int i = 0; i < n; i++) {
            adjacent[i] = new ArrayList<>();
        }

        for (int[] c : connections) {
            adjacent[c[0]].add(new int[]{c[1], 1});
            adjacent[c[1]].add(new int[]{c[0], 0});
        }

        int res = 0;
        boolean[] visit = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            visit[cur] = true;
            for (int[] adj : adjacent[cur]) {
                if (visit[adj[0]]) {
                    continue;
                }
                queue.offer(adj[0]);
                if (adj[1] == 1) {
                    res++;
                }
            }
        }
        return res;
    }


    // DFS
    public static int minReorder3(int n, int[][] connections) {
        // 邻接表，list中的数组记录邻接元素和方向
        List<int[]>[] edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int[] c : connections) {
            edges[c[0]].add(new int[]{c[1], 1});
            edges[c[1]].add(new int[]{c[0], 0});
        }

        return dfs(0, -1, edges);
    }

    private static int dfs(int cur, int parent, List<int[]>[] edges) {
        int res = 0;
        for (int[] edge : edges[cur]) {
            if (edge[0] == parent) {
                continue;
            }

            res += edge[1] + dfs(edge[0], cur, edges);
        }
        return res;
    }
}
