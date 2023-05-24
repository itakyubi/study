package com.wa.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * FrogPosition
 * https://leetcode.cn/problems/frog-position-after-t-seconds/
 * 1377. T 秒后青蛙的位置
 * 2023/5/24 6:06 PM
 *
 * @author wuao
 */
public class FrogPosition {

    /*
    给你一棵由 n 个顶点组成的无向树，顶点编号从 1 到 n。青蛙从 顶点 1 开始起跳。规则如下：
    在一秒内，青蛙从它所在的当前顶点跳到另一个 未访问 过的顶点（如果它们直接相连）。
    青蛙无法跳回已经访问过的顶点。
    如果青蛙可以跳到多个不同顶点，那么它跳到其中任意一个顶点上的机率都相同。
    如果青蛙不能跳到任何未访问过的顶点上，那么它每次跳跃都会停留在原地。
    无向树的边用数组 edges 描述，其中 edges[i] = [ai, bi] 意味着存在一条直接连通 ai 和 bi 两个顶点的边。
    返回青蛙在 t 秒后位于目标顶点 target 上的概率。与实际答案相差不超过 10-5 的结果将被视为正确答案。

    示例 1：
        输入：n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 2, target = 4
        输出：0.16666666666666666
        解释：上图显示了青蛙的跳跃路径。青蛙从顶点 1 起跳，第 1 秒 有 1/3 的概率跳到顶点 2 ，然后第 2 秒 有 1/2 的概率跳到顶点 4，因此青蛙在 2 秒后位于顶点 4 的概率是 1/3 * 1/2 = 1/6 = 0.16666666666666666 。
    示例 2：
        输入：n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 1, target = 7
        输出：0.3333333333333333
        解释：上图显示了青蛙的跳跃路径。青蛙从顶点 1 起跳，有 1/3 = 0.3333333333333333 的概率能够 1 秒 后跳到顶点 7 。

    提示：
        1 <= n <= 100
        edges.length == n - 1
        edges[i].length == 2
        1 <= ai, bi<= n
        1 <= t <= 50
        1 <= target <= n
    */

    public static void main(String[] args) {
        int n = 7, t = 2, target = 4;
        int[][] edges = {{1, 2}, {1, 3}, {1, 7}, {2, 4}, {2, 6}, {3, 5}};

        int n2 = 7, t2 = 1, target2 = 7;
        int[][] edges2 = {{1, 2}, {1, 3}, {1, 7}, {2, 4}, {2, 6}, {3, 5}};

        int n3 = 7, t3 = 20, target3 = 6;
        int[][] edges3 = {{1, 2}, {1, 3}, {1, 7}, {2, 4}, {2, 6}, {3, 5}};

        int n4 = 3, t4 = 1, target4 = 2;
        int[][] edges4 = {{2, 1}, {3, 2}};

        int n5 = 8, t5 = 7, target5 = 7;
        int[][] edges5 = {{2, 1}, {3, 2}, {4, 1}, {5, 1}, {6, 4}, {7, 1}, {8, 7}};

        System.out.println(frogPosition(n, edges, t, target));
        System.out.println(frogPosition(n2, edges2, t2, target2));
        System.out.println(frogPosition(n3, edges3, t3, target3));
        System.out.println(frogPosition(n4, edges4, t4, target4));
        System.out.println(frogPosition(n5, edges5, t5, target5));
    }

    private static double frogPosition(int n, int[][] edges, int t, int target) {
        int originTarget = target;
        int[] parents = new int[n + 1];
        List<Integer>[] children = new List[n + 1];
        for (int i = 0; i < children.length; i++) {
            children[i] = new ArrayList<>();
        }

        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        while (!queue.isEmpty()) {
            int start = queue.poll();
            visited[start] = true;
            for (int[] edge : edges) {
                if (edge[0] == start && !visited[edge[1]]) {
                    queue.offer(edge[1]);
                    parents[edge[1]] = edge[0];
                    children[edge[0]].add(edge[1]);
                } else if (edge[1] == start && !visited[edge[0]]) {
                    queue.offer(edge[0]);
                    parents[edge[0]] = edge[1];
                    children[edge[1]].add(edge[0]);
                }
            }
        }

        double res = 1;
        while (t > 0 && target != 1) {
            int parent = parents[target];
            res *= 1.0 / children[parent].size();
            t--;
            target = parent;
        }


        if (target == 1 && t > 0 && children[originTarget].size() > 0) {
            return 0;
        }
        if (target != 1) {
            return 0;
        }
        return res;
    }
}
