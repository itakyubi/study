package com.wa.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * CanReorderDoubled
 * https://leetcode-cn.com/problems/array-of-doubled-pairs/
 * 2022-04-01 09:07
 *
 * @author wuao
 */
public class CanReorderDoubled {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, 3, 6};
        int[] arr2 = new int[]{2, 1, 2, 6};
        int[] arr3 = new int[]{4, -2, 2, -4};
        int[] arr4 = new int[]{1, 2, 4, 16, 8, 4};
        int[] arr5 = new int[]{2, 4, 0, 0, 8, 1};
        int[] arr6 = new int[]{1, 2, 1, -8, 8, -4, 4, -4, 2, -2};

        System.out.println(canReorderDoubled2(arr));
        System.out.println(canReorderDoubled2(arr2));
        System.out.println(canReorderDoubled2(arr3));
        System.out.println(canReorderDoubled2(arr4));
        System.out.println(canReorderDoubled2(arr5));
        System.out.println(canReorderDoubled2(arr6));

    }

    private static boolean canReorderDoubled2(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : arr) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }

        List<Integer> list = new ArrayList<>(map.keySet());
        list.sort(Comparator.comparingInt(Math::abs));

        for (int num : list) {
            int doubled = 2 * num;
            if (map.getOrDefault(doubled, 0) < map.get(num)) {
                return false;
            }

            map.put(doubled, map.getOrDefault(doubled, 0) - map.get(num));
        }
        return true;
    }

    private static boolean canReorderDoubled(int[] arr) {
        PriorityQueue<Integer> pos = new PriorityQueue<>((o1, o2) -> o1 - o2);
        PriorityQueue<Integer> neg = new PriorityQueue<>((o1, o2) -> o2 - o1);
        Map<Integer, Integer> map = new HashMap<>();

        for (int a : arr) {
            if (a >= 0) {
                pos.add(a);
            } else {
                neg.add(a);
            }
            map.put(a, map.getOrDefault(a, 0) + 1);
        }

        // 奇数个正数直接返回false
        if ((pos.size() & 1) != 0) {
            return false;
        }

        return helper(pos, map) && helper(neg, map);
    }

    private static boolean helper(PriorityQueue<Integer> queue, Map<Integer, Integer> map) {
        while (queue.size() > 0) {
            int num = queue.poll();
            int doubled = 2 * num;

            if (!map.containsKey(num)) {
                continue;
            }
            decrCount(map, num);

            if (!map.containsKey(doubled)) {
                return false;
            }
            decrCount(map, doubled);
        }
        return true;
    }

    private static void decrCount(Map<Integer, Integer> map, int num) {
        int count = map.get(num);
        count--;
        if (count == 0) {
            map.remove(num);
        } else {
            map.put(num, count);
        }
    }
}
