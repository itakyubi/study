package leetcode;

import model.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * leetcode.CloneGraph
 *
 * @desc:
 * @author: wuao <wuao@baidu.com>
 * @time: 2020-01-15 16:19
 */
public class CloneGraph {

    private Map<Node, Node> cache = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null)
            return null;
        if (cache.containsKey(node))
            return cache.get(node);
        Node cloneNode = new Node(node.val, new ArrayList<Node>());
        cache.put(node, cloneNode);
        node.neighbors.stream().forEach(n -> {
            cloneNode.neighbors.add(cloneGraph(n));
        });
        return cloneNode;
    }

}
