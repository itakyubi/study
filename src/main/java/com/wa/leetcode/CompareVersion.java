package com.wa.leetcode;

/**
 * com.wa.leetcode.CompareVersion
 * 2020-06-12 14:57
 *
 * @author wuao
 */
public class CompareVersion {

    public static void main(String[] args) {
        compareVersion("0.1","1.1");
    }

    public static int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int i = 0;

        while (i < v1.length || i < v2.length) {
            int i1 = i < v1.length ? Integer.parseInt(v1[i]) : 0;
            int j1 = i < v2.length ? Integer.parseInt(v2[i]) : 0;
            if (i1 < j1) {
                return -1;
            } else if (i1 > j1) {
                return 1;
            }
            i++;
        }
        return 0;
    }
}
