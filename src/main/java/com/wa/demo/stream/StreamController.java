package com.wa.demo.stream;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * StreamController
 * 2021-06-15 09:29
 *
 * @author wuao
 */
@RestController
@RequestMapping("/v1/stream")
public class StreamController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/add")
    public String add(@RequestBody StreamRequest request) {
        try {
            return "controller requestBody:" + objectMapper.writeValueAsString(request);
        } catch (IOException e) {
            return "parse request error" + e.getMessage();
        }
    }
}
