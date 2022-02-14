package com.wa.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * FindKthLargest2
 * 2021-12-16 16:10
 *
 * @author wuao
 */
public class FindKthLargest2 {
    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 1, 2, 3, 8};
        System.out.println(findKthLargest(nums, 3));
    }

    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, Comparator.comparingInt(a -> a));
        for (int i = 0; i < k; i++) {
            minHeap.add(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }
        return minHeap.peek();
    }
}
