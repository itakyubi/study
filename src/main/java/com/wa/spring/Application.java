package com.wa.spring;

import com.wa.spring.aspect.TestAspect;
import com.wa.spring.configuration.TestBeanPostProcessor;
import com.wa.spring.configuration.TestConfiguration;
import com.wa.spring.models.TestModel;
import com.wa.spring.service.impl.TestServiceImpl;
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
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class,
                TestAspect.class, TestServiceImpl.class, TestBeanPostProcessor.class);
        TestModel testModel = (TestModel) context.getBean("testModel");
        System.out.println(testModel.getName());

        TestServiceImpl testServiceImpl = context.getBean(TestServiceImpl.class);
        System.out.println(testServiceImpl.getServiceName());

        ((AnnotationConfigApplicationContext) context).destroy();
    }
}
