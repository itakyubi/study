package com.wa.demo.threadlocal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * ThreadController
 * 2021-07-15 10:43
 *
 * @author wuao
 */
@RestController
@RequestMapping("/v1/thread")
public class ThreadController {

    @Autowired
    private ThreadService threadService;

    @PostMapping("/post")
    public Map<String, String> post(@RequestParam(required = false, defaultValue = "admin") String userId,
                                    @RequestBody Map<String, Object> requestBody) {
        return threadService.post(userId, requestBody);
    }

}
