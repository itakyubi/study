package com.wa.leetcode;

import java.util.ArrayList;
import java.util.List;

public class MajorityElementII {

    public static void main(String[] args) {
        System.out.println(majorityElement(new int[]{3, 2, 3}));
        System.out.println(majorityElement(new int[]{1, 1, 1, 3, 3, 2, 2, 2}));
    }

    public static List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;

        int a = nums[0], b = nums[0], countA = 0, countB = 0;
        for (int num : nums) {
            if (num == a) {
                countA++;
                continue;
            }
            if (num == b) {
                countB++;
                continue;
            }

            if (countA == 0) {
                a = num;
                countA++;
                continue;
            }
            if (countB == 0) {
                b = num;
                countB++;
                continue;
            }
            countA--;
            countB--;
        }

        countA = 0;
        countB = 0;
        for (int num : nums) {
            if (num == a) {
                countA++;
            } else if (num == b) {
                countB++;
            }
        }
        if (countA > nums.length / 3)
            res.add(a);
        if (countB > nums.length / 3)
            res.add(b);
        return res;
    }

}
