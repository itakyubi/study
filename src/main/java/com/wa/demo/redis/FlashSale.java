package com.wa.demo.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * FlashSale
 * 2021-08-08 16:58
 *
 * @author wuao
 */
public class FlashSale implements Runnable {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class FlashInfo {
        private Jedis jedis;
        private int buyNum;
        private String watchKey;
        private String useId;
    }

    private FlashInfo flashInfo;

    public FlashSale(FlashInfo flashInfo) {
        this.flashInfo = flashInfo;
    }

    @SneakyThrows
    @Override
    public void run() {
        Jedis jedis = flashInfo.jedis;
        String watchKey = flashInfo.watchKey;
        int buyNum = flashInfo.buyNum;
        String userId = flashInfo.useId;

        jedis.watch(watchKey);

        int remainNum = Integer.parseInt(jedis.get(watchKey));
        if (remainNum >= 1) {
            if (remainNum >= buyNum) {
                Transaction transaction = jedis.multi();
                transaction.decrBy(watchKey, buyNum);

                List<Object> transactionResults = transaction.exec();
                if (CollectionUtils.isEmpty(transactionResults)) {
                    System.out.println(userId + " failed.");
                } else {
                    System.out.println(String.format("%s buy %d success, before %d, remain num: %s",
                            userId, buyNum, remainNum, transactionResults.get(0).toString()));
                }
            } else {
                System.out.println(String.format("want to buy %s, remain %s, not enough", buyNum, remainNum));
            }
        } else {
            System.out.println("sale out.");
        }
        jedis.close();
    }
}
