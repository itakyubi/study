package com.wa.leetcode;

import java.util.Arrays;

/**
 * FillCups
 * https://leetcode.cn/problems/minimum-amount-of-time-to-fill-cups/
 * 2335. 装满杯子需要的最短总时长
 * 2023/2/11 3:24 下午
 *
 * @author wuao
 */
public class FillCups {

    /*
    现有一台饮水机，可以制备冷水、温水和热水。每秒钟，可以装满 2 杯 不同 类型的水或者 1 杯任意类型的水。
    给你一个下标从 0 开始、长度为 3 的整数数组 amount ，其中 amount[0]、amount[1] 和 amount[2] 分别表示需要装满冷水、温水和热水的杯子数量。返回装满所有杯子所需的 最少 秒数。

    示例 1：
        输入：amount = [1,4,2]  131 120 010 000
        输出：4
        解释：下面给出一种方案：
        第 1 秒：装满一杯冷水和一杯温水。
        第 2 秒：装满一杯温水和一杯热水。
        第 3 秒：装满一杯温水和一杯热水。
        第 4 秒：装满一杯温水。
        可以证明最少需要 4 秒才能装满所有杯子。
    示例 2：
        输入：amount = [5,4,4]  434 333 223 212 111 001 000
        输出：7
        解释：下面给出一种方案：
        第 1 秒：装满一杯冷水和一杯热水。
        第 2 秒：装满一杯冷水和一杯温水。
        第 3 秒：装满一杯冷水和一杯温水。
        第 4 秒：装满一杯温水和一杯热水。
        第 5 秒：装满一杯冷水和一杯热水。
        第 6 秒：装满一杯冷水和一杯温水。
        第 7 秒：装满一杯热水。
    示例 3：
        输入：amount = [5,0,0]
        输出：5
        解释：每秒装满一杯冷水。

    提示：
        amount.length == 3
        0 <= amount[i] <= 100
    */

    public static void main(String[] args) {
        int[] amount = new int[]{1, 4, 2};
        int[] amount2 = new int[]{5, 4, 4};
        int[] amount3 = new int[]{5, 0, 0};
        int[] amount4 = new int[]{4, 1, 4};

        System.out.println(fillCups(amount));
        System.out.println(fillCups(amount2));
        System.out.println(fillCups(amount3));
        System.out.println(fillCups(amount4));
    }

    private static int fillCups(int[] amount) {
        Arrays.sort(amount);
        int a = amount[0], b = amount[1], c = amount[2];
        if (a + b <= c) {
            return c;
        } else {
            // a+b-c是c装完后，剩余的a和b的总数量
            // 然后a和b两两匹配即可，+1是为了兼容剩余奇数的情况
            return c + (a + b - c + 1) / 2;
        }
    }
}
