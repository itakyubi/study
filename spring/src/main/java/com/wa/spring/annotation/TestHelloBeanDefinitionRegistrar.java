package com.wa.spring.annotation;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * TestHelloBeanDefinitionRegistrar
 * 2023/2/13 9:06 下午
 *
 * @author wuao
 */
public class TestHelloBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        BeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClassName(TestHello.class.getName());
        beanDefinitionRegistry.registerBeanDefinition("testHello", beanDefinition);
    }
}
