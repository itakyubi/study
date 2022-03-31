package com.wa.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * SelfDividingNumbers
 * https://leetcode-cn.com/problems/self-dividing-numbers/
 * 2022-03-31 09:18
 *
 * @author wuao
 */
public class SelfDividingNumbers {

    public static void main(String[] args) {
        int left = 1, right = 22;
        int left2 = 47, right2 = 85;

        System.out.println(selfDividingNumbers(left, right));
        System.out.println(selfDividingNumbers(left2, right2));
    }

    private static List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (isSelfDividingNumber(i)) {
                res.add(i);
            }
        }
        return res;
    }

    private static boolean isSelfDividingNumber(int num) {
        int tmp = num;
        while (tmp > 0) {
            if (tmp % 10 == 0) {
                return false;
            }
            if (num % (tmp % 10) != 0) {
                return false;
            }
            tmp = tmp / 10;
        }
        return true;
    }
}
