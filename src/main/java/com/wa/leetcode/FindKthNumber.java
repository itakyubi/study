package com.wa.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * FindKthNumber
 * https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order/
 * 2022-03-23 08:57
 *
 * @author wuao
 */
public class FindKthNumber {

    public static void main(String[] args) {
        int n = 13, k = 2;
        int n2 = 1, k2 = 1;
        int n3 = 681692778, k3 = 351251360;

        //System.out.println(findKthNumber3(n, k));
        //System.out.println(findKthNumber3(n2, k2));
        System.out.println(findKthNumber3(n3, k3));
    }

    private static int findKthNumber3(int n, int k) {
        int cur = 1;
        k--;
        while (k > 0) {
            int nodeCount = getNodeCount(cur, n);
            if (k >= nodeCount) {
                k -= nodeCount;
                cur++;
            } else {
                cur *= 10;
                k--;
            }
        }
        return cur;
    }

    // 获取当前前缀下的节点个数，prefix为前缀，n为上边界
    private static int getNodeCount(int prefix, int n) {
        long cur = prefix, next = prefix + 1;
        int count = 0;
        while (cur <= n) {
            count += Math.min(n + 1, next) - cur;
            cur *= 10;
            next *= 10;
        }
        return count;
    }

    // 超时
    private static int findKthNumber2(int n, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, (o1, o2) -> {
            String s1 = Integer.toString(o1);
            String s2 = Integer.toString(o2);
            return s2.compareTo(s1);
        });

        for (int i = 1; i <= n; i++) {
            queue.offer(i);
        }
        for (int i = 0; i < n - k; i++) {
            queue.poll();
        }

        return queue.peek();
    }

    // 超出内存限制
    private static int findKthNumber(int n, int k) {
        String[] s = new String[n];
        for (int i = 1; i <= n; i++) {
            s[i - 1] = String.valueOf(i);
        }

        Arrays.sort(s, String::compareTo);

        return Integer.parseInt(s[k - 1]);
    }
}
