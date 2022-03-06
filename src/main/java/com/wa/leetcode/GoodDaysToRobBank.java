package com.wa.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * GoodDaysToRobBank
 * https://leetcode-cn.com/problems/find-good-days-to-rob-the-bank/
 *
 * @author: wuao
 * @time: 2022/3/6 10:40
 **/
public class GoodDaysToRobBank {

    public static void main(String[] args) {
        int[] security = new int[]{5, 3, 3, 3, 5, 6, 2};
        int time = 2;

        int[] security2 = new int[]{1, 1, 1, 1, 1};
        int time2 = 0;

        System.out.println(goodDaysToRobBank(security, time));
        System.out.println(goodDaysToRobBank(security2, time2));
    }

    private static List<Integer> goodDaysToRobBank(int[] security, int time) {
        List<Integer> res = new ArrayList<>();
        int[] nonIncremental = new int[security.length];
        int[] nonDecreasing = new int[security.length];

        for (int i = 1; i < security.length; i++) {
            if (security[i] <= security[i - 1]) {
                nonIncremental[i] = nonIncremental[i - 1] + 1;
            }
        }

        for (int i = security.length - 2; i >= 0; i--) {
            if (security[i] <= security[i + 1]) {
                nonDecreasing[i] = nonDecreasing[i + 1] + 1;
            }
        }

        for (int i = 0; i < security.length; i++) {
            if (nonIncremental[i] >= time && nonDecreasing[i] >= time) {
                res.add(i);
            }
        }

        return res;
    }
}
