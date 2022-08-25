package com.wa.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * FindClosestElements
 * https://leetcode.cn/problems/find-k-closest-elements/
 * 658. 找到 K 个最接近的元素
 * 2022/8/25 3:52 下午
 *
 * @author wuao
 */
public class FindClosestElements {

    /*
    给定一个 排序好 的数组arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
    整数 a 比整数 b 更接近 x 需要满足：
            |a - x| < |b - x| 或者
            |a - x| == |b - x| 且 a < b

    示例 1：
        输入：arr = [1,2,3,4,5], k = 4, x = 3
        输出：[1,2,3,4]
    示例 2：
        输入：arr = [1,2,3,4,5], k = 4, x = -1
        输出：[1,2,3,4]

    提示：
        1 <= k <= arr.length
        1 <= arr.length<= 104
        arr按 升序 排列
        -104<= arr[i], x <= 104
    */

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        int k = 4, x = 3;

        int[] arr2 = new int[]{1, 2, 3, 4, 5};
        int k2 = 4, x2 = -1;

        System.out.println(findClosestElements(arr, k, x));
        System.out.println(findClosestElements(arr2, k2, x2));
    }

    private static List<Integer> findClosestElements(int[] arr, int k, int x) {
        // 找到x插入的下标
        int index = getInsertIndex(arr, x);
        int left = index - 1, right = index;
        while (k > 0) {
            if (left < 0) {
                right++;
            } else if (right >= arr.length) {
                left--;
            } else if (x - arr[left] <= arr[right] - x) {
                left--;
            } else {
                right++;
            }
            k--;
        }

        List<Integer> res = new ArrayList<>();
        for (int i = left + 1; i < right; i++) {
            res.add(arr[i]);
        }
        return res;
    }

    private static int getInsertIndex(int[] arr, int x) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] == x) {
                return mid;
            } else if (arr[mid] < x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

}
