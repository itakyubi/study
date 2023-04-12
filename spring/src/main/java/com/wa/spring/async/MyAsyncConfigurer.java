package com.wa.spring.async;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * MyAsyncConfigurer
 * 2023/4/11 4:10 下午
 *
 * @author wuao
 */

@Configuration
public class MyAsyncConfigurer implements AsyncConfigurer {

    // 使用自定义的线程池执行异步任务
    @Bean("myAsyncExecutor")
    public ThreadPoolTaskExecutor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(10);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setThreadNamePrefix("myAsyncExecutor");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(5);
        executor.initialize();
        return executor;
    }

    @Override
    public Executor getAsyncExecutor() {
        return executor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> System.out.printf("执行异步任务'%s', error: %s", method, ex);
    }
}
