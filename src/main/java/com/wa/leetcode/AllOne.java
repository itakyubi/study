package com.wa.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * AllOne
 * https://leetcode-cn.com/problems/all-oone-data-structure/
 *
 * @author: wuao
 * @time: 2022/3/16 20:00
 **/
public class AllOne {

    // 每种出现次数都是一个Node
    class Node {
        Node prev;
        Node next;
        int count;
        Set<String> keys;

        Node(int count) {
            this.count = count;
            this.keys = new HashSet<>();
        }

        void add(String key) {
            this.keys.add(key);
        }

        void remove(String key) {
            this.keys.remove(key);
        }

    }

    private Map<String, Node> map;
    private Node head, tail;

    public AllOne() {
        this.map = new HashMap<>();
        this.head = new Node(-1);
        this.tail = new Node(-1);
        head.next = tail;
        tail.prev = head;
    }

    public void inc(String key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.remove(key);

            Node next;
            if (node.next.count == node.count + 1) {
                next = node.next;
            } else {
                next = new Node(node.count + 1);
                insertToBehind(node, next);
            }
            next.add(key);
            map.put(key, next);
            clearEmptyNode(node);
        } else {
            Node next;
            if (head.next.count == 1) {
                next = head.next;
            } else {
                next = new Node(1);
                insertToBehind(head, next);
            }
            next.add(key);
            map.put(key, head.next);
        }
    }

    private void insertToBehind(Node node, Node next) {
        next.next = node.next;
        next.prev = node;
        node.next = next;
        next.next.prev = next;
    }

    private void clearEmptyNode(Node node) {
        if (!node.keys.isEmpty())
            return;
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void dec(String key) {
        Node node = map.get(key);
        node.remove(key);

        if (node.count == 1) {
            map.remove(key);
        } else {
            Node next;
            if (node.prev.count == node.count - 1) {
                next = node.prev;
            } else {
                next = new Node(node.count - 1);
                insertToBehind(node.prev, next);
            }
            next.add(key);
            map.put(key, next);
        }
        clearEmptyNode(node);
    }

    public String getMaxKey() {
        Node node = tail.prev;
        if (node == head) {
            return "";
        }
        return node.keys.iterator().next();
    }

    public String getMinKey() {
        Node node = head.next;
        if (node == tail) {
            return "";
        }
        return node.keys.iterator().next();
    }
}
