package com.wa.demo.redis;

import redis.clients.jedis.JedisPool;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * RedisFlashSale
 * 2021-08-08 16:53
 * <p>
 * 秒杀抢购
 *
 * @author wuao
 */
public class RedisFlashSale {

    private static ExecutorService executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors() * 2,
            0, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100));
    private static JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);

    public static void main(String[] args) {
        // 初始化商品数量
        String watchKey = "product num";
        jedisPool.getResource().set(watchKey, "1000");

        // 模拟抢购
        for (int i = 0; i < 1000; i++) {
            FlashSale.FlashInfo flashInfo = new FlashSale.FlashInfo(jedisPool.getResource(),
                    getRandomBuyNum(), watchKey, getUserId());
            executor.submit(new FlashSale(flashInfo));
        }
        executor.shutdown();

        System.out.println("remain num:" + jedisPool.getResource().get(watchKey));
    }

    private static int getRandomBuyNum() {
        Random random = new Random();
        return random.nextInt(9) + 1;
    }

    private static String getUserId() {
        return UUID.randomUUID().toString();
    }
}
