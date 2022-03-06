package com.wa.leetcode;

/**
 * AddDigits
 * https://leetcode-cn.com/problems/add-digits/
 * <p>
 * 2022-03-03 09:06
 *
 * @author wuao
 */
public class AddDigits {

    public static void main(String[] args) {
        int num = 38;
        int num2 = 0;
        System.out.println(addDigits2(num));
        System.out.println(addDigits2(num2));
    }

    private static int addDigits(int num) {
        if (num < 10)
            return num;

        int res = 0;
        while (num > 0) {
            res += num % 10;
            num = num / 10;
        }

        return addDigits(res);
    }

    private static int addDigits2(int num) {
        return (num - 1) % 9 + 1;
    }
}
