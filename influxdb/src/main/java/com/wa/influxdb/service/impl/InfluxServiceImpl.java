package com.wa.influxdb.service.impl;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;
import com.influxdb.query.dsl.Flux;
import com.wa.influxdb.configuration.InfluxConfiguration;
import com.wa.influxdb.service.InfluxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

/**
 * InfluxServiceImpl
 * 2023/2/17 3:50 下午
 *
 * @author wuao
 */
@Service
public class InfluxServiceImpl implements InfluxService {

    @Autowired
    private InfluxConfiguration influxConfiguration;

    @Autowired
    private InfluxDBClient influxDBClient;


    @Override
    public void write() {
        Point point = Point.measurement("temperature")
                .addTag("location", "west")
                .addField("value", 55D)
                .time(Instant.now().toEpochMilli(), WritePrecision.MS);
        influxDBClient.getWriteApiBlocking().writePoint(point);
    }

    @Override
    public void get() {
        String flux = Flux
                .from(influxConfiguration.getBucket())
                .range(Instant.ofEpochSecond(0))
                .toString();
        /*String flux = "from(bucket:\"wa\") |> range(start: 0)";*/
        List<FluxTable> list = influxDBClient.getQueryApi().query(flux);

        for (FluxTable fluxTable : list) {
            List<FluxRecord> records = fluxTable.getRecords();
            for (FluxRecord fluxRecord : records) {
                System.out.println(fluxRecord.getTime() + ": " + fluxRecord.getValueByKey("_value"));
            }
        }

    }
}
