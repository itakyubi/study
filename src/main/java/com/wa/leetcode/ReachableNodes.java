package com.wa.leetcode;

import java.util.Arrays;

/**
 * ReachableNodes
 * https://leetcode.cn/problems/reachable-nodes-in-subdivided-graph/
 * 882. 细分图中的可到达节点
 * 2022/11/27 3:57 下午
 *
 * @author wuao
 */
public class ReachableNodes {

    /*
    给你一个无向图（原始图），图中有 n 个节点，编号从 0 到 n - 1 。你决定将图中的每条边 细分 为一条节点链，每条边之间的新节点数各不相同。
    图用由边组成的二维数组 edges 表示，其中 edges[i] = [ui, vi, cnti] 表示原始图中节点 ui 和 vi 之间存在一条边，cnti 是将边 细分 后的新节点总数。注意，cnti == 0 表示边不可细分。
    要 细分 边 [ui, vi] ，需要将其替换为 (cnti + 1) 条新边，和 cnti 个新节点。新节点为 x1, x2, ..., xcnti ，新边为 [ui, x1], [x1, x2], [x2, x3], ..., [xcnti+1, xcnti], [xcnti, vi] 。
    现在得到一个 新的细分图 ，请你计算从节点 0 出发，可以到达多少个节点？如果节点间距离是 maxMoves 或更少，则视为 可以到达 。
    给你原始图和 maxMoves ，返回 新的细分图中从节点 0 出发 可到达的节点数 。

    示例 1：
        输入：edges = [[0,1,10],[0,2,1],[1,2,2]], maxMoves = 6, n = 3
        输出：13
        解释：边的细分情况如上图所示。
        可以到达的节点已经用黄色标注出来。
    示例 2：
        输入：edges = [[0,1,4],[1,2,6],[0,2,8],[1,3,1]], maxMoves = 10, n = 4
        输出：23
    示例 3：
        输入：edges = [[1,2,4],[1,4,5],[1,3,1],[2,3,4],[3,4,5]], maxMoves = 17, n = 5
        输出：1
        解释：节点 0 与图的其余部分没有连通，所以只有节点 0 可以到达。

    提示：
        0 <= edges.length <= min(n * (n - 1) / 2, 104)
        edges[i].length == 3
        0 <= ui < vi < n
        图中 不存在平行边
        0 <= cnti <= 104
        0 <= maxMoves <= 109
        1 <= n <= 3000
    */

    public static void main(String[] args) {
        int[][] edges = new int[][]{{0, 1, 10}, {0, 2, 1}, {1, 2, 2}};
        int maxMoves = 6, n = 3;
        System.out.println(reachableNodes(edges, maxMoves, n));
    }

    private static int reachableNodes(int[][] edges, int maxMoves, int n) {
        int INF = Integer.MAX_VALUE >> 2;
        // 建图，邻接表
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(graph[i], INF);
        }
        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = edge[2] + 1;
            graph[edge[1]][edge[0]] = edge[2] + 1;
        }

        // dijkstra 找到每个点距离节点0最近的距离
        // dijkstra[i]=x 表示节点i距离节点0对的最短距离是x
        int[] dijkstra = new int[n];
        Arrays.fill(dijkstra, INF);
        dijkstra[0] = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            // 找到未遍历的节点中，距离已遍历节点最近的那个节点
            int minDistanceNode = -1;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && (minDistanceNode == -1 || dijkstra[j] < dijkstra[minDistanceNode])) {
                    minDistanceNode = j;
                }
            }
            visited[minDistanceNode] = true;
            // 加入新节点到已遍历的节点集合后，更新所有节点距离节点0的最短距离
            for (int j = 0; j < n; j++) {
                dijkstra[j] = Math.min(dijkstra[j], dijkstra[minDistanceNode] + graph[minDistanceNode][j]);
                if (dijkstra[j] < 0) {
                    System.out.println(dijkstra[j]);
                }

            }
        }

        int res = 0;
        // 先统计所有的大节点
        for (int i = 0; i < n; i++) {
            if (dijkstra[i] <= maxMoves) {
                res++;
            }
        }
        // 再统计细分节点
        for (int[] edge : edges) {
            // 细分节点有两种可能
            // 第一种，该边上的所有节点都统计进去
            // 第二种，从edge[0]开始数剩余节点+从edge[1]开始数剩余节点
            // 最后取最小值是因为从edge[0]和edge[1]同时数节点会有重复的情况，最多只能有该边上的所有节点
            res += Math.min(edge[2], Math.max(0, maxMoves - dijkstra[edge[0]]) + Math.max(0, maxMoves - dijkstra[edge[1]]));
        }
        return res;
    }
}
