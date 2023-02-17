package com.wa.influxdb.controller;

import com.wa.influxdb.service.impl.InfluxServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * InfluxController
 * 2023/2/17 4:04 下午
 *
 * @author wuao
 */
@RequestMapping("/influx")
@RestController
public class InfluxController {

    @Autowired
    private InfluxServiceImpl influxService;

    @GetMapping("/write")
    public void write() {
        influxService.write();
    }

    @GetMapping("/get")
    public void get() {
        influxService.get();
    }

}
