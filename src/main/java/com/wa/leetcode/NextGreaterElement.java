package com.wa.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * NextGreaterElement
 * https://leetcode-cn.com/problems/next-greater-element-i/
 *
 * @author: wuao
 * @time: 2022/2/20 16:28
 **/
public class NextGreaterElement {

    public static void main(String[] args) {
        int[] nums1 = new int[]{4, 1, 2};
        int[] nums2 = new int[]{1, 3, 4, 2};
        int[] nums11 = new int[]{2, 4};
        int[] nums22 = new int[]{1, 2, 3, 4};
        printArray(nextGreaterElement(nums1, nums2));
        printArray(nextGreaterElement(nums11, nums22));
    }

    private static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        Arrays.fill(res, -1);
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    for (int k = j + 1; k < nums2.length; k++) {
                        if (nums2[k] > nums1[i]) {
                            res[i] = nums2[k];
                            break;
                        }
                    }
                    break;
                }
            }
        }
        return res;
    }

    private static int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>(); // key为nums2的数组元素，value为该位置元素的下一个更大元素
        Stack<Integer> stack = new Stack<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() < nums2[i]) {
                stack.pop();
            }
            map.put(nums2[i], stack.isEmpty() ? -1 : stack.peek());
            stack.push(nums2[i]);
        }

        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }

        return res;
    }

    private static void printArray(int[] nums) {
        for (int num : nums) {
            System.out.print(num);
            System.out.print(",");
        }
        System.out.println();
    }

}
