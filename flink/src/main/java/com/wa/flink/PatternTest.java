package com.wa.flink;

import com.wa.flink.model.Event;
import com.wa.flink.source.InternalSource;
import com.wa.flink.utils.JsonUtils;
import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternSelectFunction;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.SimpleCondition;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * PatternTest
 * 2023/4/27 10:11 上午
 *
 * @author wuao
 */
public class PatternTest {

    private ExecutorService executor = new ThreadPoolExecutor(1, 2, 5L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>());

    private static class PatternThreadA implements Runnable {

        @Override
        public void run() {
            try {
                runTask("a");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class PatternThreadB implements Runnable {

        @Override
        public void run() {
            try {
                runTask("b");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class PatternThreadC implements Runnable {

        @Override
        public void run() {
            try {
                runTask("c");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void runTask(String str) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<Event> dataStream = env.addSource(new InternalSource())
                .assignTimestampsAndWatermarks(
                        WatermarkStrategy.<Event>forBoundedOutOfOrderness(Duration.ofSeconds(3))
                                .withTimestampAssigner((SerializableTimestampAssigner<Event>) (data, l) -> data.getTs())
                );


        Pattern pattern = Pattern.<Event>begin("start").where(new SimpleCondition<Event>() {
            @Override
            public boolean filter(Event value) throws Exception {
                return value.getName().equals(str);
            }
        });

        PatternStream patternStream = CEP.pattern(dataStream, pattern);
        DataStream<String> alarms = patternStream.select(new PatternSelectFunction<Event, String>() {

            @Override
            public String select(Map<String, List<Event>> pattern) throws Exception {
                return JsonUtils.toJson(pattern);
            }
        });

        //alarms.addSink(new MySink());

        env.execute();
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new PatternThreadA());
        Thread t2 = new Thread(new PatternThreadB());
        Thread t3 = new Thread(new PatternThreadC());

        t1.start();
        t2.start();
        t3.start();


    }
}
