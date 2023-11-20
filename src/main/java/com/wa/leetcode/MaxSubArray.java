package com.wa.leetcode;

/**
 * MaxSubArray
 * 53.最大子数组和
 *
 * @Date: 2023/11/20 9:07
 * @Author: wuao
 */
public class MaxSubArray {

    /*
    给你一个整数数组nums，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
    子数组是数组中的连续部分。

    示例1
        输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
        输出：6
    示例2
        输入： nums = [1]
        输出：1

    进阶：如果你已经实现复杂度为O(n)的解法，尝试使用更为精妙的分治法求解。
    */

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums2 = {1};

        System.out.println(maxSubArray2(nums));
        System.out.println(maxSubArray2(nums2));
    }

    // 时间复杂度O(n),空间复杂度O(1)
    private static int maxSubArray(int[] nums) {
        // dp[i]代表以第i个元素结尾的最大和
        // 当dp[i-1]>=0时，dp[i]=dp[i-1]+num
        // 当dp[i-1]<0时，dp[i]=num
        // 简化一下为dp[i] = max(num, dp[i-1]+num)
        // 又因为dp[i]只与dp[i-1]有关，所以空间复杂度可以从O(n)进一步简化为O(1)

        int pre = 0, max = 0;
        for (int num : nums) {
            pre = Math.max(num, pre + num);
            max = Math.max(max, pre);
        }
        return max;
    }

    // 分治法
    // 时间复杂度O(nlogn),空间复杂度O(logn)（考虑栈空间）
    // 虽然整体的时间复杂度比方法一高，但是初始化一次后，每次求任意[l,r]的最大和的时间复杂度为O(logn)
    public static class Status {
        // lSum:区间[l,r]内包含l的最大和
        // rSum:区间[l,r]内包含r的最大和
        // mSum:区间[l,r]内的最大和
        // iSum:区间[l,r]内的元素和
        public int lSum, rSum, mSum, iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

    private static int maxSubArray2(int[] nums) {
        return getInfo(nums, 0, nums.length - 1).mSum;
    }

    private static Status getInfo(int[] nums, int l, int r) {
        if (l == r) {
            return new Status(nums[l], nums[l], nums[l], nums[l]);
        }
        int m = (l + r) / 2;
        Status lSub = getInfo(nums, l, m);
        Status rSub = getInfo(nums, m + 1, r);
        return merge(lSub, rSub);
    }

    private static Status merge(Status l, Status r) {
        int iSum = l.iSum + r.iSum;
        int lSum = Math.max(l.lSum, l.iSum + r.lSum);
        int rSum = Math.max(r.rSum, r.iSum + l.rSum);
        int mSum = Math.max(l.rSum + r.lSum, Math.max(l.mSum, r.mSum));
        return new Status(lSum, rSum, mSum, iSum);
    }
}
