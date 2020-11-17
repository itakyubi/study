package com.wa.demo.mqtt.demo1;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * MQTTClient
 * 2020-11-16 16:19
 *
 * @author wuao
 */
@Slf4j
public class MQTTClient implements MqttCallback {

    private MQTTService mqttService;
    private MqttClient mqttClient;
    private MqttConnectOptions options;

    public MQTTClient(MQTTService mqttService) {
        this.mqttService = mqttService;
        this.options = getOptions();
    }

    public void connect() {
        try {
            mqttClient = new MqttClient("tcp://127.0.0.1:1883", "wa", new MemoryPersistence());
            mqttClient.setCallback(MQTTClient.this);
            mqttClient.connect(options);
        } catch (MqttException e) {
            log.error("connect mqtt broker error.", e);
        }
    }

    public void subscribe(String topic) {
        try {
            mqttClient.subscribe(topic, 0);
        } catch (MqttException e) {
            log.error("subscribe mqtt topic:" + topic + " error.", e);
        }
    }

    @Override
    public void connectionLost(Throwable throwable) {
        // TODO reconnect to broker
        while (true) {
            try {
                Thread.sleep(1000);
                mqttService.init();
                break;
            } catch (Exception e) {
                log.error("reconnect mqtt error.", e);
            }
        }
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        // TODO do something after receive a msg
        System.out.println(new String(mqttMessage.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        // TODO do something after msg delivery
        System.out.println("msg delivery complete.");
    }

    private MqttConnectOptions getOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(false);
        options.setCleanSession(false);
        options.setUserName("wa");
        options.setPassword("pwd".toCharArray());
        options.setKeepAliveInterval(20);
        return options;
    }
}
