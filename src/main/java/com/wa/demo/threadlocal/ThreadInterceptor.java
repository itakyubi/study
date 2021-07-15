package com.wa.demo.threadlocal;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * ThreadInterceptor
 * 2021-07-15 10:46
 *
 * @author wuao
 */
public class ThreadInterceptor extends HandlerInterceptorAdapter {

    private final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ThreadWrapper wrapper = new ThreadWrapper(request);


        String requestBody = new String(wrapper.getBody());

        Map<String, Object> requestBodyMap = MAPPER.readValue(requestBody, Map.class);

        ThreadHelper.setUserId(requestBodyMap.get("userId") + "-" + System.currentTimeMillis());

        return true;
    }
}
