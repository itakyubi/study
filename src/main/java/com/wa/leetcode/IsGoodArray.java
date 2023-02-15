package com.wa.leetcode;

/**
 * IsGoodArray
 * https://leetcode.cn/problems/check-if-it-is-a-good-array/
 * 1250. 检查「好数组」
 * 2023/2/15 8:53 上午
 *
 * @author wuao
 */
public class IsGoodArray {

    /*
    给你一个正整数数组 nums，你需要从中任选一些子集，然后将子集中每一个数乘以一个 任意整数，并求出他们的和。
    假如该和结果为1，那么原数组就是一个「好数组」，则返回 True；否则请返回 False。

    示例 1：
        输入：nums = [12,5,7,23]
        输出：true
        解释：挑选数字 5 和 7。
                5*3 + 7*(-2) = 1
    示例 2：
        输入：nums = [29,6,10]
        输出：true
        解释：挑选数字 29, 6 和 10。
                29*1 + 6*(-3) + 10*(-1) = 1
    示例 3：
        输入：nums = [3,6]
        输出：false

    提示：
        1 <= nums.length <= 10^5
        1 <= nums[i] <= 10^9
    */

    public static void main(String[] args) {
        int[] nums = new int[]{12, 5, 7, 23};
        int[] nums2 = new int[]{29, 6, 10};
        int[] nums3 = new int[]{3, 6};

        System.out.println(isGoodArray(nums));
        System.out.println(isGoodArray(nums2));
        System.out.println(isGoodArray(nums3));
    }

    // 裴蜀定理
    // 对于整数a、b，其最大公约数为gcd(a,b)
    // 则对于任意整数x、y，都满足 a*x+b*y是gcd(a,b)的倍数
    // 特别的存在整数x、y，满足 a*x+b*y=gcd(a,b)
    // 裴蜀定理也可以扩展到多个整数的情况
    // a*x+b*y+c*z.... = gcd(a,b,c....)
    // 对于本题来说，即判断数组的最大公约数是否为1即可
    private static boolean isGoodArray(int[] nums) {
        int gcd = nums[0];
        for (int num : nums) {
            gcd = gcd(gcd, num);
            if (gcd == 1) {
                return true;
            }
        }
        return false;
    }

    // 令x=ky+b，其中x、y、k、b均为整数
    // 则k代表x/y的商，b代表x/y的余数
    // 如果存在a能整除x和y，则 ky+b 也必然能被a整除
    // 则b必然能被a整除
    // 所以求x和y的最大公约数就能变为求y和b的最大公约数
    // 直到b为0时终止，此时的y就是最大公约数
    private static int gcd(int a, int b) {
        // b代表上一层a/b的余数，余数为0则找到最大公约数
        return b == 0 ? a : gcd(b, a % b);
    }

}
