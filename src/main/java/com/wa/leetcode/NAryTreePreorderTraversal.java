package com.wa.leetcode;


import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * NAryTreePreorderTraversal
 * 2022-03-10 12:24
 *
 * @author wuao
 */
public class NAryTreePreorderTraversal {

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


    public List<Integer> preorder(Node root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        res.add(root.val);

        for (Node node : root.children) {
            List<Integer> tmp = preorder(node);
            if (tmp.size() > 0) {
                res.addAll(tmp);
            }
        }

        return res;
    }

    public List<Integer> preorder2(Node root) {
        List<Integer> res = new LinkedList<>();
        helper(root, res);
        return res;
    }

    private void helper(Node root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);

        for (Node node : root.children) {
            helper(node, res);
        }
    }

    public List<Integer> preorder3(Node root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            res.add(node.val);
            for (int i = node.children.size() - 1; i >= 0; i--) {
                stack.push(node.children.get(i));
            }
        }
        return res;
    }

}
