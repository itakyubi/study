package com.wa.source.code;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * HashMapTest
 * 2020-11-16 18:58
 *
 * hashmap在遍历过程中使用remove方法删除元素时，必须使用迭代器遍历，否则会出现ConcurrentModificationException异常
 * 原因是hashmap中的modCount和expectedModCount不一致，这是一种快速失败机制，为了防止hashmap在高并发情况下，多个线程同时修改map导致数据不一致的问题
 *
 * @author wuao
 */
public class HashMapTest {

    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            map.put(i, i);
        }

        Thread thread1 = new Thread(() -> {
            Iterator iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                try {
                    Integer i = (Integer) iterator.next();
                    iterator.remove();
                    System.out.println("1:" + i);
                } catch (Exception e) {
                    if (!e.getMessage().contains("ConcurrentModificationException")){
                        System.out.println("1:" + e);
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            Iterator iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                try {
                    Integer i = (Integer) iterator.next();
                    iterator.remove();
                    System.out.println("2:" + i);
                } catch (Exception e) {
                    if (!e.getMessage().contains("ConcurrentModificationException")){
                        System.out.println("2:" + e);
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
