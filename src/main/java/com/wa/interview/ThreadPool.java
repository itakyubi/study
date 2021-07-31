package com.wa.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPool
 * 2021-07-31 16:15
 *
 * 功能：调用N个外部接口，等到所有接口返回后，计算返回值并返回。
 * 要求：
 * 1、整体流程超过200ms则直接返回false
 * 2、调用外部接口增加重试逻辑
 *
 * @author wuao
 */
public class ThreadPool {

    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        System.out.println(threadPool.handler());
    }

    private ExecutorService executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors() * 2,
            0, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10));


    public boolean handler() throws InterruptedException {
        List<Future<Boolean>> results = new ArrayList<>();
        int total = 4;
        CountDownLatch latch = new CountDownLatch(total);
        for (int i = 0; i < total; i++) {
            results.add(submit(latch));
        }

        boolean result = false;
        if (latch.await(200, TimeUnit.MILLISECONDS)) {
            result = cal(results);
        }
        executorService.shutdown();
        return result;
    }

    public Future<Boolean> submit(CountDownLatch latch) {
        return executorService.submit(() -> {
            int count = 0;
            boolean result = false;
            while (count < 3) {
                try {
                    result = callApi();
                    break;
                } catch (Exception e) {
                    count++;
                }
            }
            latch.countDown();
            return result;
        });
    }


    private boolean callApi() throws InterruptedException {
        int random = (int) (Math.random() * 300);
        Thread.sleep(random);
        return true;
    }

    private boolean cal(List<Future<Boolean>> results) {
        return results.stream().noneMatch(booleanFuture -> {
            try {
                return !booleanFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return false;
        });
    }
}
