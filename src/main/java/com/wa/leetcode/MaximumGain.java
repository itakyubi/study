package com.wa.leetcode;

import java.util.Stack;

/**
 * MaximumGain
 * https://leetcode-cn.com/problems/maximum-score-from-removing-substrings/
 * <p>
 * 2022-02-24 09:11
 *
 * @author wuao
 */
public class MaximumGain {
    public static void main(String[] args) {
        String s1 = "cdbcbbaaabab";
        int x1 = 4, y1 = 5;

        String s2 = "aabbaaxybbaabb";
        int x2 = 5, y2 = 4;

        String s3 = "aabbabkbbbfvybssbtaobaaaabataaadabbbmakgabbaoapbbbbobaabvqhbbzbbkapabaavbbeghacabamdpaaqbqabbjbababmbakbaabajabasaabbwabrbbaabbafubayaazbbbaababbaaha";
        int x3 = 1926, y3 = 4320;

        String s4 = "aabbab";
        int x4 = 4, y4 = 5;

        System.out.println(maximumGain2(s1, x1, y1));
        System.out.println(maximumGain2(s2, x2, y2));
        System.out.println(maximumGain2(s3, x3, y3));
        System.out.println(maximumGain2(s4, x4, y4));

    }

    private static int maximumGain(String s, int x, int y) {
        int res = 0;

        Stack<Character> stack = new Stack<>();

        int max = Math.max(x, y);
        int min = Math.min(x, y);
        char priority = x > y ? 'b' : 'a';

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'a' || c == 'b') {
                if (!stack.isEmpty() && c == priority && stack.peek() != priority) {
                    stack.pop();
                    res += max;
                } else {
                    stack.push(c);
                }
            } else {
                res += calRes(stack, min);
                stack.clear();
            }
        }

        res += calRes(stack, min);
        return res;
    }

    private static int calRes(Stack<Character> stack, int score) {
        int res = 0;
        Stack<Character> tmpStack = new Stack<>();
        while (!stack.isEmpty() && stack.size() > 1) {
            tmpStack.push(stack.pop());
            while (!stack.isEmpty() && !tmpStack.isEmpty() && stack.peek() != tmpStack.peek()) {
                stack.pop();
                tmpStack.pop();
                res += score;
            }
        }
        return res;
    }

    private static int maximumGain2(String s, int x, int y) {
        int res = 0;
        int cFirst = 0, cSecond = 0;

        int max, min, first, second;
        if (x < y) {
            max = y;
            min = x;
            first = 'a';
            second = 'b';
        } else {
            max = x;
            min = y;
            first = 'b';
            second = 'a';
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == second) {
                cSecond++;
            } else if (c == first) {
                if (cSecond > 0) {
                    cSecond--;
                    res += max;
                } else {
                    cFirst++;
                }
            } else {
                res += min * Math.min(cFirst, cSecond);
                cFirst = 0;
                cSecond = 0;
            }
        }

        return res + min * Math.min(cFirst, cSecond);
    }
}


