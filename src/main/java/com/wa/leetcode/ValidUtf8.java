package com.wa.leetcode;

/**
 * ValidUtf8
 * https://leetcode-cn.com/problems/utf-8-validation/
 *
 * @author: wuao
 * @time: 2022/3/13 10:33
 **/
public class ValidUtf8 {

    public static void main(String[] args) {
        int[] data = new int[]{197, 130, 1};
        int[] data2 = new int[]{235, 140, 4};
        int[] data3 = new int[]{145};
        int[] data4 = new int[]{248, 130, 130, 130};

        System.out.println(validUtf8(data));
        System.out.println(validUtf8(data2));
        System.out.println(validUtf8(data3));
        System.out.println(validUtf8(data4));
    }

    private static boolean validUtf8(int[] data) {
        int i = 0;
        while (i < data.length) {
            if (((data[i] >> 7) & 1) == 0) {
                i++;
            } else {
                // 记录开头1的数量
                int count = 0;
                for (int j = 6; j >= 0; j--) {
                    if ((data[i] >> j & 1) != 0) {
                        count++;
                    } else {
                        break;
                    }
                }

                // 最少还有一个1，最多还有3个1
                if (count < 1 || count > 3) {
                    return false;
                }

                // 判断后count个是否以10开头
                i++;
                while (i < data.length && count > 0 && startWith10(data[i])) {
                    i++;
                    count--;
                }

                if (count != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean startWith10(int num) {
        return (((num >> 7) & 1) == 1) && ((num >> 6 & 1) == 0);
    }
}
