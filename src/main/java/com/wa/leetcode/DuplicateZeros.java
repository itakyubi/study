package com.wa.leetcode;

import java.util.Arrays;

/**
 * DuplicateZeros
 * https://leetcode.cn/problems/duplicate-zeros/
 * 1089. 复写零
 * 2022-06-17 08:59
 *
 * @author wuao
 */
public class DuplicateZeros {
    /*
    给你一个长度固定的整数数组 arr，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。
    注意：请不要在超过该数组长度的位置写入元素。
    要求：请对输入的数组 就地 进行上述修改，不要从函数返回任何东西。

    示例 1：
        输入：[1,0,2,3,0,4,5,0]
        输出：null
        解释：调用函数后，输入的数组将被修改为：[1,0,0,2,3,0,0,4]

    示例 2：
        输入：[1,2,3]
        输出：null
        解释：调用函数后，输入的数组将被修改为：[1,2,3]
             
    提示：
        1 <= arr.length <= 10000
        0 <= arr[i] <= 9
    */

    public static void main(String[] args) {
        int[] nums = new int[]{1, 0, 2, 3, 0, 4, 5, 0};
        int[] nums2 = new int[]{1, 2, 3};

        duplicateZeros(nums);
        duplicateZeros(nums2);

        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(nums2));
    }

    private static void duplicateZeros(int[] arr) {
        int[] res = new int[arr.length];

        int i = 0, j = 0;
        while (i < res.length) {
            res[i] = arr[j];
            i++;
            if (arr[j] == 0) {
                if (i < res.length) {
                    res[i] = 0;
                    i++;
                }
            }
            j++;
        }

        System.arraycopy(res, 0, arr, 0, arr.length);
    }
}
