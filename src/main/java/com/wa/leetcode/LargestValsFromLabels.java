package com.wa.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LargestValsFromLabels
 * https://leetcode.cn/problems/largest-values-from-labels/
 * 1090. 受标签影响的最大值
 * 2023/5/23 9:27 AM
 *
 * @author wuao
 */
public class LargestValsFromLabels {

    /*
    我们有一个n项的集合。给出两个整数数组values和 labels，第 i 个元素的值和标签分别是values[i]和labels[i]。
    还会给出两个整数numWanted和 useLimit 。
    从 n 个元素中选择一个子集 s :
    子集 s 的大小小于或等于 numWanted 。
    s 中 最多 有相同标签的 useLimit 项。
    一个子集的分数是该子集的值之和。
    返回子集s 的最大 分数 。

    示例 1：
        输入：values = [5,4,3,2,1], labels = [1,1,2,2,3], numWanted = 3, useLimit = 1
        输出：9
        解释：选出的子集是第一项，第三项和第五项。
    示例 2：
        输入：values = [5,4,3,2,1], labels = [1,3,3,3,2], numWanted = 3, useLimit = 2
        输出：12
        解释：选出的子集是第一项，第二项和第三项。
    示例 3：
        输入：values = [9,8,8,7,6], labels = [0,0,0,1,1], numWanted = 3, useLimit = 1
        输出：16
        解释：选出的子集是第一项和第四项。

    提示：
        n == values.length == labels.length
        1 <= n <= 2 * 10^4
        0 <= values[i], labels[i] <= 2 * 10^4
        1 <= numWanted, useLimit <= n
    */

    public static void main(String[] args) {
        int[] values = {5, 4, 3, 2, 1}, labels = {1, 1, 2, 2, 3};
        int numWanted = 3, useLimit = 1;

        int[] values2 = {5, 4, 3, 2, 1}, labels2 = {1, 3, 3, 3, 2};
        int numWanted2 = 3, useLimit2 = 2;

        int[] values3 = {9, 8, 8, 7, 6}, labels3 = {0, 0, 0, 1, 1};
        int numWanted3 = 3, useLimit3 = 1;

        System.out.println(largestValsFromLabels(values, labels, numWanted, useLimit));
        System.out.println(largestValsFromLabels(values2, labels2, numWanted2, useLimit2));
        System.out.println(largestValsFromLabels(values3, labels3, numWanted3, useLimit3));
    }

    private static int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        Map<Integer, Integer> count = new HashMap<>();
        int n = values.length;
        int[][] numWithLabel = new int[n][2];
        for (int i = 0; i < n; i++) {
            numWithLabel[i][0] = values[i];
            numWithLabel[i][1] = labels[i];
        }

        Arrays.sort(numWithLabel, (a, b) -> b[0] - a[0]);

        int res = 0, cnt = 0;
        for (int i = 0; i < n; i++) {
            if (cnt >= numWanted) {
                break;
            }
            int label = numWithLabel[i][1];
            if (count.containsKey(label) && count.get(label) == useLimit) {
                continue;
            }

            int value = numWithLabel[i][0];
            res += value;
            count.put(label, count.getOrDefault(label, 0) + 1);
            cnt++;
        }

        return res;
    }
}
