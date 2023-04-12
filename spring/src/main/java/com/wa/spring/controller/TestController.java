package com.wa.spring.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * TestController
 * 2023/2/18 4:10 下午
 *
 * @author wuao
 */
@Controller
//@Scope(scopeName = "refresh")
@ConfigurationProperties(prefix = "test")
public class TestController {
    //@Value("${test.name}")
    private String name;

    @GetMapping("/test/get")
    public String get() {
        return name;
    }
}
