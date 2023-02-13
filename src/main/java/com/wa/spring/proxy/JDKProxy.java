package com.wa.spring.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JDKProxy
 * 2023/2/12 5:53 下午
 *
 * @author wuao
 */
public class JDKProxy implements InvocationHandler {

    private Subject subject;

    public JDKProxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object o = method.invoke(subject, args);
        return o;
    }
}
