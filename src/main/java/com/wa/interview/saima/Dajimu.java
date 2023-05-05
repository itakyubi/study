package com.wa.interview.saima;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Dajimu
 * 2023/4/25 5:04 下午
 *
 * @author wuao
 */
public class Dajimu {


    // 64%，没考虑多个独立连通的情况
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(sc.nextLine());
            Map<String, Integer> map = new HashMap<>();
            for (int j = 0; j < n; j++) {
                String[] strs = sc.nextLine().split(" ");
                if (strs[0].equals(strs[1])) {
                    int cnt = map.getOrDefault(strs[0], 0);
                    if (cnt > 2) {
                        map.put(strs[0], cnt - 2);
                    } else if (cnt == 2) {
                        map.remove(strs[0]);
                    } else if (cnt != 1) {
                        map.put(strs[0], 2);
                    }
                } else {
                    for (int k = 0; k < 2; k++) {
                        int cnt = map.getOrDefault(strs[k], 0);
                        if (cnt > 1) {
                            map.put(strs[0], cnt - 1);
                        } else if (cnt == 1) {
                            map.remove(strs[k]);
                        } else {
                            map.put(strs[k], 1);
                        }
                    }
                }
            }
            if (map.size() == 0) {
                System.out.print("YES");
            } else if (map.size() == 2) {
                int cnt = 0;
                for (Integer c : map.values()) {
                    cnt += c;
                }
                if (cnt == 2) {
                    System.out.print("YES");
                } else {
                    System.out.print("NO");
                }
            } else {
                System.out.print("NO");
            }

        }
    }

    // 91%
    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(sc.nextLine());
            Map<String, Integer> map = new HashMap<>();
            for (int j = 0; j < n; j++) {
                String[] strs = sc.nextLine().split(" ");
                map.put(strs[0], map.getOrDefault(strs[0], 0) + 1);
                map.put(strs[1], map.getOrDefault(strs[1], 0) + 1);

            }
            int oddCnt = 0;
            for (int value : map.values()) {
                if (value % 2 != 0) {
                    oddCnt++;
                }
            }
            if (oddCnt > 2) {
                System.out.print("NO");
            } else {
                System.out.print("YES");
            }


        }
    }
}
