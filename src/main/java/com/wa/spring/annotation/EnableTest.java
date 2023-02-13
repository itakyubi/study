package com.wa.spring.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * EnableTest
 * 2023/2/13 7:48 下午
 *
 * @author wuao
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
//@Import(TestHelloSelector.class)
@Import(TestHelloBeanDefinitionRegistrar.class)
public @interface EnableTest {
}
