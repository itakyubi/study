package com.wa.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * NearestPalindromic
 * 2022-03-02 09:06
 *
 * @author wuao
 */
public class NearestPalindromic {

    public static void main(String[] args) {
        String n = "123";
        String n2 = "1";
        String n3 = "1234";
        String n4 = "999";
        String n5 = "1000";
        String n6 = "12932";
        String n7 = "99800";
        String n8 = "12120";
        String n9 = "2";
        String n10 = "121";
        String n11 = "101";
        String n12 = "100";
        String n13 = "88";
        String n14 = "99021";


        System.out.println(nearestPalindromic(n));
        System.out.println(nearestPalindromic(n2));
        System.out.println(nearestPalindromic(n3));
        System.out.println(nearestPalindromic(n4));
        System.out.println(nearestPalindromic(n5));
        System.out.println(nearestPalindromic(n6));
        System.out.println(nearestPalindromic(n7));
        System.out.println(nearestPalindromic(n8));
        System.out.println(nearestPalindromic(n9));
        System.out.println(nearestPalindromic(n10));
        System.out.println(nearestPalindromic(n11));
        System.out.println(nearestPalindromic(n12));
        System.out.println(nearestPalindromic(n13));
        System.out.println(nearestPalindromic(n14));
    }

    private static String nearestPalindromic(String n) {
        long num = Long.parseLong(n);

        List<Long> candidates = new ArrayList<>();
        candidates.add((long) Math.pow(10, n.length()) + 1); // 1000....0001
        candidates.add((long) Math.pow(10, n.length() - 1) - 1); // 9999....9999

        // 正常构造回文
        long prefix = Long.parseLong(n.substring(0, (n.length() + 1) / 2));
        for (long i = prefix - 1; i <= prefix + 1; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            StringBuilder suffix = new StringBuilder(String.valueOf(i)).reverse();
            sb.append(suffix.substring(n.length() & 1));
            candidates.add(Long.parseLong(sb.toString()));
        }

        // 从所有候选中选出最接近且最小的那个
        long res = -1;
        for (Long candidate : candidates) {
            if (candidate == num)
                continue;

            if (res == -1) {
                res = candidate;
                continue;
            }

            long candidateDiff = Math.abs(candidate - num);
            long resDiff = Math.abs(res - num);
            if (candidateDiff < resDiff || (candidateDiff == resDiff && candidate < res)) {
                res = candidate;
            }
        }

        return String.valueOf(res);
    }


    private static String nearestPalindromic2(String n) {
        StringBuilder sb = new StringBuilder();

        // 处理输入为10^n的情况
        if (n.charAt(0) == '1' && n.length() > 1) {
            StringBuilder tmp = new StringBuilder();
            StringBuilder tmp2 = new StringBuilder();
            for (int i = 0; i < n.length() - 2; i++) {
                tmp.append('0');
                tmp2.append('0');
            }
            tmp.append('0');
            tmp2.append('1');

            if (n.substring(1).equals(tmp.toString()) || n.substring(1).equals(tmp2.toString())) {
                StringBuilder res = new StringBuilder();
                for (int i = 0; i < n.length() - 1; i++) {
                    res.append('9');
                }
                return res.toString();
            }
        }

        boolean odd = (n.length() & 1) != 0;
        int midIndex = (n.length() - 1) / 2;
        int i = midIndex;
        if (odd) {
            sb.append(n.charAt(i));
            i--;
        }

        while (i >= 0) {
            sb.insert(0, n.charAt(i));
            sb.append(n.charAt(i));
            i--;
        }


        // 处理与原字符串相同的情况
        String res = sb.toString();
        if (res.equals(n)) {
            char rc = sb.charAt(midIndex) == '0' ? '1' : (char) (sb.charAt(midIndex) - 1);
            if (odd) {
                sb.replace(midIndex, midIndex + 1, String.valueOf(rc));
            } else {
                sb.replace(midIndex, midIndex + 2, rc + String.valueOf(rc));
            }
        }
        return sb.toString();
    }
}
