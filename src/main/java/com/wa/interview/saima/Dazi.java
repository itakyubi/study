package com.wa.interview.saima;

import java.util.HashMap;
import java.util.Scanner;

/**
 * dazi
 *
 * @author: wuao
 * @time: 2023/4/23 22:16
 **/
public class Dazi {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String str = scanner.nextLine();
            map = new HashMap<>();
            int cnt = helper(str, false, 0);
            System.out.println(cnt);
        }

    }

    private static HashMap<String, Integer> map = new HashMap<>();

    private static int helper(String str, boolean capsLock, int idx) {
        if (idx >= str.length()) {
            return 0;
        }
        String key = idx + '-' + String.valueOf(capsLock);
        if (map.containsKey(key)) {
            return map.get(key);
        }

        int res = 0;
        char c = str.charAt(idx);
        if (!(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z')) {
            res = helper(str, capsLock, idx + 1) + 1;
        } else if ((c >= 'a' && c <= 'z' && !capsLock) || (c >= 'A' && c <= 'Z' && capsLock)) {
            res = helper(str, capsLock, idx + 1) + 1;
        } else {
            // 切大小写
            int res1 = helper(str, !capsLock, idx + 1) + 2;
            // shift
            int res2 = helper(str, capsLock, idx + 1) + 2;
            res = Math.min(res1, res2);
        }
        map.put(key, Math.min(res, map.getOrDefault(key, Integer.MAX_VALUE)));
        return res;
    }
}
