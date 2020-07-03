package com.wa.model;

import java.util.List;

/**
 * Node
 *
 * @desc:
 * @author: wuao <wuao@baidu.com>
 * @time: 2020-01-15 16:19
 */
public class Node {

    public int val;
    public List<Node> neighbors;

    public Node() {
    }

    public Node(int _val, List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }

}
