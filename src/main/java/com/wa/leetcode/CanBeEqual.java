package com.wa.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * CanBeEqual
 * https://leetcode.cn/problems/make-two-arrays-equal-by-reversing-sub-arrays/
 * 1460. 通过翻转子数组使两个数组相等
 * 2022/8/24 4:11 下午
 *
 * @author wuao
 */
public class CanBeEqual {

    /*
    给你两个长度相同的整数数组target和arr。每一步中，你可以选择arr的任意 非空子数组并将它翻转。你可以执行此过程任意次。
    如果你能让 arr变得与 target相同，返回 True；否则，返回 False 。

    示例 1：
        输入：target = [1,2,3,4], arr = [2,4,1,3]
        输出：true
        解释：你可以按照如下步骤使 arr 变成 target：
                1- 翻转子数组 [2,4,1] ，arr 变成 [1,4,2,3]
                2- 翻转子数组 [4,2] ，arr 变成 [1,2,4,3]
                3- 翻转子数组 [4,3] ，arr 变成 [1,2,3,4]
        上述方法并不是唯一的，还存在多种将 arr 变成 target 的方法。
    示例 2：
        输入：target = [7], arr = [7]
        输出：true
        解释：arr 不需要做任何翻转已经与 target 相等。
    示例 3：
        输入：target = [3,7,9], arr = [3,7,11]
        输出：false
        解释：arr 没有数字 9 ，所以无论如何也无法变成 target 。

    提示：
        target.length == arr.length
        1 <= target.length <= 1000
        1 <= target[i] <= 1000
        1 <= arr[i] <= 1000
    */

    public static void main(String[] args) {
        int[] target = new int[]{1, 2, 3, 4}, arr = new int[]{2, 4, 1, 3};
        int[] target2 = new int[]{7}, arr2 = new int[]{7};
        int[] target3 = new int[]{3, 7, 9}, arr3 = new int[]{3, 7, 11};

        System.out.println(canBeEqual(target, arr));
        System.out.println(canBeEqual(target2, arr2));
        System.out.println(canBeEqual(target3, arr3));
    }

    private static boolean canBeEqual(int[] target, int[] arr) {
        if (target.length != arr.length)
            return false;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < target.length; i++) {
            map.put(target[i], map.getOrDefault(target[i], 0) + 1);
            map.put(arr[i], map.getOrDefault(arr[i], 0) - 1);
        }

        for (int v : map.values()) {
            if (v != 0) {
                return false;
            }
        }
        return true;
    }
}
