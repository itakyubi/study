package com.wa.leetcode;

/**
 * ConvertToBase7
 * https://leetcode-cn.com/problems/base-7/
 * 2022-03-07 08:54
 *
 * @author wuao
 */
public class ConvertToBase7 {

    public static void main(String[] args) {
        int num = 100;
        int num2 = -7;

        System.out.println(convertToBase7(num));
        System.out.println(convertToBase7(num2));
    }

    private static String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();

        int sign = num > 0 ? 1 : -1;
        num = num * sign;

        while (num > 0) {
            int remainder = num % 7;
            int quotient = num / 7;

            sb.append(remainder);
            num = quotient;
        }

        String res = sb.reverse().toString();
        if (sign < 0) {
            return "-" + res;
        }
        return res;
    }
}
