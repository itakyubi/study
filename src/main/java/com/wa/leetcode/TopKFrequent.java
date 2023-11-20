package com.wa.leetcode;

import cn.hutool.core.lang.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * TopKFrequent
 * 2020-11-25 19:07
 *
 * @author wuao
 */
public class TopKFrequent {
    public static void main(String[] args) {
        int[] res = topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
        for (int num : res) {
            System.out.print(num + ",");
        }
    }


    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Pair<Integer, Integer>> priorityQueue = new PriorityQueue<>(k, (o1, o2) -> o2.getValue() - o1.getValue());
        for (Map.Entry entry : map.entrySet()) {
            priorityQueue.offer(new Pair(entry.getKey(), entry.getValue()));
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = priorityQueue.poll().getKey();
        }

        return res;
    }
}
