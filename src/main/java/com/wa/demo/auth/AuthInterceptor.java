package com.wa.demo.auth;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * AuthInterceptor
 * 2021-03-04 10:02
 *
 * @author wuao
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler == null || !handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
        if (auth == null) {
            return true;
        }

        String[] needRole = auth.role();
        String[] needPermission = auth.permission();

        String uuid = request.getHeader("uuid");

        // get role and permission by uuid
        List<String> hasRole = new ArrayList<>();
        List<String> hasPermission = new ArrayList<>();
        if ("0".equals(uuid)) {
            hasRole = Arrays.asList("ADMIN");
            hasPermission = Arrays.asList("ADD", "DELETE", "READ");
        }
        if ("1".equals(uuid)) {
            hasRole = Arrays.asList("USER");
            hasPermission = Arrays.asList("ADD", "READ");
        }
        if ("2".equals(uuid)) {
            hasRole = Arrays.asList("GUEST");
            hasPermission = Arrays.asList("READ");
        }

        boolean isAuth = false;
        if (needRole.length > 0) {
            isAuth = !Collections.disjoint(Arrays.asList(needRole), hasRole);
        }
        if (needPermission.length > 0) {
            isAuth = isAuth || !Collections.disjoint(Arrays.asList(needPermission), hasPermission);
        }

        System.out.println("uuid:" + uuid);
        System.out.println("isAuth:" + isAuth);
        System.out.println("needRole:" + Arrays.toString(needRole));
        System.out.println("needPermission:" + Arrays.toString(needPermission));
        System.out.println("hasRole:" + hasRole);
        System.out.println("hasPermission:" + hasPermission);

        if (!isAuth) {
            throw new Exception("no auth!");
        }

        return true;
    }
}
