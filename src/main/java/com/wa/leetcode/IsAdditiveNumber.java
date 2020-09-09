package com.wa.leetcode;

/**
 * IsAdditiveNumber
 * 2020-09-09 18:53
 *
 * @author wuao
 */
public class IsAdditiveNumber {

    public boolean isAdditiveNumber(String num) {
        if (num.length() < 3)
            return false;
        int n = num.length();
        for (int i = 0; i <= n / 2; i++) {
            for (int j = i + 1; n - j >= j - i && n - j >= i; j++) {
                if (check(num.substring(0, i + 1), num.substring(i + 1, j + 1), num.substring(j + 1)))
                    return true;
            }
        }
        return false;
    }

    private boolean check(String num1, String num2, String num3) {
        if ((num1.length() > 1 && num1.charAt(0) == '0') || (num2.length() > 1 && num2.charAt(0) == '0'))
            return false;
        String sum = strAdd(num1, num2);
        if (sum.equals(num3))
            return true;
        if (!num3.startsWith(sum)) {
            return false;
        } else {
            return check(num2, sum, num3.substring(sum.length()));
        }
    }

    private String strAdd(String s1, String s2) {
        StringBuilder res = new StringBuilder();
        int i = s1.length() - 1, j = s2.length() - 1;
        int carry = 0, sum;
        while (i >= 0 || j >= 0) {
            sum = carry;
            sum += i >= 0 ? s1.charAt(i--) - '0' : 0;
            sum += j >= 0 ? s2.charAt(j--) - '0' : 0;
            carry = sum / 10;
            res.append(sum % 10);
        }
        if (carry > 0) {
            res.append(carry);
        }
        return res.reverse().toString();
    }
}
