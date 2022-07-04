package com.wa.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * MinimumAbsDifference
 * https://leetcode.cn/problems/minimum-absolute-difference/
 * 1200. 最小绝对差
 *
 * @author: wuao
 * @time: 2022/7/4 17:39
 **/
public class MinimumAbsDifference {

    /*
    给你个整数数组 arr，其中每个元素都 不相同。
    请你找到所有具有最小绝对差的元素对，并且按升序的顺序返回。

    示例 1：
        输入：arr = [4,2,1,3]
        输出：[[1,2],[2,3],[3,4]]
    示例 2：
        输入：arr = [1,3,6,10,15]
        输出：[[1,3]]
    示例 3：
        输入：arr = [3,8,-10,23,19,-4,-14,27]
        输出：[[-14,-10],[19,23],[23,27]]
             
    提示：
        2 <= arr.length <= 10^5
        -10^6 <= arr[i] <= 10^6
    */

    public static void main(String[] args) {
        int[] arr = new int[]{4, 2, 1, 3};
        int[] arr2 = new int[]{1, 3, 6, 10, 15};
        int[] arr3 = new int[]{3, 8, -10, 23, 19, -4, -14, 27};

        System.out.println(minimumAbsDifference(arr));
        System.out.println(minimumAbsDifference(arr2));
        System.out.println(minimumAbsDifference(arr3));
    }

    private static List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        int min = Integer.MAX_VALUE;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < arr.length - 1; i++) {
            int diff = arr[i + 1] - arr[i];
            List<Integer> list = new ArrayList<>();
            list.add(arr[i]);
            list.add(arr[i + 1]);
            if (diff < min) {
                res.clear();
                res.add(list);
                min = diff;
            } else if (diff == min) {
                res.add(list);
            }
        }
        return res;
    }
}
