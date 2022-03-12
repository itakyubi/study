package com.wa.leetcode;

import java.util.*;

/**
 * NAryTreePostorderTraversal
 * https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/
 *
 * @author: wuao
 * @time: 2022/3/12 14:44
 **/
public class NAryTreePostorderTraversal {

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    private List<Integer> res;

    public List<Integer> postorder(Node root) {
        res = new ArrayList<>();
        helper(root);
        return res;
    }

    private void helper(Node root) {
        if (root == null) {
            return;
        }

        for (Node node : root.children) {
            helper(node);
        }
        res.add(root.val);
    }

    public List<Integer> postorder2(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        Map<Node, Boolean> visited = new HashMap<>();
        while (!stack.isEmpty()) {
            Node node = stack.peek();
            if (visited.containsKey(node) || node.children.size() == 0) {
                node = stack.pop();
                res.add(node.val);
            } else {
                for (int i = node.children.size() - 1; i >= 0; i--) {
                    stack.push(node.children.get(i));
                }
                visited.put(node, true);
            }
        }
        return res;
    }
}
