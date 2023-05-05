package com.wa.flink.service;

import com.wa.flink.job.DynamicJob;
import com.wa.flink.sink.MySink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * RuleService
 * 2023/4/27 2:04 下午
 *
 * @author wuao
 */

@Service
public class RuleService {

    @Autowired
    private MySink sink;

    private ExecutorService executor;

    @PostConstruct
    public void init() {
        executor = new ThreadPoolExecutor(2, 4, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        // job
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            addRule(String.valueOf((char) ('a' + random.nextInt(26))));
        }

    }

    public void addRule(String condition) {
        DynamicJob dynamicJob = new DynamicJob(condition, condition, sink);
        executor.submit(dynamicJob);
    }


}
