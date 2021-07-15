package com.wa.demo.threadlocal;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadService
 * 2021-07-15 10:47
 *
 * @author wuao
 */
@Service
public class ThreadService {

    private final ObjectMapper MAPPER = new ObjectMapper();

    public Map<String, String> post(String userId, Map<String, Object> requestBody) {
        Map<String, String> result = new HashMap<>();

        result.put("ThreadHelper userId", ThreadHelper.getUserId());
        result.put("request param userId", userId);

        try {
            result.put("request body", MAPPER.writeValueAsString(requestBody));
        } catch (Exception e) {
            result.put("request body", e.getMessage());
        }

        return result;
    }
}
