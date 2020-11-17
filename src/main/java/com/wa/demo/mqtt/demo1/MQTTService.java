package com.wa.demo.mqtt.demo1;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * MQTTService
 * 2020-11-16 16:18
 *
 * @author wuao
 */
@Service
public class MQTTService {

    private MQTTClient mqttClient;

    @PostConstruct
    public void init() {
        mqttClient = new MQTTClient(this);
        mqttClient.connect();
        mqttClient.subscribe("baetyl");
    }


}
