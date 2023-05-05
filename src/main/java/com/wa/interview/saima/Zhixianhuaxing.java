package com.wa.interview.saima;

import java.util.Scanner;

/**
 * Zhixianhuaxing
 * 2023/4/28 4:34 下午
 *
 * @author wuao
 */
public class Zhixianhuaxing {

    private static int[][] records;
    private static String arrow;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String arrows = sc.nextLine();

        int max = 0;
        records = new int[n][2]; // 0-l,1-r
        arrow = arrows;
        for (int i = 0; i < n; i++) {
            records[i][0] = -1;
            records[i][1] = -1;
        }
        // >><><<<
        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            int tmp = helper(i, arrows.charAt(i) == '<', visited);
            System.out.println(tmp);
            max = Math.max(max, tmp);
        }
        System.out.println(max);
    }

    private static int helper(int start, boolean toLeft, boolean[] visited) {
        if (start < 0 || start >= arrow.length()) {
            return 0;
        }

        if (visited[start]) {
            if (toLeft) {
                return helper(start - 1, toLeft, visited);
            } else {
                return helper(start + 1, toLeft, visited);
            }
        }

        visited[start] = true;
        if (arrow.charAt(start) == '>') {
            if (records[start][1] != -1) {
                return records[start][1];
            }
        } else {
            if (records[start][0] != -1) {
                return records[start][0];
            }
        }

        int res = 0;

        if (arrow.charAt(start) == '>') {
            res = helper(start + 1, false, visited) + 1;
            records[start][1] = res;
        } else {
            res = helper(start - 1, true, visited) + 1;
            records[start][0] = res;
        }
        return res;
    }
}
