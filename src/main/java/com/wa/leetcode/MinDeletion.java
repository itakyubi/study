package com.wa.leetcode;

/**
 * MinDeletion
 * https://leetcode.cn/problems/minimum-deletions-to-make-array-beautiful
 * 2216. 美化数组的最少删除数
 *
 * @Date: 2023/11/21 9:05
 * @Author: wuao
 */
public class MinDeletion {

    /*
    给你一个下标从 0 开始的整数数组 nums ，如果满足下述条件，则认为数组 nums 是一个 美丽数组 ：
    nums.length 为偶数
    对所有满足 i % 2 == 0 的下标 i ，nums[i] != nums[i + 1] 均成立
    注意，空数组同样认为是美丽数组。
    你可以从 nums 中删除任意数量的元素。当你删除一个元素时，被删除元素右侧的所有元素将会向左移动一个单位以填补空缺，而左侧的元素将会保持 不变 。
    返回使 nums 变为美丽数组所需删除的 最少 元素数目。

    示例 1：
        输入：nums = [1,1,2,3,5]
        输出：1
        解释：可以删除 nums[0] 或 nums[1] ，这样得到的 nums = [1,2,3,5] 是一个美丽数组。可以证明，要想使 nums 变为美丽数组，至少需要删除 1 个元素。
    示例 2：
        输入：nums = [1,1,2,2,3,3]
        输出：2
        解释：可以删除 nums[0] 和 nums[5] ，这样得到的 nums = [1,2,2,3] 是一个美丽数组。可以证明，要想使 nums 变为美丽数组，至少需要删除 2 个元素。

    提示：
        1 <= nums.length <= 10^5
        0 <= nums[i] <= 10^5
    */

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 3, 5};
        int[] nums2 = {1, 1, 2, 2, 3, 3};

        System.out.println(minDeletion(nums));
        System.out.println(minDeletion(nums2));
    }


    private static int minDeletion(int[] nums) {
        // 记录原始数组中最小的坏下标i0（i%2==0,且nums[i]==nums[i+1]）
        // 删除i<i0的元素时，有可能会使i0变得更小（例如1211）
        // 删除i>i0+1的元素时，i0仍然时坏下标，不满足条件
        // 删除i=i0或者i=i0+1的元素时，新的i0有可能变为好下标
        // 所以最优策略就是每次找到坏下标就删除该位置的元素，并记录删除个数
        int cnt = 0;
        boolean even = true;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1] && even) {
                cnt++;
            } else {
                even = !even;
            }
        }
        // 如果最终时奇数个元素，则直接删除最后一个元素即可
        if ((nums.length - cnt) % 2 != 0) {
            cnt++;
        }
        return cnt;
    }
}
