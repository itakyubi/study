package com.wa.leetcode;

/**
 * com.wa.leetcode.CanCompleteCircuit
 *
 * @desc:
 * @author: wuao <wuao@baidu.com>
 * @time: 2020-01-15 17:08
 */
public class CanCompleteCircuit {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int start = 0;
        int total_gas = 0;
        int cur_gas = 0;
        for (int i = 0; i < gas.length; i++) {
            total_gas += gas[i] - cost[i];
            cur_gas += gas[i] - cost[i];
            if (cur_gas < 0) {
                start = i + 1;
                cur_gas = 0;
            }
        }
        return total_gas < 0 ? -1 : start;
    }
}
