package com.wa.interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * SequentialPrintingII
 * 2021-02-25 09:16
 *
 * @author wuao
 */
public class SequentialPrintingII {
    private static Lock lock = new ReentrantLock();
    private static Condition condition1 = lock.newCondition();
    private static Condition condition2 = lock.newCondition();
    private static Condition condition3 = lock.newCondition();

    private static class MyThread implements Runnable {
        private String s;
        private Condition signalCondition;
        private Condition awaitCondition;
        private Lock lock;

        public MyThread(String s, Condition c1, Condition c2, Lock lock) {
            this.s = s;
            this.signalCondition = c1;
            this.awaitCondition = c2;
            this.lock = lock;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    lock.lock();
                    System.out.print(s);
                    signalCondition.signal();
                    awaitCondition.await();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new MyThread("A", condition2, condition1, lock));
        Thread t2 = new Thread(new MyThread("B", condition3, condition2, lock));
        Thread t3 = new Thread(new MyThread("C", condition1, condition3, lock));

        t1.start();
        t2.start();
        t3.start();
    }
}

