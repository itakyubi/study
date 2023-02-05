package com.wa.spring.models;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * TestModel
 * 2023/2/5 3:17 下午
 *
 * @author wuao
 */
public class TestModel implements BeanNameAware, InitializingBean, DisposableBean {

    private String name;

    public void testInit() {
        System.out.printf("TestModel init method, name{%s}\n", this.name);
    }

    public void testDestroy() {
        System.out.printf("TestModel destroy method, name{%s}\n", this.name);
    }

    @PostConstruct
    public void postConstruct() {
        System.out.printf("TestModel postConstruct method, name{%s}\n", this.name);
    }

    public TestModel() {
        this.name = "default";
    }


    public TestModel(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.printf("TestModel BeanNameAware setBeanName method, beanName{%s}, name{%s}\n", beanName, this.name);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.printf("TestModel InitializingBean afterPropertiesSet method, name{%s}\n", this.name);
    }

    @Override
    public void destroy() throws Exception {
        System.out.printf("TestModel DisposableBean destroy method, name{%s}\n", this.name);
    }
}
