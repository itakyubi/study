package com.wa.leetcode;

/**
 * SolveEquation
 * https://leetcode.cn/problems/solve-the-equation/
 * 640. 求解方程
 * 2022/8/10 4:45 下午
 *
 * @author wuao
 */
public class SolveEquation {

    /*
    求解一个给定的方程，将x以字符串 "x=#value"的形式返回。该方程仅包含 '+' ， '-' 操作，变量x和其对应系数。
    如果方程没有解，请返回"No solution"。如果方程有无限解，则返回 “Infinite solutions” 。
    题目保证，如果方程中只有一个解，则 'x' 的值是一个整数。

    示例 1：
        输入: equation = "x+5-3+x=6+x-2"
        输出: "x=2"
    示例 2:
        输入: equation = "x=x"
        输出: "Infinite solutions"
    示例 3:
        输入: equation = "2x=x"
        输出: "x=0"

    提示:
        3 <= equation.length <= 1000
        equation只有一个'='.
        equation方程由整数组成，其绝对值在[0, 100]范围内，不含前导零和变量 'x' 。
    */

    public static void main(String[] args) {
        String equation = "x+5-3+x=6+x-2";
        String equation2 = "x=x";
        String equation3 = "2x=x";
        String equation4 = "x=x+2";
        String equation5 = "-x=-1";
        String equation6 = "2x+3x-6x=x+2";
        String equation7 = "1+1=x";
        String equation8 = "0x=0";

        System.out.println(solveEquation(equation));
        System.out.println(solveEquation(equation2));
        System.out.println(solveEquation(equation3));
        System.out.println(solveEquation(equation4));
        System.out.println(solveEquation(equation5));
        System.out.println(solveEquation(equation6));
        System.out.println(solveEquation(equation7));
        System.out.println(solveEquation(equation8));
    }

    private static String solveEquation(String equation) {
        int k = 0, b = 0, flag = 1, gFlag = 1;
        for (int i = 0; i < equation.length(); i++) {
            StringBuilder sb = new StringBuilder();
            while (i < equation.length() && equation.charAt(i) >= '0' && equation.charAt(i) <= '9') {
                sb.append(equation.charAt(i));
                i++;
            }

            int num = sb.toString().length() == 0 ? 0 : Integer.parseInt(sb.toString()) * flag * gFlag;
            if (i >= equation.length()) {
                b += num;
            } else {
                switch (equation.charAt(i)) {
                    case 'x':
                        k += sb.toString().length() == 0 ? flag * gFlag : num;
                        break;
                    case '-':
                        b += num;
                        flag = -1;
                        break;
                    case '+':
                        b += num;
                        flag = 1;
                        break;
                    case '=':
                        b += num;
                        gFlag = -1;
                        flag = 1;
                        break;
                }
            }
        }

        if (k == 0) {
            return b == 0 ? "Infinite solutions" : "No solution";
        }
        return "x=" + (-b / k);
    }
}
