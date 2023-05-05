package com.wa.interview.saima;

import java.util.Scanner;

/**
 * Zhixianhuaxing2
 * 2023/5/3 5:37 下午
 *
 * @author wuao
 */
public class Zhixianhuaxing2 {

    private static int[][] records;
    private static String arrows;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String arrows = sc.nextLine();

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, helper(arrows, i));
        }
        System.out.println(max);
    }

    // 64%，超时
    private static int helper(String arrows, int start) {
        int i = start;
        int res = 0;
        boolean[] visited = new boolean[arrows.length()];
        boolean toLeft = arrows.charAt(start) == '<';
        while (i >= 0 && i < arrows.length()) {
            if (visited[i]) {
                if (toLeft) {
                    i--;
                } else {
                    i++;
                }
            } else {
                visited[i] = true;
                res++;
                if (toLeft) {
                    i--;
                } else {
                    i++;
                }
            }
        }

        return res;
    }
}
