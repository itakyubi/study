package com.wa.leetcode;

import java.util.Arrays;

/**
 * DistanceLimitedPathsExist
 * https://leetcode.cn/problems/checking-existence-of-edge-length-limited-paths/
 * 1697. 检查边长度限制的路径是否存在
 * 2022/12/14 4:33 下午
 *
 * @author wuao
 */
public class DistanceLimitedPathsExist {

    /*
    给你一个 n个点组成的无向图边集edgeList，其中edgeList[i] = [ui, vi, disi]表示点ui 和点vi之间有一条长度为disi的边。请注意，两个点之间可能有 超过一条边。
    给你一个查询数组queries，其中queries[j] = [pj, qj, limitj]，你的任务是对于每个查询queries[j]，判断是否存在从pj到qj的路径，且这条路径上的每一条边都 严格小于limitj。
    请你返回一个 布尔数组answer，其中answer.length == queries.length，当queries[j]的查询结果为true时，answer 第j个值为true，否则为false。

    示例 1：
        输入：n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries = [[0,1,2],[0,2,5]]
        输出：[false,true]
        解释：上图为给定的输入数据。注意到 0 和 1 之间有两条重边，分别为 2 和 16 。
        对于第一个查询，0 和 1 之间没有小于 2 的边，所以我们返回 false 。
        对于第二个查询，有一条路径（0 -> 1 -> 2）两条边都小于 5 ，所以这个查询我们返回 true 。
    示例 2：
        输入：n = 5, edgeList = [[0,1,10],[1,2,5],[2,3,9],[3,4,13]], queries = [[0,4,14],[1,4,13]]
        输出：[true,false]
        解释：上图为给定数据。

    提示：
        2 <= n <= 10^5
        1 <= edgeList.length, queries.length <= 10^5
        edgeList[i].length == 3
        queries[j].length == 3
        0 <= ui, vi, pj, qj <= n - 1
        ui != vi
        pj != qj
        1 <= disi, limitj <= 10^9
        两个点之间可能有 多条边。
    */


    public static void main(String[] args) {
        int n = 3;
        int[][] edgeList = new int[][]{{0, 1, 2}, {1, 2, 4}, {2, 0, 8}, {1, 0, 16}}, queries = new int[][]{{0, 1, 2}, {0, 2, 5}};

        int n2 = 5;
        int[][] edgeList2 = new int[][]{{0, 1, 10}, {1, 2, 5}, {2, 3, 9}, {3, 4, 13}}, queries2 = new int[][]{{0, 4, 14}, {1, 4, 13}};

        System.out.println(Arrays.toString(distanceLimitedPathsExist2(n, edgeList, queries)));
        System.out.println(Arrays.toString(distanceLimitedPathsExist2(n2, edgeList2, queries2)));
    }


    private static boolean[] distanceLimitedPathsExist2(int n, int[][] edgeList, int[][] queries) {
        return null;
    }


    // 超时
    private static boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        int INF = Integer.MAX_VALUE >> 2;

        // 构建邻接表
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(graph[i], INF);
        }
        for (int[] edge : edgeList) {
            int i = edge[0], j = edge[1], distance = edge[2];
            if (graph[i][j] != 0) {
                distance = Math.min(graph[i][j], distance);
            }
            graph[i][j] = distance;
            graph[j][i] = distance;
        }

        // 遍历
        boolean[] res = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int node1 = queries[i][0], node2 = queries[i][1], limit = queries[i][2];

            // dijkstra[i]=x 表示节点i距离节点node1的最短距离是x
            int[] dijkstra = new int[n];
            Arrays.fill(dijkstra, INF);
            dijkstra[node1] = 0;
            boolean[] visited = new boolean[n];
            for (int j = 0; j < n; j++) {
                int minDistanceNode = -1;
                for (int k = 0; k < n; k++) {
                    if (!visited[k] && (minDistanceNode == -1 || dijkstra[k] < dijkstra[minDistanceNode])) {
                        minDistanceNode = k;
                    }
                }
                visited[minDistanceNode] = true;

                for (int k = 0; k < n; k++) {
                    if (graph[minDistanceNode][k] < limit) {
                        dijkstra[k] = Math.min(dijkstra[k], dijkstra[minDistanceNode] + graph[minDistanceNode][k]);
                    }
                }
            }

            res[i] = dijkstra[node2] != INF;
        }
        return res;

    }
}
