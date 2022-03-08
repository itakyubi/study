package com.wa.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * PlatesBetweenCandles
 * https://leetcode-cn.com/problems/plates-between-candles/
 * 2022-03-08 09:12
 *
 * @author wuao
 */
public class PlatesBetweenCandles {

    public static void main(String[] args) {
        String s = "**|**|***|";
        int[][] queries = new int[][]{{2, 5}, {5, 9}};

        String s2 = "***|**|*****|**||**|*";
        int[][] queries2 = new int[][]{{1, 17}, {4, 5}, {14, 17}, {5, 11}, {15, 16}};

        System.out.println(Arrays.toString(platesBetweenCandles(s, queries)));
        System.out.println(Arrays.toString(platesBetweenCandles(s2, queries2)));
    }

    // 预处理+前缀和
    private static int[] platesBetweenCandles(String s, int[][] queries) {
        int[] res = new int[queries.length];

        int[] platesCount = new int[s.length()]; // platesCount[i]代表下标为i左边的盘子数量（包含i位置）
        for (int i = 0, count = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*') {
                count++;
            }
            platesCount[i] = count;
        }

        int[] left = new int[s.length()]; // left[i]代表位置i左边第一根蜡烛的下标，如果没有则为-1
        int[] right = new int[s.length()]; // right[i]代表位置i右边第一根蜡烛的下标，如果没有则为-1
        for (int i = 0, l = -1; i < s.length(); i++) {
            if (s.charAt(i) == '|') {
                l = i;
            }
            left[i] = l;
        }
        for (int i = s.length() - 1, r = -1; i >= 0; i--) {
            if (s.charAt(i) == '|') {
                r = i;
            }
            right[i] = r;
        }

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0], r = queries[i][1];
            if (right[l] != -1 && left[r] != -1 && right[l] < left[r]) {
                res[i] = platesCount[left[r]] - platesCount[right[l]];
            }
        }

        return res;
    }

    // TODO 二分查找+前缀和
    private static int[] platesBetweenCandles4(String s, int[][] queries) {
        int[] res = new int[queries.length];

        int[] platesCount = new int[s.length()]; // platesCount[i]代表下标为i左边的盘子数量（包含i位置）
        for (int i = 0, count = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*') {
                count++;
            }
            platesCount[i] = count;
        }

        for (int i = 0; i < queries.length; i++) {
            int leftBoundary, rightBoundary;

            // 找到左边界，即找到比platesCount[queries[i][0]]大1最左边的那个位置，减1即为左边界
            int left = queries[i][0], right = queries[i][1];
            while (left < right) {
                int mid = (left + right) / 2;
                if (platesCount[mid] > platesCount[queries[i][0]] + 1) {
                    right = mid - 1;
                } else if (platesCount[mid] == platesCount[queries[i][0]]) {
                    left = mid + 1;
                } else {
                    // 因为platesCount是非递减的，所以platesCount[mid]一定大于platesCount[queries[i][0]]
                    // 因此这个分支只能是platesCount[mid]==platesCount[queries[i][0]]+1的情况
                    right = mid;
                }
            }
            leftBoundary = left - 1;

            // 找到右边界，即找到比platesCount[queries[i][1]]
            left = queries[i][0];
            right = queries[i][1];
            while (left < right) {
                int mid = (left + right) / 2;
                if (platesCount[mid] < platesCount[queries[i][1]] - 1) {
                    left = mid + 1;
                } else if (platesCount[mid] == platesCount[queries[i][1]]) {
                    right = mid - 1;
                } else {
                    // 因为platesCount是非递减的，所以platesCount[mid]一定小于platesCount[queries[i][1]]
                    // 因此这个分支只能是platesCount[mid]==platesCount[queries[i][1]]-1的情况
                    left = mid;
                }
            }
            rightBoundary = right;

            res[i] = platesCount[rightBoundary] - platesCount[leftBoundary];
        }

        return res;
    }


    // 还是超时
    private static int[] platesBetweenCandles3(String s, int[][] queries) {
        int[] res = new int[queries.length];

        List<Integer> candles = new ArrayList<>(); // 记录每个蜡烛的下标
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '|') {
                candles.add(i);
            }
        }

        for (int i = 0; i < queries.length; i++) {
            int leftCandle = queries[i][0], rightCandle = queries[i][1];

            for (int j = 0; j < candles.size(); j++) {
                if (leftCandle <= candles.get(j)) {
                    leftCandle = j;
                    break;
                }
            }

            int count = 0;
            while (leftCandle < candles.size() - 1 && candles.get(leftCandle + 1) <= rightCandle) {
                count += candles.get(leftCandle + 1) - candles.get(leftCandle) - 1;
                leftCandle++;
            }

            res[i] = count;
        }

        return res;
    }

    // 暴力法超出时间限制
    private static int[] platesBetweenCandles2(String s, int[][] queries) {
        int[] res = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];

            int left = query[0], right = query[1];
            while (left <= right) {
                if (s.charAt(left) == '|') {
                    break;
                } else {
                    left++;
                }
            }
            while (left <= right) {
                if (s.charAt(right) == '|') {
                    break;
                } else {
                    right--;
                }
            }

            if (left < right) {
                for (int j = left + 1; j < right; j++) {
                    if (s.charAt(j) == '*') {
                        res[i]++;
                    }
                }
            }
        }

        return res;
    }
}
