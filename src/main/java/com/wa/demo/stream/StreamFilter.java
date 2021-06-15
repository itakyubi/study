package com.wa.demo.stream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * StreamFilter
 * 2021-06-15 09:36
 *
 * @author wuao
 */
public class StreamFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletRequest requestWrapepr = null;
        if (request instanceof HttpServletRequest) {
            requestWrapepr = new StreamRequestWrapper((HttpServletRequest) request);
        }
        if (requestWrapepr == null) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(requestWrapepr, response);
        }
    }
}
