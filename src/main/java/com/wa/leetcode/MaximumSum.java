package com.wa.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * MaximumSum
 * 2342. 数位和相等数对的最大和
 * 2023/11/18 9:02 上午
 *
 * @author wuao
 */
public class MaximumSum {

    /*
    给你一个下标从0开始的数组nums，数组中的元素都是正整数。请你选出两个下标i和j，且nums[i]的数位和与nums[j]的数位和相等。
    请你找出所有满足条件的下标i和j，找出并返回nuns[i]+nums[j]可以得到的最大值。

    示例 1：
        输入：nums=[18,43,36,13,7]
        输出：54
    示例 2：
        输入：nums=[10,12,19,14]
        输出：-1
    */

    public static void main(String[] args) {
        int[] nums1 = new int[]{18,43,36,13,7};
        int[] nums2 = {10,12,19,14};

        System.out.println(maximumSum(nums1));
        System.out.println(maximumSum(nums2));
    }

    private static int maximumSum(int[] nums) {
        int max = 0;
        Map<Integer, int[]> map = new HashMap<>();
        for (int num : nums){
            int sum = getSum(num);
            if (map.containsKey(sum)){
                int[] tmp = map.get(sum);
                int a = tmp[0], b = tmp[1];
                if (num > a){
                    tmp[0] = num;
                } else if (num > b){
                    tmp[1] = num;
                }
                max = Math.max(max, tmp[0]+tmp[1]);
                map.put(sum, tmp);
            }else{
                map.put(sum, new int[]{num, Integer.MIN_VALUE});
            }
        }
        return max;
    }

    private static int getSum(int num) {
        int sum = 0;
        while(num > 0){
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
