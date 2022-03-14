package com.wa.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FindRestaurant
 * https://leetcode-cn.com/problems/minimum-index-sum-of-two-lists/
 * 2022-03-14 09:01
 *
 * @author wuao
 */
public class FindRestaurant {

    public static void main(String[] args) {
        String[] list1 = new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] list2 = new String[]{"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"};

        System.out.println(Arrays.toString(findRestaurant(list1, list2)));
    }

    private static String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }

        List<String> res = new ArrayList<>();
        int indexSum = -1;
        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                int tmpSum = map.get(list2[i]) + i;
                if (indexSum == -1 || tmpSum == indexSum) {
                    res.add(list2[i]);
                    indexSum = tmpSum;
                } else if (tmpSum < indexSum) {
                    indexSum = tmpSum;
                    res.clear();
                    res.add(list2[i]);
                }
            }
        }

        return res.toArray(new String[0]);
    }
}
