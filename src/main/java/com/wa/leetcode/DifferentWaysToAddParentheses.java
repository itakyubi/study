package com.wa.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * DifferentWaysToAddParentheses
 * https://leetcode.cn/problems/different-ways-to-add-parentheses/
 * 241. 为运算表达式设计优先级
 * 2022-07-01 15:51
 *
 * @author wuao
 */
public class DifferentWaysToAddParentheses {

    /*
    给你一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。你可以 按任意顺序 返回答案。
    生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 104 。

    示例 1：
        输入：expression = "2-1-1"
        输出：[0,2]
        解释：
            ((2-1)-1) = 0
            (2-(1-1)) = 2
    示例 2：
        输入：expression = "2*3-4*5"
        输出：[-34,-14,-10,-10,10]
        解释：
            (2*(3-(4*5))) = -34
            ((2*3)-(4*5)) = -14
            ((2*(3-4))*5) = -10
            (2*((3-4)*5)) = -10
            (((2*3)-4)*5) = 10

    提示：
        1 <= expression.length <= 20
        expression 由数字和算符 '+'、'-' 和 '*' 组成。
        输入表达式中的所有整数值在范围 [0, 99] 
    */

    public static void main(String[] args) {
        String expression = "2-1-1";
        String expression2 = "2*3-4*5";

        System.out.println(diffWaysToCompute(expression));
        System.out.println(diffWaysToCompute(expression2));
    }

    private static List<Integer> diffWaysToCompute(String expression) {
        return helper(expression, 0, expression.length() - 1);
    }

    private static List<Integer> helper(String s, int left, int right) {
        List<Integer> res = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9')
                continue;
            List<Integer> leftList = helper(s, left, i - 1);
            List<Integer> rightList = helper(s, i + 1, right);
            for (int l : leftList) {
                for (int r : rightList) {
                    switch (c) {
                        case '+':
                            res.add(l + r);
                            break;
                        case '-':
                            res.add(l - r);
                            break;
                        case '*':
                            res.add(l * r);
                            break;
                    }
                }
            }
        }
        int tmp = 0;
        if (res.isEmpty()) {
            for (int i = left; i <= right; i++) {
                tmp = tmp * 10 + s.charAt(i) - '0';
            }
            res.add(tmp);
        }
        return res;
    }
}
