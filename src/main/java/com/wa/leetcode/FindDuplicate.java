package com.wa.leetcode;

/**
 * FindDuplicate
 * 2020-08-24 19:03
 *
 * @author wuao
 */
public class FindDuplicate {
    public int findDuplicate(int[] nums) {
        int len = nums.length;
        int left = 1, right = len - 1, res = -1, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            int count = 0;
            for (int num : nums) {
                if (num <= mid)
                    count++;
            }
            if (count <= mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
                res = mid;
            }
        }
        return res;
    }
}
