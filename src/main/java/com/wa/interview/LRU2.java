package com.wa.interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LRU2
 * 2021-01-11 18:16
 *
 * @author wuao
 */
public class LRU2 {


    public static void main(String[] args) {
        int[] res = lruTest(new int[][]{{1, 1, 1}, {1, 2, 2}, {1, 3, 3}, {2, 1}, {1, 4, 4}, {2, 2}}, 3);
        for (int i : res) {
            System.out.println(i);
        }
    }

    /**
     * 输入 [[1,1,1],[1,2,2],[1,3,3],[2,1],[1,4,4],[2,2]], 第一位为1代表put, 为2代表get
     * 输出 [1,-1]
     */
    private static int[] lruTest(int[][] operators, int k) {
        List<Integer> res = new ArrayList<>();
        CustomLru lru = new CustomLru(k);
        for (int[] operator : operators) {
            if (operator[0] == 1) {
                lru.put(operator[1], operator[2]);
            } else {
                res.add(lru.get(operator[1]));
            }
        }

        return res.stream().mapToInt(i -> i).toArray();
    }

    static class CustomLru {
        class Node {
            private int key;
            private int value;
            private Node next;
            private Node pre;

            public Node() {
            }

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private Map<Integer, Node> map = new HashMap<>();
        private Node head = new Node();
        private Node tail = new Node();
        private int capacity;

        public CustomLru(int capacity) {
            this.capacity = capacity;
            head.next = tail;
            tail.pre = head;
        }

        public void put(int key, int value) {
            Node node = map.get(key);
            if (node != null) {
                node.value = value;
                moveToHead(node);
            } else {
                node = new Node(key, value);
                map.put(key, node);
                addToHead(node);
                if (map.size() > capacity) {
                    removeTail();
                }
            }
        }

        public int get(int key) {
            Node node = map.get(key);
            if (node == null)
                return -1;
            moveToHead(node);
            return node.value;
        }

        private void addToHead(Node node) {
            node.pre = head;
            node.next = head.next;
            head.next.pre = node;
            head.next = node;
        }

        private void removeNode(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        private void moveToHead(Node node) {
            removeNode(node);
            addToHead(node);
        }

        private void removeTail() {
            Node node = tail.pre;
            removeNode(node);
            map.remove(node.key);
        }

    }


}
