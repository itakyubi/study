package com.wa.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * MiceAndCheese
 * https://leetcode.cn/problems/mice-and-cheese/
 * 2611. 老鼠和奶酪
 * 2023/6/7 8:51 AM
 *
 * @author wuao
 */
public class MiceAndCheese {

    /*
    有两只老鼠和n块不同类型的奶酪，每块奶酪都只能被其中一只老鼠吃掉。
    下标为 i处的奶酪被吃掉的得分为：
    如果第一只老鼠吃掉，则得分为reward1[i]。
    如果第二只老鼠吃掉，则得分为reward2[i]。
    给你一个正整数数组reward1，一个正整数数组reward2，和一个非负整数k。
    请你返回第一只老鼠恰好吃掉 k块奶酪的情况下，最大得分为多少。

    示例 1：
        输入：reward1 = [1,1,3,4], reward2 = [4,4,1,1], k = 2
        输出：15
        解释：这个例子中，第一只老鼠吃掉第 2和 3 块奶酪（下标从 0 开始），第二只老鼠吃掉第 0 和 1 块奶酪。
        总得分为 4 + 4 + 3 + 4 = 15 。
        15 是最高得分。
    示例 2：
        输入：reward1 = [1,1], reward2 = [1,1], k = 2
        输出：2
        解释：这个例子中，第一只老鼠吃掉第 0 和 1 块奶酪（下标从 0 开始），第二只老鼠不吃任何奶酪。
        总得分为 1 + 1 = 2 。
        2 是最高得分。

    提示：
        1 <= n == reward1.length == reward2.length <= 105
        1 <= reward1[i],reward2[i] <= 1000
        0 <= k <= n
    */

    public static void main(String[] args) {
        int[] reward1 = {1, 1, 3, 4}, reward2 = {4, 4, 1, 1};
        int k = 2;
        int[] reward12 = {1, 1}, reward22 = {1, 1};
        int k2 = 2;
        int[] reward13 = {4, 1, 5, 3, 3}, reward23 = {3, 4, 4, 5, 2};
        int k3 = 3;
        int[] reward14 = {1, 4, 4, 6, 4}, reward24 = {6, 5, 3, 6, 1};
        int k4 = 1;

        System.out.println(miceAndCheese(reward1, reward2, k));
        System.out.println(miceAndCheese(reward12, reward22, k2));
        System.out.println(miceAndCheese(reward13, reward23, k3));
        System.out.println(miceAndCheese(reward14, reward24, k4));
    }

    private static int miceAndCheese(int[] reward1, int[] reward2, int k) {
        // res = sum(reward2) + max(sum(reward1[i]-reward2[i]))
        int sum = 0, n = reward1.length;
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            diff[i] = reward1[i] - reward2[i];
            sum += reward2[i];
        }

        Arrays.sort(diff);
        for (int i = 0; i < k; i++) {
            sum += diff[n - i - 1];
        }
        return sum;
    }

    // 第一只老鼠不一定会吃最大的
    private static int miceAndCheese2(int[] reward1, int[] reward2, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] == o2[1] ? o1[2] - o2[2] : o2[1] - o1[1]);
        for (int i = 0; i < reward1.length; i++) {
            queue.add(new int[]{i, reward1[i], reward2[i]});
        }
        Set<Integer> set = new HashSet<>();
        int res = 0;
        for (int i = 0; i < k; i++) {
            int[] tmp = queue.poll();
            set.add(tmp[0]);
            res += tmp[1];
        }
        for (int i = 0; i < reward2.length; i++) {
            if (!set.contains(i)) {
                res += reward2[i];
            }
        }
        return res;
    }
}
