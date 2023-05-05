package com.wa.flink.job;

import com.wa.flink.model.Event;
import com.wa.flink.model.OutputEvent;
import com.wa.flink.source.MySource;
import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternSelectFunction;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.SimpleCondition;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

import java.time.Duration;
import java.util.List;
import java.util.Map;

/**
 * DynamicJob
 * 2023/4/27 2:09 下午
 *
 * @author wuao
 */
public class DynamicJob implements Runnable {

    private String name;
    private String condition;
    private SinkFunction sink;

    public DynamicJob(String name, String condition, SinkFunction sink) {
        this.name = name;
        this.condition = condition;
        this.sink = sink;
    }


    @Override
    public void run() {
        String cond = this.condition;
        //SinkFunction sink = this.sink;


        try {
            StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

            DataStreamSource<Event> dataStreamSource = env.addSource(new MySource(cond));
            DataStream<Event> dataStream = dataStreamSource.assignTimestampsAndWatermarks(
                    WatermarkStrategy.<Event>forBoundedOutOfOrderness(Duration.ofSeconds(3))
                            .withTimestampAssigner((SerializableTimestampAssigner<Event>) (data, l) -> data.getTs())
            );


            Pattern pattern = Pattern.<Event>begin("start").where(new SimpleCondition<Event>() {
                @Override
                public boolean filter(Event value) throws Exception {
                    return value.getName().equals(cond);
                }
            });

            PatternStream patternStream = CEP.pattern(dataStream, pattern);
            DataStream<OutputEvent> alarms = patternStream.select(new PatternSelectFunction<Event, OutputEvent>() {

                @Override
                public OutputEvent select(Map<String, List<Event>> pattern) throws Exception {
                    Event event = pattern.get("start").get(0);

                    OutputEvent outputEvent = new OutputEvent();
                    outputEvent.setCondition(cond);
                    outputEvent.setName(event.getName());
                    outputEvent.setTs(event.getTs());

                    return outputEvent;
                }
            });

            alarms.addSink(sink);


            env.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

}
