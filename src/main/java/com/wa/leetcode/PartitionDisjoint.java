package com.wa.leetcode;

/**
 * PartitionDisjoint
 * https://leetcode.cn/problems/partition-array-into-disjoint-intervals/
 * 915. 分割数组
 * 2022/10/24 3:56 下午
 *
 * @author wuao
 */
public class PartitionDisjoint {

    /*
    给定一个数组nums，将其划分为两个连续子数组left和right，使得：
    left中的每个元素都小于或等于right中的每个元素。
    left 和right都是非空的。
    left 的长度要尽可能小。
    在完成这样的分组后返回left的长度。
    用例可以保证存在这样的划分方法。

    示例 1：
        输入：nums = [5,0,3,8,6]
        输出：3
        解释：left = [5,0,3]，right = [8,6]
    示例 2：
        输入：nums = [1,1,1,0,6,12]
        输出：4
        解释：left = [1,1,1,0]，right = [6,12]

    提示：
        2 <= nums.length <= 105
        0 <= nums[i] <= 106
        可以保证至少有一种方法能够按题目所描述的那样对 nums 进行划分。
    */

    public static void main(String[] args) {
        int[] nums = new int[]{5, 0, 3, 8, 6};
        int[] nums2 = new int[]{1, 1, 1, 0, 6, 12};
        System.out.println(partitionDisjoint(nums));
        System.out.println(partitionDisjoint(nums2));
    }

    private static int partitionDisjoint(int[] nums) {
        int n = nums.length;

        int[] maxs = new int[n];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
            maxs[i] = max;
        }

        int[] mins = new int[n];
        int min = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] < min) {
                min = nums[i];
            }
            mins[i] = min;
        }

        for (int i = 0; i < n; i++) {
            if (maxs[i] <= mins[i + 1]) {
                return i + 1;
            }
        }
        return n;
    }
}
