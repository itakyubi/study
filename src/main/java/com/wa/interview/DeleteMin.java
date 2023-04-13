package com.wa.interview;

import java.util.ArrayList;
import java.util.List;

/**
 * DeleteMin
 * 2023/2/20 9:09 上午
 *
 * @author wuao
 */
public class DeleteMin {

    /*
    给定一个数组nums，删除其中最少的元素，使其成为非严格递增或递减的数组，并返回删除的最小元素
    特别的，如果数组已经有序，则返回最小元素

    示例1：
        输入：[2,1]
        输出：1
    示例2：
        输入：[4,4,2,3,1]
        输出：2
        解释：
            删除2或者3都能使原数组非严格递减，但是2<3，所以返回2
    */

    public static void main(String[] args) {
        int[] nums = new int[]{2, 1};
        int[] nums2 = new int[]{4, 4, 2, 3, 1};
        int[] nums3 = new int[]{4, 2, 3, 2, 1};
        int[] nums4 = new int[]{1, 2, 3, 2, 4};

        /*System.out.println(helper(nums));
        System.out.println(helper(nums2));
        System.out.println(helper(nums3));*/

        helper(nums4);
    }

    private static int helper(int[] nums) {
        // 找到最长非严格递增子序列们
        int[] pos = new int[nums.length]; // 记录每个元素前一个最长子序列的元素下标
        int[] dpIncr = new int[nums.length];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            dpIncr[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] <= nums[i]) {
                    pos[i] = j;
                    dpIncr[i] = dpIncr[j] + 1;
                    max = Math.max(max, dpIncr[i]);
                }
            }
        }

        int lastPos = -1;
        for (int i = 0; i < nums.length; i++) {
            if (dpIncr[i] == max) {
                lastPos = i;
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            res.add(nums[lastPos]);
            lastPos = pos[lastPos];
        }

        System.out.println(res);


        // 找到最长非严格递减子序列们
        /*int[] Decr*/

        // 取最长的递增或者递减子序列们

        // 计算删除的最小元素


        return 0;
    }


    public void origin(int[] nums) {
        int[] pos = new int[nums.length]; // 记录每个元素前一个最长子序列的元素下标
        int[] dpIncr = new int[nums.length];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            dpIncr[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] <= nums[i]) {
                    pos[i] = j;
                    dpIncr[i] = dpIncr[j] + 1;
                    max = Math.max(max, dpIncr[i]);
                }
            }
        }

        int lastPos = -1;
        for (int i = 0; i < nums.length; i++) {
            if (dpIncr[i] == max) {
                lastPos = i;
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            res.add(nums[lastPos]);
            lastPos = pos[lastPos];
        }

        System.out.println(res);
    }

}
