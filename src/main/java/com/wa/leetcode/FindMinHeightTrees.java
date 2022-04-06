package com.wa.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * FindMinHeightTrees
 * https://leetcode-cn.com/problems/minimum-height-trees/
 * 2022-04-06 09:24
 *
 * @author wuao
 */
public class FindMinHeightTrees {

    public static void main(String[] args) {
        int n = 4;
        int[][] edges = new int[][]{{1, 0}, {1, 2}, {1, 3}};

        int n2 = 6;
        int[][] edges2 = new int[][]{{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}};

        System.out.println(findMinHeightTrees(n, edges));
        System.out.println(findMinHeightTrees(n2, edges2));
    }


    private static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(0);
            return res;
        }

        // 初始化邻接表
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        // 记录每个节点的度和邻接节点
        int[] degrees = new int[n];
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
            degrees[edge[0]]++;
            degrees[edge[1]]++;
        }

        // 找到所有度为1的节点
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degrees[i] == 1) {
                queue.offer(i);
            }
        }

        // 删除度为1的节点
        while (!queue.isEmpty()) {
            res = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int node = queue.poll();
                res.add(node);
                for (int v : adj[node]) {
                    degrees[v]--;
                    if (degrees[v] == 1) {
                        queue.offer(v);
                    }
                }
            }
        }

        return res;
    }
}
