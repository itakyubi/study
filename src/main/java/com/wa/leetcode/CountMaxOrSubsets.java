package com.wa.leetcode;

/**
 * CountMaxOrSubsets
 * https://leetcode-cn.com/problems/count-number-of-maximum-bitwise-or-subsets/
 * 2022-03-15 09:01
 *
 * @author wuao
 */
public class CountMaxOrSubsets {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 1};
        int[] nums2 = new int[]{2, 2, 2};
        int[] nums3 = new int[]{3, 2, 1, 5};

        System.out.println(countMaxOrSubsets4(nums));
        System.out.println(countMaxOrSubsets4(nums2));
        System.out.println(countMaxOrSubsets4(nums3));
    }

    private static int max, count;

    private static int countMaxOrSubsets4(int[] nums) {
        max = 0;
        count = 0;
        for (int num : nums) {
            max |= num;  // 全集的求或一定是最大的，接下来只要遍历所有子集找到跟这个值一样的就可以了
        }
        helper4(0, 0, nums);
        return count;
    }

    private static void helper4(int index, int or, int[] nums) {
        if (or == max) {
            // 还剩nums.length-index个值未处理，取或不取一共2^(nums.length-index)种，都不影响最终结果
            count += 1 << (nums.length - index);
            return;
        }
        if (index >= nums.length) {
            return;
        }
        helper4(index + 1, or, nums);
        helper4(index + 1, or | nums[index], nums);
    }


    private static int countMaxOrSubsets3(int[] nums) {
        max = 0;
        count = 0;
        helper3(0, 0, nums);
        return count;
    }

    private static void helper3(int index, int or, int[] nums) {
        if (index == nums.length) {
            if (or > max) {
                max = or;
                count = 1;
            } else if (or == max) {
                count++;
            }
            return;
        }

        helper3(index + 1, or, nums);
        helper3(index + 1, or | nums[index], nums);
    }

    private static int countMaxOrSubsets2(int[] nums) {
        int max = 0, count = 0;
        for (int num : nums) {
            max |= num;  // 全集的求或一定是最大的，接下来只要遍历所有子集找到跟这个值一样的就可以了
        }

        for (int i = 0; i < (1 << nums.length); i++) {  // 枚举子集
            int or = 0;
            for (int j = 0; j < nums.length; j++) {
                if (((i >> j) & 1) == 1) {  // 对子集中为1的索引位置进行求或运算
                    or = or | nums[j];
                }
            }
            if (or == max) {
                count++;
            }
        }
        return count;
    }

    private static int countMaxOrSubsets(int[] nums) {
        int max = 0, count = 0;
        for (int i = 0; i < (1 << nums.length); i++) {  // 枚举子集
            int or = 0;
            for (int j = 0; j < nums.length; j++) {
                if (((i >> j) & 1) == 1) {  // 对子集中为1的索引位置进行求或运算
                    or = or | nums[j];
                }
            }

            if (or > max) {
                max = or;
                count = 1;
            } else if (or == max) {
                count++;
            }
        }
        return count;
    }
}
