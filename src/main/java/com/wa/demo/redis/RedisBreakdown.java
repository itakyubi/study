package com.wa.demo.redis;

import org.apache.commons.lang.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * RedisBreakdown
 * 2021-08-08 16:50
 * <p>
 * 缓存击穿
 *
 * @author wuao
 */
public class RedisBreakdown {

    private static JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        String key = "key";
        jedisPool.getResource().del(key);

        Thread thread1 = new Thread(() -> getValue(key));
        Thread thread2 = new Thread(() -> getValue(key));
        Thread thread3 = new Thread(() -> getValue(key));

        thread1.start();
        thread2.start();
        thread3.start();
    }

    // 可重入锁防止缓存击穿
    private static String getValue(String key) {
        Jedis jedis = jedisPool.getResource();
        String val = jedis.get(key);
        String threadName = Thread.currentThread().getName();
        if (StringUtils.isEmpty(val)) {
            if (lock.tryLock()) {
                try {
                    System.out.println(threadName + " get key from db");
                    val = String.valueOf(System.currentTimeMillis());
                    jedis.set("key", val);
                    System.out.println(threadName + " set key, val = " + val);
                } finally {
                    lock.unlock();
                }
            } else {
                val = getValue(key);
            }
        } else {
            System.out.println(threadName + " get key from redis, val=" + val);
        }
        return val;
    }
}
