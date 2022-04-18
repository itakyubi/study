package com.wa.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * LexicalOrder
 * https://leetcode-cn.com/problems/lexicographical-numbers/
 * 2022-04-18 09:09
 *
 * @author wuao
 */
public class LexicalOrder {

    public static void main(String[] args) {
        int n = 13;
        int n2 = 2;

        System.out.println(lexicalOrder(n));
        System.out.println(lexicalOrder(n2));
    }

    private static List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();

        int num = 1;
        for (int i = 0; i < n; i++) {
            res.add(num);
            if (num * 10 <= n) {
                num *= 10;
            } else {
                while (num % 10 == 9 || num + 1 > n) {
                    num /= 10;
                }
                num++;
            }
        }
        return res;
    }
}
