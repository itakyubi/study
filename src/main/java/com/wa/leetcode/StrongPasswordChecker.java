package com.wa.leetcode;

/**
 * StrongPasswordChecker
 * https://leetcode-cn.com/problems/strong-password-checker/
 * 2022-04-02 09:13
 *
 * @author wuao
 */
public class StrongPasswordChecker {

    public static void main(String[] args) {
        String password = "a";
        String password2 = "aA1";
        String password3 = "1337C0d3";

        System.out.println(strongPasswordChecker(password));
        System.out.println(strongPasswordChecker(password2));
        System.out.println(strongPasswordChecker(password3));
    }

    private static int strongPasswordChecker(String password) {
        int n = password.length();

        int lowerFlag = 0, upperFlag = 0, numFlag = 0;
        for (int i = 0; i < n; i++) {
            char c = password.charAt(i);
            if (c >= 'a' && c <= 'z') {
                lowerFlag = 1;
            } else if (c >= 'A' && c <= 'Z') {
                upperFlag = 1;
            } else if (c >= '0' && c <= '9') {
                numFlag = 1;
            }
        }

        int count = 0;
        int[] cnts = new int[3];
        int start = 0;
        while (start < n) {
            char c = password.charAt(start);
            int index = start;
            while (index < n && password.charAt(index) == c) {
                index++;
            }
            if (index - start >= 3) {
                count += (index - start) / 3;
                cnts[(index - start) % 3]++;
            }
            start = index;
        }

        if (n < 6) {
            return Math.max(6 - n, 3 - lowerFlag - upperFlag - numFlag);
        } else if (n <= 20) {
            return Math.max(count, 3 - lowerFlag - upperFlag - numFlag);
        } else {
            int base = n - 20, cur = base;
            for (int i = 0; i < 3; i++) {
                if (i == 2) {
                    cnts[i] = count;
                }
                if (cnts[i] != 0 && cur != 0) {
                    int t = Math.min(cnts[i] * (i + 1), cur);
                    cur -= t;
                    count -= t / (i + 1);
                }
            }
            return base + Math.max(count, 3 - lowerFlag - upperFlag - numFlag);
        }
    }
}
