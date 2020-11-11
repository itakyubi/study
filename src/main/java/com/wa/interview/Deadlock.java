package com.wa.interview;

/**
 * Deadlock
 *
 * @author: wuao
 * @time: 2020/11/11 22:03
 **/
public class Deadlock {

    private static final String A = "A";
    private static final String B = "B";

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (A) {
                System.out.println("t1 get A lock");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    System.out.println("t1 get B lock");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (B) {
                System.out.println("t2 get B lock");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (A) {
                    System.out.println("t2 get A lock");
                }
            }
        });

        t1.start();
        t2.start();


    }
}
