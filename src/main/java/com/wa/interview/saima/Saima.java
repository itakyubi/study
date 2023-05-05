package com.wa.interview.saima;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Saima
 * 2023/4/28 9:59 上午
 *
 * @author wuao
 */
public class Saima {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(sc.nextLine());
            String[] strs = sc.nextLine().split(" ");
            int[] nums = new int[n * 2];
            for (int j = 0; j < n * 2; j++) {
                nums[j] = Integer.parseInt(strs[j]);
            }
            Arrays.sort(nums);
            if (nums[n - 1] < nums[n]) {
                System.out.print("YES");
            } else {
                System.out.print("NO");
            }
        }
    }
}
