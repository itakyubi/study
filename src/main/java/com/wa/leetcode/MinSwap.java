package com.wa.leetcode;

/**
 * MinSwap
 * https://leetcode.cn/problems/minimum-swaps-to-make-sequences-increasing/
 * 801. 使序列递增的最小交换次数
 * 2022/10/10 6:52 下午
 *
 * @author wuao
 */
public class MinSwap {

    /*
    我们有两个长度相等且不为空的整型数组nums1和nums2。在一次操作中，我们可以交换nums1[i]和nums2[i]的元素。
    例如，如果 nums1 = [1,2,3,8] ， nums2 =[5,6,7,4] ，你可以交换 i = 3 处的元素，得到 nums1 =[1,2,3,4] 和 nums2 =[5,6,7,8] 。
    返回 使 nums1 和 nums2 严格递增所需操作的最小次数 。
    数组arr严格递增 且arr[0] < arr[1] < arr[2] < ... < arr[arr.length - 1]。

    注意：
    用例保证可以实现操作。

    示例 1:
        输入: nums1 = [1,3,5,4], nums2 = [1,2,3,7]
        输出: 1
        解释:
            交换 A[3] 和 B[3] 后，两个数组如下:
            A = [1, 3, 5, 7] ， B = [1, 2, 3, 4]
            两个数组均为严格递增的。
    示例 2:
        输入: nums1 = [0,3,5,8,9], nums2 = [2,1,4,6,9]
        输出: 1

    提示:
        2 <= nums1.length <= 105
        nums2.length == nums1.length
        0 <= nums1[i], nums2[i] <= 2 * 105
    */

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 3, 5, 4}, nums2 = new int[]{1, 2, 3, 7};
        int[] nums11 = new int[]{0, 3, 5, 8, 9}, nums22 = new int[]{2, 1, 4, 6, 9};

        System.out.println(minSwap(nums1, nums2));
        System.out.println(minSwap(nums11, nums22));
    }

    private static int minSwap(int[] nums1, int[] nums2) {
        int n = nums1.length;
        // dp[i][0]表示以下标i为结尾且i位置不发生交换的最小操作次数
        // dp[i][1]表示以下标i为结尾且i位置发生交换的最小操作次数
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            dp[i][0] = n;
            dp[i][1] = n;
        }
        dp[0][0] = 0;
        dp[0][1] = 1;

        for (int i = 1; i < n; i++) {
            if (nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1]) {
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = dp[i - 1][1] + 1;
            }
            if (nums1[i] > nums2[i - 1] && nums2[i] > nums1[i - 1]) {
                dp[i][0] = Math.min(dp[i][0], dp[i - 1][1]);
                dp[i][1] = Math.min(dp[i][1], dp[i - 1][0] + 1);
            }
        }

        return Math.min(dp[n - 1][0], dp[n - 1][1]);
    }

    private static int minSwap2(int[] nums1, int[] nums2) {
        int n = nums1.length;
        // a表示以下标i为结尾且i位置不发生交换的最小操作次数
        // b表示以下标i为结尾且i位置发生交换的最小操作次数
        int a = 0, b = 1;

        for (int i = 1; i < n; i++) {
            int tmpA = a, tmpB = b;
            a = n;
            b = n;
            if (nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1]) {
                a = tmpA;
                b = tmpB + 1;
            }
            if (nums1[i] > nums2[i - 1] && nums2[i] > nums1[i - 1]) {
                a = Math.min(a, tmpB);
                b = Math.min(b, tmpA + 1);
            }
        }

        return Math.min(a, b);
    }
}
