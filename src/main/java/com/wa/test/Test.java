package com.wa.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * com.wa.test.Test
 *
 * @desc:
 * @author: wuao <wuao@baidu.com>
 * @time: 2020-03-05 17:27
 */
public abstract class Test {
    private static Process mainProcess;
    private static volatile int mState = 0;
    private static ConcurrentLinkedQueue<String> detectQueue;
    private static ExecutorService executorService;
    private static Timer timer;
    private static boolean isBatchUpload = true;
    private static int number = 0;

    private static ConcurrentHashMap<String, String> uploadMap;
    private static long lastUploadTime;
    private static Lock lock;

    private static int count;
    private static int count2;
    private static Set<String> set;

    private static Runnable detectThread = new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                detectQueue.add(Integer.toString(i));
            }
            /*while (true) {
                try {
                    Thread.sleep(10000);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }*/
        }
    };

    private static void init() {
        detectQueue = new ConcurrentLinkedQueue<>();
        executorService = Executors.newFixedThreadPool(5);
        timer = new Timer();
        uploadMap = new ConcurrentHashMap<>();
        lastUploadTime = System.currentTimeMillis();
        lock = new ReentrantLock();
        set = new HashSet<>();
    }

    public static void main(String[] args) {
        init();
        new Thread(detectThread).start();
        executorService.submit(() -> {
            while (true) {
                try {
                    if (!detectQueue.isEmpty()) {
                        String detectStr = detectQueue.poll();

                        count2 += 1;
                        synchronized (uploadMap) {
                            uploadMap.put(detectStr, detectStr);
                            List<String> uploadList;
                            if (System.currentTimeMillis() - lastUploadTime > 1) {
                                uploadList = new ArrayList<>(uploadMap.values());
                                uploadMap.clear();
                                for (String s : uploadList) {
                                    //System.out.println(s);
                                    set.add(s);
                                    count += 1;
                                }
                                lastUploadTime = System.currentTimeMillis();
                            }
                        }


                    } else {
                        System.out.println("set size:" + set.size());
                        System.out.println("count:" + count);
                        System.out.println("count2:" + count2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
