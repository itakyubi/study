package com.wa.demo.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * StreamConfiguration
 * 2021-06-15 09:30
 *
 * @author wuao
 */
@Configuration
@Slf4j
public class StreamConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(StreamInterceptor()).addPathPatterns("/**");
    }

    @Bean
    StreamInterceptor StreamInterceptor() {
        return new StreamInterceptor();
    }

    @Bean
    public FilterRegistrationBean streamFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new StreamFilter());
        //registration.addUrlPatterns("/*");
        //registration.setName("xStreamFilter");
        //registration.setOrder(Ordered.LOWEST_PRECEDENCE);

        return registration;
    }




}
