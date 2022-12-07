package com.wa.leetcode;

/**
 * EqualSumArraysWithMinimumNumberOfOperations
 * https://leetcode.cn/problems/equal-sum-arrays-with-minimum-number-of-operations/
 * 1775. 通过最少操作次数使数组的和相等
 * 2022/12/7 3:49 下午
 *
 * @author wuao
 */
public class EqualSumArraysWithMinimumNumberOfOperations {

    /*
    给你两个长度可能不等的整数数组 nums1 和 nums2 。两个数组中的所有值都在 1 到 6 之间（包含 1 和 6）。
    每次操作中，你可以选择 任意 数组中的任意一个整数，将它变成 1 到 6 之间 任意 的值（包含 1 和 6）。
    请你返回使 nums1 中所有数的和与 nums2 中所有数的和相等的最少操作次数。如果无法使两个数组的和相等，请返回 -1 。

    示例 1：
        输入：nums1 = [1,2,3,4,5,6], nums2 = [1,1,2,2,2,2]
        输出：3
        解释：你可以通过 3 次操作使 nums1 中所有数的和与 nums2 中所有数的和相等。以下数组下标都从 0 开始。
                - 将 nums2[0] 变为 6 。 nums1 = [1,2,3,4,5,6], nums2 = [6,1,2,2,2,2] 。
                - 将 nums1[5] 变为 1 。 nums1 = [1,2,3,4,5,1], nums2 = [6,1,2,2,2,2] 。
                - 将 nums1[2] 变为 2 。 nums1 = [1,2,2,4,5,1], nums2 = [6,1,2,2,2,2] 。
    示例 2：
        输入：nums1 = [1,1,1,1,1,1,1], nums2 = [6]
        输出：-1
        解释：没有办法减少 nums1 的和或者增加 nums2 的和使二者相等。
    示例 3：
        输入：nums1 = [6,6], nums2 = [1]
        输出：3
        解释：你可以通过 3 次操作使 nums1 中所有数的和与 nums2 中所有数的和相等。以下数组下标都从 0 开始。
                - 将 nums1[0] 变为 2 。 nums1 = [2,6], nums2 = [1] 。
                - 将 nums1[1] 变为 2 。 nums1 = [2,2], nums2 = [1] 。
                - 将 nums2[0] 变为 4 。 nums1 = [2,2], nums2 = [4] 。

    提示：
        1 <= nums1.length, nums2.length <= 105
        1 <= nums1[i], nums2[i] <= 6
    */

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4, 5, 6}, nums2 = {1, 1, 2, 2, 2, 2};
        int[] nums11 = {1, 1, 1, 1, 1, 1, 1}, nums22 = {6};
        int[] nums111 = {6, 6}, nums222 = {1};
        int[] nums1111 = {5, 6, 4, 3, 1, 2}, nums2222 = {6, 3, 3, 1, 4, 5, 3, 4, 1, 3, 4};
        int[] nums11111 = {2, 2, 4, 3, 1, 1, 5, 2, 5, 2, 5, 6, 1, 1, 6, 4, 5, 2, 5, 3}, nums22222 = {3, 3, 4};

        System.out.println(minOperations(nums1, nums2));
        System.out.println(minOperations(nums11, nums22));
        System.out.println(minOperations(nums111, nums222));
        System.out.println(minOperations(nums1111, nums2222));
        System.out.println(minOperations(nums11111, nums22222));
    }

    private static int minOperations2(int[] nums1, int[] nums2) {
        // 统计各数组中的1到6的个数，以及总和
        int[] cnt1 = new int[7], cnt2 = new int[7];
        int sum1 = 0, sum2 = 0;
        for (int num : nums1) {
            sum1 += num;
            cnt1[num]++;
        }
        for (int num : nums2) {
            sum2 += num;
            cnt2[num]++;
        }


        if (sum1 > sum2) {
            int tmp = sum1;
            sum1 = sum2;
            sum2 = tmp;

            int[] tmpCnt = cnt1;
            cnt1 = cnt2;
            cnt2 = tmpCnt;
        }

        int res = 0;
        int diff = sum2 - sum1;
        for (int i = 5; i >= 1; i--) {
            while (diff >= i && cnt1[6 - i] > 0) {
                diff -= i;
                cnt1[6 - i]--;
                res++;
            }
            while (diff >= i && cnt2[i + 1] > 0) {
                diff -= i;
                cnt2[i + 1]--;
                res++;
            }
        }
        if (diff > 0) {
            for (int i = 6; i >= 1; i--) {
                if (cnt2[i] > 0 && i > diff) {
                    diff = 0;
                    res++;
                    break;
                }
            }
        }

        return diff == 0 ? res : -1;
    }

    private static int minOperations(int[] nums1, int[] nums2) {
        // 统计各数组中的1到6的个数，以及总和
        int[] cnt1 = new int[7], cnt2 = new int[7];
        int sum1 = 0, sum2 = 0;
        for (int num : nums1) {
            sum1 += num;
            cnt1[num]++;
        }
        for (int num : nums2) {
            sum2 += num;
            cnt2[num]++;
        }


        if (sum1 > sum2) {
            int tmp = sum1;
            sum1 = sum2;
            sum2 = tmp;

            int[] tmpCnt = cnt1;
            cnt1 = cnt2;
            cnt2 = tmpCnt;
        }

        int res = 0;
        int diff = sum2 - sum1;
        for (int i = 5; i >= 1; i--) {
            int cnt = Math.min(diff / i, cnt1[6 - i]);
            cnt1[6 - i] -= cnt;
            diff -= i * cnt;
            res += cnt;

            cnt = Math.min(diff / i, cnt2[i + 1]);
            cnt2[i + 1] -= cnt;
            diff -= i * cnt;
            res += cnt;
        }
        if (diff > 0) {
            for (int i = 6; i >= 1; i--) {
                if (cnt2[i] > 0 && i > diff) {
                    diff = 0;
                    res++;
                    break;
                }
            }
        }

        return diff == 0 ? res : -1;
    }
}
