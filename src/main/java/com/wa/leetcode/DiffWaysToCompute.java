package com.wa.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiffWaysToCompute {

    private Map<String, List<Integer>> map = new HashMap<>();

    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<>();
        if (input == null || input.length() == 0)
            return res;

        if (map.containsKey(input))
            return map.get(input);

        int firstNum = 0, index = 0;
        while (index < input.length() && !isOperation(input.charAt(index))) {
            firstNum = firstNum * 10 + input.charAt(index++) - '0';
        }
        if (index == input.length()) {
            res.add(firstNum);
            map.put(input, res);
            return res;
        }

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (isOperation(c)) {
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1));
                for (Integer l : left) {
                    for (Integer r : right) {
                        res.add(calc(l, r, c));
                    }
                }
            }
        }
        map.put(input, res);
        return res;
    }

    public List<Integer> diffWaysToCompute2(String input) {
        List<Integer> nums = new ArrayList<>();
        List<Character> ops = new ArrayList<>();

        int num = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (isOperation(c)) {
                nums.add(num);
                num = 0;
                ops.add(c);
                continue;
            }
            num = num * 10 + c - '0';
        }
        nums.add(num);

        int len = nums.size();
        List<Integer>[][] dp = new ArrayList[len][len];
        for (int i = 0; i < len; i++) {
            List<Integer> res = new ArrayList<>();
            res.add(nums.get(i));
            dp[i][i] = res;
        }

        for (int i = 2; i <= len; i++) {
            for (int j = 0; j < len; j++) {
                int k = i + j - 1;
                if (k >= len)
                    break;
                List<Integer> res = new ArrayList<>();
                for (int l = j; l < k; l++) {
                    List<Integer> left = dp[j][l];
                    List<Integer> right = dp[l + 1][k];
                    for (Integer le : left) {
                        for (Integer ri : right) {
                            res.add(calc(le, ri, ops.get(l)));
                        }
                    }
                }
                dp[j][k] = res;
            }
        }
        return dp[0][len - 1];
    }

    private boolean isOperation(char c) {
        return c == '+' || c == '-' || c == '*';
    }

    private int calc(int a, int b, char c) {
        switch (c) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            default:
                return 0;
        }
    }
}
