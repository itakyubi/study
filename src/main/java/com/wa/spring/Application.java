package com.wa.spring;

import com.wa.spring.configuration.TestBeanPostProcessor;
import com.wa.spring.configuration.TestConfiguration;
import com.wa.spring.models.TestModel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Application
 * 2023/2/5 3:15 下午
 *
 * @author wuao
 */
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class, TestBeanPostProcessor.class);
        TestModel testModel = (TestModel) context.getBean("testModel");
        System.out.println(testModel.getName());
        ((AnnotationConfigApplicationContext) context).destroy();
    }
}
