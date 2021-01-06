package com.wa.interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * SequentialPrinting
 * 多线程顺序打印
 * 2021-01-06 17:35
 *
 * @author wuao
 */
public class SequentialPrinting {

    public static void main(String[] args) {
        printWithLock();
    }

    /**
     * print with flag
     */
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


    /**
     * print with lock
     */
    private static Lock lock = new ReentrantLock();
    private static Condition condition1 = lock.newCondition();
    private static Condition condition2 = lock.newCondition();
    private static int i = 0;

    private static void printWithLock() {
        Thread t1 = new Thread(() -> {
            while (i <= 20) {
                lock.lock();
                try {
                    while (i % 2 == 1) {
                        condition1.await();
                    }
                    System.out.println(i);
                    i++;
                    condition2.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        });

        Thread t2 = new Thread(() -> {
            while (i <= 20) {
                lock.lock();
                try {
                    while (i % 2 == 0) {
                        condition2.await();
                    }
                    System.out.println(i);
                    i++;
                    condition1.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        });

        t1.start();
        t2.start();
    }
}
