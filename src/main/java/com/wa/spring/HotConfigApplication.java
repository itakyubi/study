package com.wa.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * HotConfigApplication
 * 2023/2/18 4:12 下午
 *
 * @author wuao
 */
@SpringBootApplication
@EnableConfigurationProperties
public class HotConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(HotConfigApplication.class, args);
    }
}
