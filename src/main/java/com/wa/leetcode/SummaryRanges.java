package com.wa.leetcode;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {

    public static void main(String[] args) {
        System.out.println(summaryRanges(new int[]{0, 1, 2, 4, 5, 7}));
        System.out.println(summaryRanges(new int[]{0, 2, 3, 4, 6, 8, 9}));
    }

    public static List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;

        int start = nums[0];
        for (int i = 1; i < nums.length; i++) {
            while (i < nums.length && nums[i] - nums[i - 1] == 1)
                i++;
            res.add("" + start + (start == nums[i - 1] ? "" : "->" + nums[i - 1]));
            if (i < nums.length)
                start = nums[i];
        }
        if (start == nums[nums.length - 1])
            res.add("" + start);
        return res;
    }
}
