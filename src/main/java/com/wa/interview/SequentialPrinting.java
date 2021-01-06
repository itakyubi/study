package com.wa.interview;

/**
 * SequentialPrinting
 * 多线程顺序打印
 * 2021-01-06 17:35
 *
 * @author wuao
 */
public class SequentialPrinting {

    public static void main(String[] args) {
        printWithFlag();
    }

    private static volatile boolean flag = true;

    private static void printWithFlag() {
        Thread t1 = new Thread(() -> {
            int i = 1;
            while (i <= 20) {
                if (flag) {
                    System.out.println(i);
                    i += 2;
                    flag = !flag;
                }
            }
        });

        Thread t2 = new Thread(() -> {
            int i = 2;
            while (i <= 20) {
                if (!flag) {
                    System.out.println(i);
                    i += 2;
                    flag = !flag;
                }
            }
        });

        t1.start();
        t2.start();
    }
}
