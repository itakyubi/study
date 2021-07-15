package com.wa.demo.threadlocal;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ThreadConfiguration
 * 2021-07-15 11:10
 *
 * @author wuao
 */
@Configuration
public class ThreadConfiguration implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean authFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ThreadFilter());
        return registration;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ThreadInterceptor()).addPathPatterns("/v1/thread/**");
    }

    @Bean
    ThreadInterceptor ThreadInterceptor() {
        return new ThreadInterceptor();
    }
}
