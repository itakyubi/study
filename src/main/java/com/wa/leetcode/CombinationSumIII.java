package com.wa.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * CombinationSumIII
 * 2020-07-10 16:38
 *
 * @author wuao
 */
public class CombinationSumIII {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        helper(k, n, 1, new ArrayList<>(), res);
        return res;
    }

    private void helper(int k, int n, int start, List<Integer> temp, List<List<Integer>> res) {
        if (k == 0 && n == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i <= 9; i++) {
            if (n - i < 0)
                break;
            temp.add(i);
            helper(k - 1, n - i, i + 1, temp, res);
            temp.remove(temp.size() - 1);
        }
    }
}
