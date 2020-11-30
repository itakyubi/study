package com.wa.source.code;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadLocalTest
 * 2020-11-29 16:00
 *
 * @author wuao
 */
public class ThreadLocalTest {
    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    private static AtomicInteger total = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            threadLocal.set(0);
            for (int i = 0; i < 10; i++) {
                threadLocal.set(threadLocal.get() + 1);
            }
            total.addAndGet(threadLocal.get());
        });

        Thread t2 = new Thread(() -> {
            threadLocal.set(0);
            for (int i = 0; i < 20; i++) {
                threadLocal.set(threadLocal.get() + 1);
            }
            total.addAndGet(threadLocal.get());
        });

        t1.start();
        t2.start();

        Thread.sleep(100);

        System.out.println(total.get());
    }

}
