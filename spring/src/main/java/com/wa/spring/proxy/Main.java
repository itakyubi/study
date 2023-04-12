package com.wa.spring.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Main
 * 2023/2/12 5:56 下午
 *
 * @author wuao
 */
public class Main {

    public static void main(String[] args) {
        InvocationHandler handler = new JDKProxy(new Subject());

        Subject proxySubject = (Subject) Proxy.newProxyInstance(Main.class.getClassLoader(), new Class[]{Subject.class}, handler);
    }
}
