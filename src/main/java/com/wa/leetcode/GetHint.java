package com.wa.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * GetHint
 * https://leetcode.cn/problems/bulls-and-cows/
 * 299. 猜数字游戏
 * 2023/7/7 8:19 PM
 *
 * @author wuao
 */
public class GetHint {

    public String getHint(String secret, String guess) {
        int bullCnt = 0;
        int[] cnts = new int[10];
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < secret.length(); i++) {
            char c1 = secret.charAt(i);
            char c2 = guess.charAt(i);
            if (c1 == c2) {
                bullCnt++;
            } else {
                cnts[c1 - '0']++;
                characters.add(c2);
            }
        }

        int cowCnt = 0;
        for (Character c : characters) {
            if (cnts[c - '0'] > 0) {
                cnts[c - '0']--;
                cowCnt++;
            }
        }

        return bullCnt + "A" + cowCnt + "B";
    }
}
