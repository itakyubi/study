package com.wa.test;

/**
 * TTT
 * 2022/8/27 4:26 下午
 *
 * @author wuao
 */
public class TTT {

    public static void main(String[] args) {
        int num = 0;
        int sum = 0, i = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
            i++;
        }
        System.out.println(i);
        System.out.println(sum);
    }
}
