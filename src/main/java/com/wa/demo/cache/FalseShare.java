package com.wa.demo.cache;

/**
 * FalseShare
 * 2023/3/20 7:03 下午
 *
 * @author wuao
 */
public class FalseShare implements Runnable {

    public static int NUM_THREADS = 4;
    public final static long ITERATIONS = 500L * 1000L * 1000L;
    private final int arrayIndex;
    private static VolatileLong[] longs;
    public static long SUM_TIME = 0l;

    public FalseShare(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(final String[] args) throws Exception {
        Thread.sleep(10000);
        for (int j = 0; j < 10; j++) {
            System.out.println(j);
            if (args.length == 1) {
                NUM_THREADS = Integer.parseInt(args[0]);
            }
            longs = new VolatileLong[NUM_THREADS];
            for (int i = 0; i < longs.length; i++) {
                longs[i] = new VolatileLong();
            }
            final long start = System.nanoTime();
            runTest();
            final long end = System.nanoTime();
            SUM_TIME += end - start;
        }
        System.out.println("平均耗时：" + SUM_TIME / 10);
    }

    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseShare(i));
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
    }

    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = i;
        }
    }

    public final static class VolatileLong {
        public volatile long value = 0L;

        // 注释此行，  耗时21074111322，会出现伪缓存问题
        // 不注释此行，耗时11432735660
        // public long p1, p2, p3, p4, p5, p6;
    }

}
