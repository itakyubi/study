package com.wa.demo.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * AuthConfiguration
 * 2021-03-04 16:00
 *
 * @author wuao
 */
@Configuration
public class AuthConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(AuthInterceptor()).addPathPatterns("/**");
    }

    @Bean
    AuthInterceptor AuthInterceptor() {
        return new AuthInterceptor();
    }
}
