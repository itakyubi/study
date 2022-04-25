package com.wa.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * RandomPickIndex
 * 2022-04-25 09:15
 *
 * @author wuao
 */
public class RandomPickIndex {

    private Map<Integer, List<Integer>> map;
    private Random random;

    public RandomPickIndex(int[] nums) {
        map = new HashMap<>();
        random = new Random();
        for (int i = 0; i < nums.length; i++) {
            map.putIfAbsent(nums[i], new ArrayList<>());
            map.get(nums[i]).add(i);
        }
    }

    public int pick(int target) {
        List<Integer> list = map.get(target);
        return list.get(random.nextInt(map.get(target).size()));
    }


    // method2
    private int[] nums;

    public void RandomPickIndex2(int[] nums) {
        this.nums = nums;
        this.random = new Random();
    }

    public int pick2(int target) {
        int ans = 0;
        for (int i = 0, cnt = 0; i < nums.length; ++i) {
            if (nums[i] == target) {
                ++cnt; // 第 cnt 次遇到 target
                if (random.nextInt(cnt) == 0) {
                    ans = i;
                }
            }
        }
        return ans;
    }
}
