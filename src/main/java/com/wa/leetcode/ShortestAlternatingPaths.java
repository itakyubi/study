package com.wa.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * ShortestAlternatingPaths
 * https://leetcode.cn/problems/shortest-path-with-alternating-colors/
 * 1129. 颜色交替的最短路径
 * 2023/2/2 2:26 下午
 *
 * @author wuao
 */
public class ShortestAlternatingPaths {

    /*
    在一个有向图中，节点分别标记为0, 1, ..., n-1。图中每条边为红色或者蓝色，且存在自环或平行边。
    red_edges中的每一个[i, j]对表示从节点 i 到节点 j 的红色有向边。类似地，blue_edges中的每一个[i, j]对表示从节点 i 到节点 j 的蓝色有向边。
    返回长度为 n 的数组answer，其中answer[X]是从节点0到节点X的红色边和蓝色边交替出现的最短路径的长度。如果不存在这样的路径，那么 answer[x] = -1。

    示例 1：
        输入：n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
        输出：[0,1,-1]
    示例 2：
        输入：n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]
        输出：[0,1,-1]
    示例 3：
        输入：n = 3, red_edges = [[1,0]], blue_edges = [[2,1]]
        输出：[0,-1,-1]
    示例 4：
        输入：n = 3, red_edges = [[0,1]], blue_edges = [[1,2]]
        输出：[0,1,2]
    示例 5：
        输入：n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]]
        输出：[0,1,1]

    提示：
        1 <= n <= 100
        red_edges.length <= 400
        blue_edges.length <= 400
        red_edges[i].length == blue_edges[i].length == 2
        0 <= red_edges[i][j], blue_edges[i][j] < n
    */

    public static void main(String[] args) {
        int n = 3;
        int[][] red_edges = new int[][]{{0, 1}, {1, 2}}, blue_edges = {};

        int n2 = 3;
        int[][] red_edges2 = new int[][]{{0, 1}}, blue_edges2 = {{2, 1}};

        int n3 = 3;
        int[][] red_edges3 = new int[][]{{1, 0}}, blue_edges3 = {{2, 1}};

        int n4 = 3;
        int[][] red_edges4 = new int[][]{{0, 1}}, blue_edges4 = {{1, 2}};

        int n5 = 3;
        int[][] red_edges5 = new int[][]{{0, 1}, {0, 2}}, blue_edges5 = {{1, 0}};

        System.out.println(Arrays.toString(shortestAlternatingPaths(n, red_edges, blue_edges)));
        System.out.println(Arrays.toString(shortestAlternatingPaths(n2, red_edges2, blue_edges2)));
        System.out.println(Arrays.toString(shortestAlternatingPaths(n3, red_edges3, blue_edges3)));
        System.out.println(Arrays.toString(shortestAlternatingPaths(n4, red_edges4, blue_edges4)));
        System.out.println(Arrays.toString(shortestAlternatingPaths(n5, red_edges5, blue_edges5)));
    }

    private static int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        // 构建邻接表
        List<Integer>[][] edges = new List[2][n];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                edges[i][j] = new ArrayList<>();
            }
        }
        for (int[] redEdge : redEdges) {
            edges[0][redEdge[0]].add(redEdge[1]);
        }
        for (int[] blueEdge : blueEdges) {
            edges[1][blueEdge[0]].add(blueEdge[1]);
        }

        // graph[i][j]=x
        // i代表到达节点j的路径为红色还是蓝色，i=0代表是红色，i=1代表是蓝色
        // j代表第j个节点
        // x代表到达节点j的最小路径
        int[][] graph = new int[2][n];
        Arrays.fill(graph[0], Integer.MAX_VALUE);
        Arrays.fill(graph[1], Integer.MAX_VALUE);
        graph[0][0] = 0;
        graph[1][0] = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0}); // pair[0]为当前节点，pair[1]代表到达当前节点的是红色还是蓝色路径，0为红色，1为蓝色
        queue.offer(new int[]{0, 1});
        while (!queue.isEmpty()) {
            int[] pair = queue.poll();
            int start = pair[0], type = pair[1];
            for (int end : edges[1 - type][start]) {
                if (graph[1 - type][end] != Integer.MAX_VALUE) {
                    continue;
                }
                graph[1 - type][end] = graph[type][start] + 1;
                queue.offer(new int[]{end, 1 - type});
            }
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = Math.min(graph[0][i], graph[1][i]);
            if (res[i] == Integer.MAX_VALUE) {
                res[i] = -1;
            }
        }
        return res;
    }
}
