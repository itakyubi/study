package com.wa.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GroupThePeople
 * https://leetcode.cn/problems/group-the-people-given-the-group-size-they-belong-to/
 * 1282. 用户分组
 * 2022/8/12 2:20 下午
 *
 * @author wuao
 */
public class GroupThePeople {

    /*
    有n个人被分成数量未知的组。每个人都被标记为一个从 0 到 n - 1 的唯一ID。
    给定一个整数数组 groupSizes ，其中groupSizes[i]是第 i 个人所在的组的大小。例如，如果groupSizes[1] = 3，则第 1 个人必须位于大小为 3 的组中。
    返回一个组列表，使每个人 i 都在一个大小为groupSizes[i]的组中。
    每个人应该恰好只出现在一个组中，并且每个人必须在一个组中。如果有多个答案，返回其中任何一个。可以保证给定输入至少有一个有效的解。

    示例 1：
        输入：groupSizes = [3,3,3,3,3,1,3]
        输出：[[5],[0,1,2],[3,4,6]]
        解释：
            第一组是 [5]，大小为 1，groupSizes[5] = 1。
            第二组是 [0,1,2]，大小为 3，groupSizes[0] = groupSizes[1] = groupSizes[2] = 3。
            第三组是 [3,4,6]，大小为 3，groupSizes[3] = groupSizes[4] = groupSizes[6] = 3。
        其他可能的解决方案有 [[2,1,6],[5],[0,4,3]] 和 [[5],[0,6,2],[4,3,1]]。
    示例 2：
        输入：groupSizes = [2,1,3,3,3,2]
        输出：[[1],[0,5],[2,3,4]]
            
    提示：
        groupSizes.length == n
        1 <= n<= 500
        1 <=groupSizes[i] <= n
    */

    public static void main(String[] args) {
        int[] groupSizes = new int[]{3, 3, 3, 3, 3, 1, 3};
        int[] groupSizes2 = new int[]{2, 1, 3, 3, 3, 2};

        System.out.println(groupThePeople(groupSizes));
        System.out.println(groupThePeople(groupSizes2));
    }

    private static List<List<Integer>> groupThePeople(int[] groupSizes) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i++) {
            List<Integer> list = map.getOrDefault(groupSizes[i], new ArrayList<>());
            list.add(i);
            map.put(groupSizes[i], list);
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int key : map.keySet()) {
            int i = 0;
            List<Integer> list = map.get(key);
            while (i < list.size()) {
                List<Integer> tmp = new ArrayList<>();
                while (tmp.size() < key) {
                    tmp.add(list.get(i));
                    i++;
                }
                res.add(tmp);
            }
        }
        return res;
    }
}
