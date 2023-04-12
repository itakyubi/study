package com.wa.influxdb.configuration;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * InfluxConfiguration
 * 2023/2/17 2:50 下午
 *
 * @author wuao
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "influxdb2")
public class InfluxConfiguration {

    private String url;
    private String token;
    private String org;
    private String bucket;

    @Bean
    public InfluxDBClient influxDBClient() {
        return InfluxDBClientFactory.create(url, token.toCharArray(), org, bucket);
    }
}
