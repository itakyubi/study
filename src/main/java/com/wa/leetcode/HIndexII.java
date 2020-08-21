package com.wa.leetcode;

/**
 * HIndexII
 * 2020-08-21 18:09
 *
 * @author wuao
 */
public class HIndexII {

    public static void main(String[] args) {
        System.out.println(hIndex(new int[]{0, 1, 3, 5, 6}));
        System.out.println(hIndex(new int[]{0, 1, 4, 5, 6}));
    }

    public static int hIndex(int[] citations) {
        int len = citations.length;
        int left = 0, right = len - 1, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (citations[mid] == len - mid) {
                return len - mid;
            } else if (citations[mid] < len - mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return len - left;
    }
}
