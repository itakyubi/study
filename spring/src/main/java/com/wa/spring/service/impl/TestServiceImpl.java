package com.wa.spring.service.impl;

import com.wa.spring.service.TestService;
import org.springframework.stereotype.Service;

/**
 * TestService
 * 2023/2/12 3:53 下午
 *
 * @author wuao
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    public String getServiceName() {
        return "service name";
    }
}
