package com.wa.spring.configuration;

import com.wa.spring.models.TestModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TestConfiguration
 * 2023/2/5 3:17 下午
 *
 * @author wuao
 */
@Configuration
public class TestConfiguration {

    @Bean(initMethod = "testInit", destroyMethod = "testDestroy")
    public TestModel testModel() {
        return new TestModel();
    }
}
