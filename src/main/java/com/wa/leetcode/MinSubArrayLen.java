package com.wa.leetcode;

/**
 * MinSubArrayLen
 * 2020-07-07 09:56
 *
 * @author wuao
 */
public class MinSubArrayLen {

    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0)
            return 0;
        int res = Integer.MAX_VALUE, start = 0, end = 0, sum = 0;
        while (end < nums.length) {
            sum += nums[end];
            while (sum >= s) {
                res = Math.min(res, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
