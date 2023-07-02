package com.wa.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ThreeSum
 * https://leetcode.cn/problems/3sum/
 * 15. 三数之和
 * 2023/7/1 5:42 PM
 *
 * @author wuao
 */
public class ThreeSum {

    /*
    给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
    你返回所有和为 0 且不重复的三元组。
    注意：答案中不可以包含重复的三元组。

    示例 1：
        输入：nums = [-1,0,1,2,-1,-4]
        输出：[[-1,-1,2],[-1,0,1]]
        解释：
        nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
        nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
        nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
        不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
        注意，输出的顺序和三元组的顺序并不重要。
    示例 2：
        输入：nums = [0,1,1]
        输出：[]
        解释：唯一可能的三元组和不为 0 。
    示例 3：
        输入：nums = [0,0,0]
        输出：[[0,0,0]]
        解释：唯一可能的三元组和为 0 。

    提示：
        3 <= nums.length <= 3000
        -105 <= nums[i] <= 105
    */

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        int[] nums2 = {0, 1, 1};
        int[] nums3 = {0, 0, 0};

        System.out.println(threeSum(nums));
        System.out.println(threeSum(nums2));
        System.out.println(threeSum(nums3));
    }


    private static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;

        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) { // nums[i]都大于0了，后边求和肯定大于0
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) { // 去重
                continue;
            }

            int l = i + 1, r = n - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum > 0) {
                    r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    while (l < r && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    l++;
                    r--;
                }
            }

        }

        return res;
    }


}
