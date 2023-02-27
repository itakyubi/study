package com.wa.leetcode;

/**
 * MovesToMakeZigzag
 * https://leetcode.cn/problems/decrease-elements-to-make-array-zigzag/
 * 1144. 递减元素使数组呈锯齿状
 * 2023/2/27 9:05 上午
 *
 * @author wuao
 */
public class MovesToMakeZigzag {

    /*
    给你一个整数数组nums，每次 操作会从中选择一个元素并 将该元素的值减少1。
    如果符合下列情况之一，则数组A就是 锯齿数组：
    每个偶数索引对应的元素都大于相邻的元素，即A[0] > A[1] < A[2] > A[3] < A[4] > ...
    或者，每个奇数索引对应的元素都大于相邻的元素，即A[0] < A[1] > A[2] < A[3] > A[4] < ...
    返回将数组nums转换为锯齿数组所需的最小操作次数。

    示例 1：
        输入：nums = [1,2,3]
        输出：2
        解释：我们可以把 2 递减到 0，或把 3 递减到 1。
    示例 2：
        输入：nums = [9,6,1,6,2]
        输出：4

    提示：
        1 <= nums.length <= 1000
        1 <= nums[i] <= 1000
    */


    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        int[] nums2 = new int[]{9, 6, 1, 6, 2};

        System.out.println(movesToMakeZigzag(nums));
        System.out.println(movesToMakeZigzag(nums2));
    }

    // 为使操作数最小
    // 当偶数索引位置大于奇数索引位置时，只调整奇数位置的索引
    // 当奇数索引位置大于偶数索引位置时，只调整偶数位置的索引
    // 贪心，但是如何证明在这个方法的正确性？
    private static int movesToMakeZigzag(int[] nums) {
        return Math.min(help(nums, 0), help(nums, 1));
    }

    private static int help(int[] nums, int start) {
        int max = 0;
        for (int i = start; i < nums.length; i += 2) {
            // 找到该索引元素与前后位置元素的差值中的最大值
            // 即为当前元素需要操作的次数
            int tmp = 0;
            if (i - 1 >= 0) {
                tmp = Math.max(nums[i] - nums[i - 1] + 1, tmp);
            }
            if (i + 1 < nums.length) {
                tmp = Math.max(nums[i] - nums[i + 1] + 1, tmp);
            }
            max += tmp;
        }
        return max;
    }
}
