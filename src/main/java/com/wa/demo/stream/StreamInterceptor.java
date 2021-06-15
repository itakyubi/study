package com.wa.demo.stream;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * StreamInterceptor
 * 2021-06-15 09:29
 *
 * @author wuao
 */
public class StreamInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        StreamRequestWrapper requestWrapper = new StreamRequestWrapper(request);
        String requestBody = new String(requestWrapper.getBody());
        System.out.println("interceptor requestBody:" + requestBody);
        return true;
    }
}
