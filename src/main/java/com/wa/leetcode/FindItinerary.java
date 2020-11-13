package com.wa.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * FindItinerary
 * 2020-11-13 18:57
 *
 * @author wuao
 */
public class FindItinerary {

    private Map<String, PriorityQueue<String>> map = new HashMap<>();
    private List<String> res = new ArrayList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            String src = ticket.get(0);
            String dst = ticket.get(1);
            if (!map.containsKey(src)) {
                map.put(src, new PriorityQueue<>());
            }
            map.get(src).offer(dst);
        }
        dfs("JFK");
        Collections.reverse(res);
        return res;
    }

    public void dfs(String node) {
        while (map.containsKey(node) && map.get(node).size() > 0) {
            dfs(map.get(node).poll());
        }
        res.add(node);
    }
}
