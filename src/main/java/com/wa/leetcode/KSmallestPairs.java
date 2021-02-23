package com.wa.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * KSmallestPairs
 * 2021-01-27 19:18
 *
 * @author wuao
 */
public class KSmallestPairs {

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 2};//{1, 7, 11}; //{1, 2};
        int[] nums2 = {1, 2, 3};//{2, 4, 6}; //{3};
        int k = 10;
        System.out.println(kSmallestPairs(nums1, nums2, k));
    }

    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums1.length, m = nums2.length;
        if (n == 0 || m == 0)
            return res;

        // match数组记录nums1数组中每个元素可用的nums2数组中的最小下标
        // 例如match[1]=2表示nums1数组中的下标为1的元素只能使用nums2中下标大于等于2的元素进行组合
        // 目的是记录可使用的组合，防止重复使用
        int[] match = new int[n];
        while (res.size() < k) {
            int cur = 0;
            for (int i = 1; i < n; i++) {
                // nums1中下标为i的元素在nums2中已经没有可使用的元素了
                if (match[i] == m)
                    continue;
                if (match[cur] == m || nums1[cur] + nums2[match[cur]] > nums1[i] + nums2[match[i]]) {
                    cur = i;
                }
            }
            // 所有的组合都用完了，就跳出循环
            if (match[cur] == m)
                break;
            res.add(Arrays.asList(nums1[cur], nums2[match[cur]]));
            match[cur]++;
        }
        return res;
    }
}
