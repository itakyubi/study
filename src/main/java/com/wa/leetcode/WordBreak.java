package com.wa.leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * com.wa.leetcode.WordBreak
 *
 * @desc:
 * @author: wuao <wuao@baidu.com>
 * @time: 2020-01-17 13:38
 */
public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
