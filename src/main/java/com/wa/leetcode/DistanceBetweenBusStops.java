package com.wa.leetcode;

/**
 * DistanceBetweenBusStops
 * https://leetcode.cn/problems/distance-between-bus-stops/
 * 1184. 公交站间的距离
 *
 * @author: wuao
 * @time: 2022/7/24 16:13
 **/
public class DistanceBetweenBusStops {

    /*
    环形公交路线上有 n 个站，按次序从 0 到 n - 1 进行编号。我们已知每一对相邻公交站之间的距离，distance[i] 表示编号为 i 的车站和编号为 (i + 1) % n 的车站之间的距离。
    环线上的公交车都可以按顺时针和逆时针的方向行驶。
    返回乘客从出发点 start 到目的地 destination 之间的最短距离。

    示例 1：
        输入：distance = [1,2,3,4], start = 0, destination = 1
        输出：1
        解释：公交站 0 和 1 之间的距离是 1 或 9，最小值是 1。
    示例 2：
        输入：distance = [1,2,3,4], start = 0, destination = 2
        输出：3
        解释：公交站 0 和 2 之间的距离是 3 或 7，最小值是 3。
    示例 3：
        输入：distance = [1,2,3,4], start = 0, destination = 3
        输出：4
        解释：公交站 0 和 3 之间的距离是 6 或 4，最小值是 4。
    提示：
        1 <= n <= 10^4
        distance.length == n
        0 <= start, destination < n
        0 <= distance[i] <= 10^4
    */

    public static void main(String[] args) {
        int[] distance = new int[]{1, 2, 3, 4};
        int start = 0, destination = 1;

        int[] distance2 = new int[]{1, 2, 3, 4};
        int start2 = 0, destination2 = 2;

        int[] distance3 = new int[]{1, 2, 3, 4};
        int start3 = 0, destination3 = 3;

        System.out.println(distanceBetweenBusStops(distance, start, destination));
        System.out.println(distanceBetweenBusStops(distance2, start2, destination2));
        System.out.println(distanceBetweenBusStops(distance3, start3, destination3));
    }

    private static int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int n = distance.length;
        int cur = start + 1;
        int sum1 = 0, sum2 = 0;
        while (cur % n != destination) {
            sum1 += distance[(cur - 1) % n];
            cur++;
        }
        sum1 += distance[(cur - 1) % n];
        cur++;
        while (cur % n != start) {
            sum2 += distance[(cur - 1) % n];
            cur++;
        }
        sum2 += distance[(cur - 1) % n];
        return Math.min(sum1, sum2);
    }
}
