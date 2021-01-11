package com.wa.interview;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * LRU
 * 2021-01-11 17:43
 *
 * @author wuao
 */
public class LRU {

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
        LRULinkedHashMap lru = new LRULinkedHashMap(k);
        for (int[] operator : operators) {
            if (operator[0] == 1) {
                lru.put(operator[1], operator[2]);
            } else {
                res.add(lru.get(operator[1]));
            }
        }

        return res.stream().mapToInt(i -> i).toArray();
    }

    static class LRULinkedHashMap extends LinkedHashMap<Integer, Integer> {
        private int capacity;

        public LRULinkedHashMap(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key) {
            Integer value = getOrDefault(key, -1);
            if (value != -1) {
                remove(value);
                put(key, value);
            }
            return value;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }


}
