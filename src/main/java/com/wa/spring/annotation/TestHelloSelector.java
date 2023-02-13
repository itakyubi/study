package com.wa.spring.annotation;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * TestHelloSelector
 * 2023/2/13 8:59 下午
 *
 * @author wuao
 */
public class TestHelloSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{TestHello.class.getName()};
    }
}
