package com.wa.flink.source;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Source
 * 2023/2/16 2:26 下午
 *
 * @author wuao
 */
public class Source {

    // 官方提供的不同source
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 从文件读取
        DataStreamSource<String> sourceFile = env.readTextFile("src/main/java/com/wa/flink/input/words.txt");

        // 从集合读取
        List<String> list = Arrays.asList("1", "2", "3");
        DataStreamSource<String> sourceCollection = env.fromCollection(list);

        // 直接读取元素
        DataStreamSource<String> sourceElement = env.fromElements("1", "2", "3");

        // 从socket读取
        DataStreamSource<String> sourceSocket = env.socketTextStream("localhost", 9999);

        // 从kafka读取
        Properties properties = new Properties();
        properties.setProperty("boosstrrap.servers", "hdp1:9092,hdp2:9092,hdp3:9092");
        properties.setProperty("group.id", "Flink_source_kafka");
        properties.setProperty("auto.offset.reset", "latest");
        DataStreamSource<String> sourceKafka = env
                .addSource(new FlinkKafkaConsumer<>("test", new SimpleStringSchema(StandardCharsets.UTF_8), properties));


        sourceFile.print();
        sourceCollection.print();
        sourceElement.print();
        sourceSocket.print();
        sourceKafka.print();

        env.execute();
    }
}
