package com.wa.leetcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * SecondGreaterElement
 * https://leetcode.cn/problems/next-greater-element-iv
 * 2454. 下一个更大元素 IV
 *
 * @Date: 2023/12/12 8:45
 * @Author: wuao
 */
public class SecondGreaterElement {

    /*
    给你一个下标从 0 开始的非负整数数组 nums 。对于 nums 中每一个整数，你必须找到对应元素的 第二大 整数。
    如果 nums[j] 满足以下条件，那么我们称它为 nums[i] 的 第二大 整数：
    j > i
    nums[j] > nums[i]
    恰好存在 一个 k 满足 i < k < j 且 nums[k] > nums[i] 。
    如果不存在 nums[j] ，那么第二大整数为 -1 。
    比方说，数组 [1, 2, 4, 3] 中，1 的第二大整数是 4 ，2 的第二大整数是 3 ，3 和 4 的第二大整数是 -1 。
    请你返回一个整数数组 answer ，其中 answer[i]是 nums[i] 的第二大整数。

    示例 1：
        输入：nums = [2,4,0,9,6]
        输出：[9,6,6,-1,-1]
        解释：
        下标为 0 处：2 的右边，4 是大于 2 的第一个整数，9 是第二个大于 2 的整数。
        下标为 1 处：4 的右边，9 是大于 4 的第一个整数，6 是第二个大于 4 的整数。
        下标为 2 处：0 的右边，9 是大于 0 的第一个整数，6 是第二个大于 0 的整数。
        下标为 3 处：右边不存在大于 9 的整数，所以第二大整数为 -1 。
        下标为 4 处：右边不存在大于 6 的整数，所以第二大整数为 -1 。
        所以我们返回 [9,6,6,-1,-1] 。
    示例 2：
        输入：nums = [3,3]
        输出：[-1,-1]
        解释：
        由于每个数右边都没有更大的数，所以我们返回 [-1,-1] 。

    提示：
        1 <= nums.length <= 105
        0 <= nums[i] <= 109
    */

    public static void main(String[] args) {
        int[] nums = {2, 4, 0, 9, 6};
        int[] nums2 = {3, 3};
        int[] nums3 = {11, 13, 15, 12, 0, 15, 12, 11, 9};

        System.out.println(Arrays.toString(secondGreaterElement(nums)));
        System.out.println(Arrays.toString(secondGreaterElement(nums2)));
        System.out.println(Arrays.toString(secondGreaterElement(nums3)));
    }

    public static int[] secondGreaterElement(int[] nums) {
        // 每个数有两条命，有两条命的放在list2里，有一条命的放在list1里
        // 遍历每个元素，设当前元素为num
        // 先从list1里砍（从前往后），比num小的就砍掉，对于砍掉的元素，num就是其第二大的值
        // 直到砍到比自己大的出现或者list1为空
        // 然后开始砍list2（从前往后），被砍掉的元素进入list1的尾部（注意要按照原顺序放到list1尾部）
        // 直到砍到比自己大的出现或者list2为空
        // 然后把num加入到list2的尾部

        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Deque<Integer> list1 = new LinkedList<>();
        Deque<Integer> list2 = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            while (!list1.isEmpty() && nums[list1.peekLast()] < num) {
                res[list1.pollLast()] = num;
            }

            // 利用栈实现按原顺序放到list1尾部
            Stack<Integer> stack = new Stack<>();
            while (!list2.isEmpty() && nums[list2.peekLast()] < num) {
                stack.push(list2.pollLast());
            }
            while (!stack.isEmpty()) {
                list1.offerLast(stack.pop());
            }

            list2.offerLast(i);
        }
        return res;

    }
}
