package com.wa.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * ContainsNearbyAlmostDuplicate
 * 2020-07-13 18:55
 *
 * @author wuao
 */
public class ContainsNearbyAlmostDuplicate {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j <= i + k && j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) <= t)
                    return true;
            }
        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        TreeSet<Long> treeSet = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Long s = treeSet.ceiling((long) nums[i]);
            if (s != null && s - nums[i] <= t)
                return true;

            Long g = treeSet.floor((long) nums[i]);
            if (g != null && nums[i] - g <= t)
                return true;

            treeSet.add((long) nums[i]);
            if (treeSet.size() > k) {
                treeSet.remove((long) nums[i - k]);
            }
        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicate3(int[] nums, int k, int t) {
        if (t < 0)
            return false;
        Map<Long, Long> d = new HashMap<>();
        long w = (long) t + 1;
        for (int i = 0; i < nums.length; i++) {
            long m = getID(nums[i], w);
            if (d.containsKey(m))
                return true;
            if (d.containsKey(m - 1) && Math.abs(nums[i] - d.get(m - 1)) < w)
                return true;
            if (d.containsKey(m + 1) && Math.abs(nums[i] - d.get(m + 1)) < w)
                return true;
            d.put(m, (long) nums[i]);
            if (i >= k)
                d.remove(getID(nums[i - k], w));
        }
        return false;
    }

    private long getID(long x, long w) {
        return x < 0 ? (x + 1) / w - 1 : x / w;
    }
}
