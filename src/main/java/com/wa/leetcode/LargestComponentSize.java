package com.wa.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LargestComponentSize
 * https://leetcode.cn/problems/largest-component-size-by-common-factor/
 * 952. 按公因数计算最大组件大小
 *
 * @author: wuao
 * @time: 2022/7/30 14:27
 **/
public class LargestComponentSize {

    /*
    给定一个由不同正整数的组成的非空数组 nums ，考虑下面的图：
    有 nums.length 个节点，按从 nums[0] 到 nums[nums.length - 1] 标记；
    只有当 nums[i] 和 nums[j] 共用一个大于 1 的公因数时，nums[i] 和 nums[j]之间才有一条边。
    返回 图中最大连通组件的大小 。          

    示例 1：
        输入：nums = [4,6,15,35]
        输出：4
    示例 2：
        输入：nums = [20,50,9,63]
        输出：2
    示例 3：
        输入：nums = [2,3,6,7,4,12,21,39]
        输出：8
    提示：
        1 <= nums.length <= 2 * 104
        1 <= nums[i] <= 105
        nums 中所有值都 不同
    */

    public static void main(String[] args) {
        int[] nums = new int[]{4, 6, 15, 35};
        int[] nums2 = new int[]{20, 50, 9, 63};
        int[] nums3 = new int[]{2, 3, 6, 7, 4, 12, 21, 39};

        System.out.println(largestComponentSize(nums));
        System.out.println(largestComponentSize(nums2));
        System.out.println(largestComponentSize(nums3));
    }

    private static int[] parents = new int[20010];
    private static int[] sizes = new int[20010];
    private static int res = 1;

    private static int largestComponentSize(int[] nums) {
        int n = nums.length;
        // key为质因子，value为包含该质因子的元素下标
        Map<Integer, List<Integer>> map = new HashMap<>();
        // 计算所有数的质因数，并存到map中
        for (int i = 0; i < n; i++) {
            int tmp = nums[i];
            for (int j = 2; j * j <= tmp; j++) {
                if (tmp % j == 0) {
                    List<Integer> list = map.getOrDefault(j, new ArrayList<>());
                    list.add(i);
                    map.put(j, list);
                }
                while (tmp % j == 0) {
                    tmp /= j;
                }
            }
            if (tmp > 1) {
                List<Integer> list = map.getOrDefault(tmp, new ArrayList<>());
                list.add(i);
                map.put(tmp, list);
            }
        }

        for (int i = 0; i <= n; i++) {
            parents[i] = i;
            sizes[i] = 1;
        }
        for (int key : map.keySet()) {
            List<Integer> list = map.get(key);
            for (int i = 1; i < list.size(); i++) {
                union(list.get(0), list.get(i));
            }
        }
        return res;
    }

    private static void union(int a, int b) {
        if (findRoot(a) == findRoot(b))
            return;
        sizes[findRoot(a)] += sizes[findRoot(b)];
        parents[findRoot(b)] = parents[findRoot(a)];
        res = Math.max(res, sizes[findRoot(a)]);
    }

    private static int findRoot(int x) {
        if (parents[x] != x) {
            parents[x] = findRoot(parents[x]);
        }
        return parents[x];
    }
}
