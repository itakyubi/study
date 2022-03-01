package com.wa.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PyramidTransition
 * https://leetcode-cn.com/problems/pyramid-transition-matrix/
 * <p>
 * 2022-02-22 09:14
 *
 * @author wuao
 */
public class PyramidTransition {

    public static void main(String[] args) {
        String bottom = "BCD";
        List<String> allowed = Arrays.asList("BCG", "CDE", "GEA", "FFF");
        String bottom2 = "AABA";
        List<String> allowed2 = Arrays.asList("AAA", "AAB", "ABA", "ABB", "BAC");
        String bottom3 = "DBCDA";
        List<String> allowed3 = Arrays.asList("ACC", "ACB", "ACA", "AAC", "ACD", "BCD", "BCC", "BAB", "CCD",
                "CCA", "CCB", "DAD", "DAC", "AAB", "CAD", "ABB", "ABC", "ABD", "BDC", "BDB", "BBD", "BBC",
                "BBB", "ADD", "ADB", "DDA", "CDD", "CBC", "CDA", "CDB", "DBD", "BDA");


        System.out.println(pyramidTransition(bottom, allowed));
        System.out.println(pyramidTransition(bottom2, allowed2));
        System.out.println(pyramidTransition(bottom3, allowed3));
    }

    private static Map<String, Boolean> visited = new HashMap<>();
    private static Map<String, List<String>> allowedMap = new HashMap<>();

    private static boolean pyramidTransition(String bottom, List<String> allowed) {
        for (String allow : allowed) {
            String prefix = allow.substring(0, 2);
            List<String> endList = allowedMap.getOrDefault(prefix, new ArrayList<>());
            endList.add(allow.substring(2));
            allowedMap.put(prefix, endList);
        }

        return helper(bottom);
    }

    private static boolean helper(String bottom) {
        if (bottom.length() == 1) {
            return true;
        }

        if (visited.containsKey(bottom)) {
            return visited.get(bottom);
        }

        List<String> nextBottoms = new ArrayList<>();

        for (int i = 0; i < bottom.length() - 1; i++) {
            String next = bottom.substring(i, i + 2);
            if (!allowedMap.containsKey(next)) {
                return false;
            } else {
                if (nextBottoms.size() == 0) {
                    List<String> endList = allowedMap.get(next);
                    nextBottoms.addAll(endList);
                } else {
                    List<String> tmp = new ArrayList<>();
                    List<String> endList = allowedMap.get(next);
                    for (String nextBottom : nextBottoms) {
                        for (String end : endList) {
                            tmp.add(nextBottom + end);
                        }
                    }
                    nextBottoms = tmp;
                }
            }
        }

        for (String nextBottom : nextBottoms) {
            boolean res = helper(nextBottom);
            visited.put(nextBottom, res);
            if (res) {
                return true;
            }
        }
        return false;
    }
}
