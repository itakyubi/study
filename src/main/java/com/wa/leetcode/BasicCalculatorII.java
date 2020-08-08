package com.wa.leetcode;

import java.util.Stack;

public class BasicCalculatorII {

    public static void main(String[] args) {
        System.out.println(calculate("3+2*2"));
        System.out.println(calculate(" 3/2 "));
        System.out.println(calculate(" 3+5 / 2 "));
        System.out.println(calculate("1-2+3*4/5"));
    }

    private static String str;
    private static int index;

    public static int calculate(String s) {
        if (s == null || s.length() == 0)
            return 0;

        str = s;
        index = 0;

        Stack<Integer> stack = new Stack<>();
        stack.push(getNum());
        while (index < str.length()) {
            char c = str.charAt(index++);
            int num = getNum();
            if (c == '-') {
                num *= -1;
            } else if (c == '*') {
                num *= stack.pop();
            } else if (c == '/') {
                num = stack.pop() / num;
            }
            stack.push(num);
        }

        int res = 0;
        for (Integer num : stack)
            res += num;
        return res;
    }

    private static int getNum() {
        StringBuilder sb = new StringBuilder();
        char c = str.charAt(index);
        while ((c >= '0' && c <= '9') || c == ' ') {
            if (c != ' ')
                sb.append(c);
            index++;
            if (index >= str.length())
                break;
            c = str.charAt(index);
        }
        return Integer.parseInt(sb.toString());
    }
}
