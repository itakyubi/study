package com.wa.leetcode;

/**
 * com.wa.leetcode.SingleNumber
 *
 * @desc:
 * @author: wuao <wuao@baidu.com>
 * @time: 2020-01-16 11:49
 */
public class SingleNumber {

    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int num : nums) {
                count += (num >> i) & 1;
            }
            res |= (count % 3) << i;
        }
        return res;
    }

    public int singleNumber2(int[] nums){
        int a = 0, b = 0;
        for (int x : nums) {
            b = (b ^ x) & ~a;
            a = (a ^ x) & ~b;
        }
        return b;
    }
}
