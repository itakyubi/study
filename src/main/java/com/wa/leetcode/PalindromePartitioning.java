package com.wa.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * com.wa.leetcode.PalindromePartitioning
 *
 * @desc:
 * @author: wuao <wuao@baidu.com>
 * @time: 2020-01-14 17:16
 */
public class PalindromePartitioning {

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> tmp = new ArrayList<>();
        partition(s, res, tmp, 0);
        return res;
    }

    private void partition(String s, List<List<String>> res, List<String> tmp, int index) {
        if (s.length() == index) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            String str = s.substring(index, i + 1);
            if (isPalindrome(str)) {
                tmp.add(str);
                partition(s, res, tmp, i + 1);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
}
