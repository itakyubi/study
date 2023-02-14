package com.wa.leetcode;

import java.util.Stack;

/**
 * LongestWPI
 * https://leetcode.cn/problems/longest-well-performing-interval/
 * 1124. 表现良好的最长时间段
 *
 * @author: wuao
 * @time: 2023/2/14 21:15
 **/
public class LongestWPI {

    /*
    给你一份工作时间表 hours，上面记录着某一位员工每天的工作小时数。
    我们认为当员工一天中的工作小时数大于 8 小时的时候，那么这一天就是「劳累的一天」。
    所谓「表现良好的时间段」，意味在这段时间内，「劳累的天数」是严格 大于「不劳累的天数」。
    请你返回「表现良好时间段」的最大长度。

    示例 1：
        输入：hours = [9,9,6,0,6,6,9]
        输出：3
        解释：最长的表现良好时间段是 [9,9,6]。

    示例 2：
        输入：hours = [6,6,6]
        输出：0

    提示：
        1 <= hours.length <= 104
        0 <= hours[i] <= 16
    */

    public static void main(String[] args) {
        int[] hours = new int[]{9, 9, 6, 0, 6, 6, 9};
        int[] hours2 = new int[]{6, 6, 6};
        int[] hours3 = new int[]{6, 6, 9};

        System.out.println(longestWPI(hours));
        System.out.println(longestWPI(hours2));
        System.out.println(longestWPI(hours3));
    }

    private static int longestWPI(int[] hours) {
        // 当hour>8时，记为1；当hour<=8时，记为-1；
        // preSum[i] 代表前i个元素的前缀和，preSum[j]-preSum[i]就代表hour[i]到hour[j]的和
        // 题目就等同于，在preSum[j]-preSum[i]>0的情况下，求j-i的最大值
        // 我们固定右端点r, r的取值范围为[1,n-1]
        // 依次遍历左端点，找到最大的范围
        // 左端点的可选范围为preSum严格递减的那些端点，假设preSum[i]<preSum[j]，且i<j，那么如果j满足条件的话，i必然满足，且长度更长


        // stack中存放的是节点的下标, 单调递减栈
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int[] preSums = new int[hours.length + 1];
        for (int i = 1; i <= hours.length; i++) {
            // 计算前缀和
            preSums[i] = preSums[i - 1] + (hours[i - 1] > 8 ? 1 : -1);
            if (preSums[stack.peek()] > preSums[i]) {
                stack.push(i);
            }
        }

        int res = 0;
        for (int r = hours.length; r >= 1; r--) {
            while (!stack.isEmpty() && preSums[stack.peek()] < preSums[r]) {
                res = Math.max(res, r - stack.pop());
            }
        }
        return res;
    }
}
