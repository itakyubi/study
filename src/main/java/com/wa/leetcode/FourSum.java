package com.wa.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * FourSum
 * https://leetcode.cn/problems/4sum/
 * 18. 四数之和
 * 2023/7/2 3:00 PM
 *
 * @author wuao
 */
public class FourSum {

    /*
    给你一个由 n 个整数组成的数组nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组[nums[a], nums[b], nums[c], nums[d]]（若两个四元组元素一一对应，则认为两个四元组重复）：
    0 <= a, b, c, d< n
    a、b、c 和 d 互不相同
    nums[a] + nums[b] + nums[c] + nums[d] == target
    你可以按 任意顺序 返回答案 。

    示例 1：
        输入：nums = [1,0,-1,0,-2,2], target = 0
        输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
    示例 2：
        输入：nums = [2,2,2,2,2], target = 8
        输出：[[2,2,2,2]]

    提示：
        1 <= nums.length <= 200
        -109 <= nums[i] <= 109
        -109 <= target <= 109
    */

    public static void main(String[] args) {
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;

        int[] nums2 = {2, 2, 2, 2, 2};
        int target2 = 8;

        int[] nums3 = {1000000000, 1000000000, 1000000000, 1000000000};
        int target3 = -294967296;

        int[] nums4 = {1, 0, -1, 0, -2, 2};
        int target4 = 0;

        System.out.println(fourSum(nums, target));
        System.out.println(fourSum(nums2, target2));
        System.out.println(fourSum(nums3, target3));
        System.out.println(fourSum(nums4, target4));
    }

    private static List<List<Integer>> fourSum(int[] nums, int target) {
        // cast to long
        int n = nums.length;
        long longTarget = target;
        long[] longNums = new long[n];
        for (int i = 0; i < n; i++) {
            longNums[i] = (long) nums[i];
        }

        List<List<Integer>> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        Arrays.sort(longNums);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                String key = String.join(",", String.valueOf(longNums[i]), String.valueOf(longNums[j]));
                if (set.contains(key)) {
                    continue;
                }

                int l = j + 1, r = n - 1;
                while (l < r) {
                    long sum = longNums[i] + longNums[j] + longNums[l] + longNums[r];
                    if (sum < longTarget) {
                        l++;
                    } else if (sum > longTarget) {
                        r--;
                    } else {
                        res.add(Arrays.asList((int) longNums[i], (int) longNums[j], (int) longNums[l], (int) longNums[r]));
                        set.add(key);
                        while (l < r && longNums[l] == longNums[l + 1]) {
                            l++;
                        }
                        l++;
                        while (l < r && longNums[r] == longNums[r - 1]) {
                            r--;
                        }
                        r--;
                    }
                }

            }
        }
        return res;
    }

    private static List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> quadruplets = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4) {
            return quadruplets;
        }
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            if ((long) nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
                continue;
            }
            for (int j = i + 1; j < length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                if ((long) nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
                    continue;
                }
                int left = j + 1, right = length - 1;
                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        quadruplets.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return quadruplets;
    }

}
