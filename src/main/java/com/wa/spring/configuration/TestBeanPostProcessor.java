package com.wa.spring.configuration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * TestBeanPostProcessor
 * 2023/2/5 3:37 下午
 *
 * @author wuao
 */
@Component
public class TestBeanPostProcessor implements BeanPostProcessor, DestructionAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.printf("BeanPostProcessor postProcessBeforeInitialization method, beanName{%s}\n", beanName);
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.printf("BeanPostProcessor postProcessAfterInitialization method, beanName{%s}\n", beanName);
        return null;
    }

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        System.out.printf("DestructionAwareBeanPostProcessor postProcessBeforeDestruction method, beanName{%s}\n", beanName);
    }
}
