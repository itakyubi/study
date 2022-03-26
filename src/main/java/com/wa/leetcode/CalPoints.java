package com.wa.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * CalPoints
 * https://leetcode-cn.com/problems/baseball-game/
 *
 * @author: wuao
 * @time: 2022/3/26 15:44
 **/
public class CalPoints {

    public static void main(String[] args) {
        String[] ops = new String[]{"5", "2", "C", "D", "+"};
        String[] ops2 = new String[]{"5", "-2", "4", "C", "D", "9", "+", "+"};
        String[] ops3 = new String[]{"1"};
        String[] ops4 = new String[]{"36", "28", "70", "65", "C", "+", "33", "-46", "84", "C"};

        System.out.println(calPoints(ops));
        System.out.println(calPoints(ops2));
        System.out.println(calPoints(ops3));
        System.out.println(calPoints(ops4));
    }


    private static int calPoints(String[] ops) {
        int sum = 0;
        List<Integer> list = new ArrayList<>();
        for (String op : ops) {
            int n = list.size();
            switch (op) {
                case "+":
                    sum += list.get(n - 1) + list.get(n - 2);
                    list.add(list.get(n - 1) + list.get(n - 2));
                    break;
                case "D":
                    sum += 2 * list.get(n - 1);
                    list.add(2 * list.get(n - 1));
                    break;
                case "C":
                    sum -= list.get(n - 1);
                    list.remove(n - 1);
                    break;
                default:
                    sum += Integer.parseInt(op);
                    list.add(Integer.parseInt(op));
            }
        }
        return sum;
    }
}
