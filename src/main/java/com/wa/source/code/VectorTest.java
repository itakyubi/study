package com.wa.source.code;

import java.util.Vector;

/**
 * VectorTest
 *
 * Vector类并不是线程安全的
 * Vector类中的方法加了synchronized关键字，但是其只能保证同一时间只有一个线程使用该方法
 * 对于一个线程使用多个方法时无法保证线程安全
 * 本例中printThread先调用size()方法，后调用get()方法，由于两个方法之间并没有加锁，所以出现异常
 * 这种异常类似于数据库中的幻读
 *
 * @author: wuao
 * @time: 2020/11/29 15:29
 **/
public class VectorTest {

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread removeThread = new Thread(() -> {
                for (int i = 0; i < vector.size(); i++) {
                    vector.remove(i);
                }
            });

            Thread printThread = new Thread(() -> {
                for (int i = 0; i < vector.size(); i++) {
                    System.out.println(vector.get(i));
                }
            });

            removeThread.start();
            printThread.start();

            while (Thread.activeCount() > 20) ;
        }
    }
}
