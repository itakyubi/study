package com.wa.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * CountHighestScoreNodes
 * https://leetcode-cn.com/problems/count-nodes-with-the-highest-score/
 * 2022-03-11 08:58
 *
 * @author wuao
 */
public class CountHighestScoreNodes {

    public static void main(String[] args) {
        int[] parents = new int[]{-1, 2, 0, 2, 0};
        int[] parents2 = new int[]{-1, 2, 0};
        int[] parents3 = new int[]{-1, 3, 10, 12, 0, 9, 4, 6, 9, 4, 12, 10, 0, 3};
        int[] parents4 = new int[]{-1, 8, 9, 7, 6, 2, 9, 8, 0, 0};

        System.out.println(countHighestScoreNodes(parents));
        System.out.println(countHighestScoreNodes(parents2));
        System.out.println(countHighestScoreNodes(parents3));
        System.out.println(countHighestScoreNodes(parents4));
    }

    static class Node {
        List<Node> children; // 子节点

        Node() {
            children = new ArrayList<>();
        }
    }

    private static int total, count;
    private static long max;

    private static int countHighestScoreNodes(int[] parents) {
        max = 0;
        total = parents.length;
        count = 0;

        Node[] nodes = new Node[parents.length];
        for (int i = 0; i < parents.length; i++) {
            nodes[i] = new Node();
        }
        for (int i = 1; i < parents.length; i++) {
            nodes[parents[i]].children.add(nodes[i]);
        }

        helper(nodes[0]);

        return count;
    }

    private static int helper(Node root) {
        if (root == null) {
            return 0;
        }

        long score = 1;
        int parentCount = total - 1;
        int childCount = 0;
        for (Node node : root.children) {
            int count = helper(node);
            if (count > 0) {
                childCount += count;
                parentCount -= count;
                score *= count;
            }
        }

        if (parentCount > 0) {
            score *= parentCount;
        }

        if (score > max) {
            max = score;
            count = 1;
        } else if (score == max) {
            count++;
        }

        return childCount + 1;
    }


}
