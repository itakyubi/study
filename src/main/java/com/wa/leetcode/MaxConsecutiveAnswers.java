package com.wa.leetcode;

/**
 * MaxConsecutiveAnswers
 * https://leetcode-cn.com/problems/maximize-the-confusion-of-an-exam/
 * 2022-03-29 09:04
 *
 * @author wuao
 */
public class MaxConsecutiveAnswers {

    public static void main(String[] args) {
        String answerKey = "TTFF";
        int k = 2;

        String answerKey2 = "TFFT";
        int k2 = 1;

        String answerKey3 = "TTFTTFTT";
        int k3 = 1;


        System.out.println(maxConsecutiveAnswers(answerKey, k));
        System.out.println(maxConsecutiveAnswers(answerKey2, k2));
        System.out.println(maxConsecutiveAnswers(answerKey3, k3));
    }

    private static int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(helper(answerKey, k, 'T'), helper(answerKey, k, 'F'));
    }

    private static int helper(String answerKey, int k, char c) {
        int left = 0, right = 0, max = 0, count = 0;
        while (right < answerKey.length()) {
            if (answerKey.charAt(right) != c) {
                count++;
            }
            while (count > k) {
                if (answerKey.charAt(left) != c) {
                    count--;
                }
                left++;
            }
            max = Math.max(max, right - left + 1);
            right++;
        }
        return max;
    }
}
