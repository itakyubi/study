package com.wa.leetcode;

/**
 * FractionAddition
 * https://leetcode.cn/problems/fraction-addition-and-subtraction/
 * 592. 分数加减运算
 * 2022/7/27 4:29 下午
 *
 * @author wuao
 */
public class FractionAddition {

    /*
    给定一个表示分数加减运算的字符串expression，你需要返回一个字符串形式的计算结果。
    这个结果应该是不可约分的分数，即最简分数。如果最终结果是一个整数，例如2，你需要将它转换成分数形式，其分母为1。所以在上述例子中, 2应该被转换为2/1。

    示例1:
        输入:expression= "-1/2+1/2"
        输出: "0/1"
    示例 2:
        输入:expression= "-1/2+1/2+1/3"
        输出: "1/3"
    示例 3:
        输入:expression= "1/3-1/2"
        输出: "-1/6"
    提示:
        输入和输出字符串只包含'0' 到'9'的数字，以及'/', '+' 和'-'。
        输入和输出分数格式均为±分子/分母。如果输入的第一个分数或者输出的分数是正数，则'+'会被省略掉。
        输入只包含合法的最简分数，每个分数的分子与分母的范围是[1,10]。如果分母是1，意味着这个分数实际上是一个整数。
        输入的分数个数范围是 [1,10]。
        最终结果的分子与分母保证是 32 位整数范围内的有效整数。
    */

    public static void main(String[] args) {
        String expression = "-1/2+1/2";
        String expression2 = "-1/2+1/2+1/3";
        String expression3 = "1/3-1/2";

        System.out.println(fractionAddition(expression));
        System.out.println(fractionAddition(expression2));
        System.out.println(fractionAddition(expression3));
    }

    private static String fractionAddition(String expression) {
        int m1 = 0, d1 = 1;

        int i = 0;
        while (i < expression.length()) {
            int sign = 1;
            if (expression.charAt(i) == '-') {
                sign = -1;
                i++;
            } else if (expression.charAt(i) == '+') {
                i++;
            }

            int m2 = 0, d2 = 0;
            while (i < expression.length() && expression.charAt(i) != '/') {
                m2 = m2 * 10 + (expression.charAt(i) - '0');
                i++;
            }
            i++;
            while (i < expression.length() && expression.charAt(i) != '+' && expression.charAt(i) != '-') {
                d2 = d2 * 10 + (expression.charAt(i) - '0');
                i++;
            }

            int tmpM1 = m1 * d2 + m2 * d1 * sign;
            int tmpD1 = d1 * d2;
            if (tmpM1 == 0) {
                tmpD1 = 1;
            } else {
                int gcd = Math.abs(gcd(tmpD1, tmpM1));
                tmpM1 = tmpM1 / gcd;
                tmpD1 = tmpD1 / gcd;
            }
            m1 = tmpM1;
            d1 = tmpD1;
        }
        return "" + m1 + "/" + d1;
    }

    private static int gcd(int num1, int num2) {
        int remainder = num1 % num2;
        while (remainder != 0) {
            num1 = num2;
            num2 = remainder;
            remainder = num1 % num2;
        }
        return num2;
    }
}
