package com.wa.spring;

import com.wa.spring.models.TestModel;
import com.wa.spring.service.impl.TestServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * SpringBootApplication
 * 2023/2/12 4:18 下午
 *
 * @author wuao
 */
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@ComponentScan("com.wa.spring.*")
public class SpringbootApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringbootApplication.class);
        TestModel testModel = (TestModel) context.getBean("testModel");
        System.out.println(testModel.getName());

        TestServiceImpl testServiceImpl = context.getBean(TestServiceImpl.class);
        System.out.println(testServiceImpl.getServiceName());

        ((ConfigurableApplicationContext) context).close();
    }
}
