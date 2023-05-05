package com.wa.interview.saima;

import java.util.Scanner;

/**
 * Fenjinbi
 * 2023/4/28 9:23 上午
 *
 * @author wuao
 */
public class Fenjinbi {

    // https://blog.csdn.net/qq_40934617/article/details/122414875
    // https://cloud.tencent.com/developer/article/2083435
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] strs = sc.nextLine().split(" ");
        int n = Integer.parseInt(strs[0]);
        int m = Integer.parseInt(strs[1]);
        int res;
        switch (n + 1) {
            case 1:
                res = m;
                break;
            case 2:
                res = -1;
                break;
            case 3:
                res = m;
                break;
            case 4:
                res = m - 2;
                break;
            default:
                // 假设从一号开始提出分配方案
                // 5个以上时，给2号分0个，给3号分1个，剩下的人中选一半的人（向上取整）给2个
                res = m - 1 - ((n + 1 - 3) / 2 + 1);
                if (res < 0) {
                    res = -1;
                }
        }
        System.out.print(res);
    }
}
