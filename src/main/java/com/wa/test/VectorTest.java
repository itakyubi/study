package com.wa.test;

import java.util.Vector;

/**
 * VectorTest
 *
 * Vector类并不是线程安全的
 * Vector类中的方法加了synchronized关键字，但是其只能保证同一时间只有一个线程使用该方法
 * 对于多个线程使用多个方法时无法保证线程安全
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
