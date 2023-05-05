package com.wa.interview.saima;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Manager
 * 2023/4/24 9:23 上午
 *
 * @author wuao
 */
public class Manager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = Integer.parseInt(scanner.nextLine());
        String[] schools = scanner.nextLine().split(" ");
        Integer[] nums = new Integer[k];
        for (int i = 0; i < k; i++) {
            nums[i] = Integer.parseInt(schools[i]);
        }
        Arrays.sort(nums, (a, b) -> (b - a));
        int res = 0, max = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            if (max <= 1 || nums[i]<=0) {
                break;
            }
            if (nums[i] < max) {
                res += nums[i];
                max = nums[i];
            } else {
                int tmp = Math.max(0, max - 1);
                res += tmp;
                max = tmp;
            }
        }
        System.out.println(res);
    }
}
