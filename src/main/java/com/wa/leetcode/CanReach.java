package com.wa.leetcode;

import java.util.Arrays;

/**
 * CanReach
 * https://leetcode-cn.com/problems/jump-game-iii/
 * <p>
 * 2022-03-01 09:11
 *
 * @author wuao
 */
public class CanReach {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 2, 3, 0, 3, 1, 2};
        int start = 5;

        int[] arr2 = new int[]{4, 2, 3, 0, 3, 1, 2};
        int start2 = 0;

        int[] arr3 = new int[]{3, 0, 2, 1, 2};
        int start3 = 2;

        System.out.println(canReach(arr, start));
        System.out.println(canReach(arr2, start2));
        System.out.println(canReach(arr3, start3));
    }

    private static boolean[] visited;

    private static boolean canReach(int[] arr, int start) {
        visited = new boolean[arr.length];
        Arrays.fill(visited, false);
        return helper(arr, start);
    }

    private static boolean helper(int[] arr, int start) {
        if (start < 0 || start >= arr.length)
            return false;

        if (visited[start])
            return false;

        visited[start] = true;
        if (arr[start] == 0)
            return true;

        return helper(arr, start - arr[start]) || helper(arr, start + arr[start]);
    }
}
