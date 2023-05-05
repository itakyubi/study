package com.wa.interview.saima;

import java.util.Scanner;

/**
 * Dazhengshujiequ
 * 2023/4/24 9:32 上午
 *
 * @author wuao
 */
public class Dazhengshujiequ {
    // 1000000007
    /*1000000008001
            4
            8
            0
            1
            10*/
    // 9
    //39
    //5
    //2

    public static void main(String[] args) {
        long mod = 1000000007L;
        Scanner scanner = new Scanner(System.in);
        String origin = scanner.nextLine();
        long n = Long.parseLong(scanner.nextLine());

        long[][] grid = new long[origin.length()][origin.length()];
        for (int l = 0; l < origin.length(); l++) {
            long ans = 0;
            for (int r = l; r < origin.length(); r++) {
                ans = ((ans * 10) % mod + origin.charAt(r) - '0') % mod;
                grid[l][r] = ans;
            }
        }

        for (int i = 0; i < n; i++) {
            long num = Integer.parseInt(scanner.nextLine());
            // origin[l,r] mod 1000000007 = num
            long res = 0;
            for (int l = 0; l < origin.length(); l++) {
                for (int r = l; r < origin.length(); r++) {
                    if (grid[l][r] == num) {
                        res++;
                    }
                }
            }
            System.out.println(res);
        }

    }
}
