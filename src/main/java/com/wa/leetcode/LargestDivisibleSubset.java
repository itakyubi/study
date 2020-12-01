package com.wa.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LargestDivisibleSubset
 * 2020-12-01 19:05
 *
 * @author wuao
 */
public class LargestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;

        List<ArrayList> EDS = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            EDS.add(new ArrayList());
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            List<Integer> maxSubset = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && maxSubset.size() < EDS.get(j).size()) {
                    maxSubset = EDS.get(j);
                }
            }
            EDS.get(i).addAll(maxSubset);
            EDS.get(i).add(nums[i]);
        }

        for (int i = 0; i < nums.length; i++) {
            if (res.size() < EDS.get(i).size())
                res = EDS.get(i);
        }

        return res;
    }
}
