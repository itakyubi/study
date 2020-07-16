package com.wa.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * Orchestration
 * 2020-07-14 10:39
 *
 * @author wuao
 */


public class Orchestration {

    static class Node {
        List<Node> nextCells; // size()为节点的出度
        int indegree; // 节点的入度
        int readyNodeCount; // 前置节点就绪个数
    }

    public static void main(String[] args) {
        // TODO 构造 node graph

        // 获取入度为0的节点
        List<Node> nodes = genNodes();
        List<Node> zeroIndegreeNodes = nodes.stream().filter(node -> node.indegree == 0).collect(Collectors.toList());

        // 构造起始节点
        Node root = new Node();
        root.nextCells = zeroIndegreeNodes;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        // 遍历节点
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (Node n : node.nextCells) {
                go(n);
                if (!queue.contains(n)) {
                    queue.offer(n);
                }
            }

        }
    }

    private static void go(Node node) {
        while (node.indegree == 0 || node.readyNodeCount == node.indegree) {
            // TODO 执行node

            node.readyNodeCount = 0;
            for (Node n : node.nextCells) {
                n.readyNodeCount++;
            }
        }
    }

    private static List<Node> genNodes() {
        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();
        Node node4 = new Node();
        Node node5 = new Node();
        Node node6 = new Node();
        Node node7 = new Node();
        Node node8 = new Node();

        List<Node> temp1 = new ArrayList<>();
        temp1.add(node2);
        temp1.add(node3);
        node1.nextCells = temp1;

        List<Node> temp2 = new ArrayList<>();
        temp2.add(node4);
        node2.nextCells = temp2;
        node2.indegree = 2;

        List<Node> temp3 = new ArrayList<>();
        temp3.add(node5);
        node3.nextCells = temp3;
        node3.indegree = 1;

        List<Node> temp4 = new ArrayList<>();
        temp4.add(node6);
        node4.nextCells = temp4;
        node4.indegree = 2;

        List<Node> temp5 = new ArrayList<>();
        temp5.add(node4);
        node5.nextCells = temp5;
        node5.indegree = 1;

        List<Node> temp6 = new ArrayList<>();
        temp6.add(node7);
        node6.nextCells = temp6;
        node6.indegree = 1;

        node7.nextCells = new ArrayList<>();
        node7.indegree = 1;

        List<Node> temp8 = new ArrayList<>();
        temp8.add(node2);
        node8.nextCells = temp8;

        List<Node> nodes = new ArrayList<>();
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);
        nodes.add(node4);
        nodes.add(node5);
        nodes.add(node6);
        nodes.add(node7);
        nodes.add(node8);

        return nodes;
    }
}
