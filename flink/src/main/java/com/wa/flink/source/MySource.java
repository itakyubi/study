package com.wa.flink.source;

import com.wa.flink.model.Event;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.io.Serializable;
import java.util.Random;

/**
 * MySource
 * 2023/4/20 5:12 下午
 *
 * @author wuao
 */
public class MySource implements SourceFunction<Event>, Serializable {

    private String name;
    private boolean isRunning = true;
    private Random random = new Random();

    public MySource(String name) {
        this.name = name;
    }

    @Override
    public void run(SourceContext<Event> ctx) throws Exception {

        while (isRunning) {
            Event event = new Event(String.valueOf((char) ('a' + random.nextInt(26))), System.currentTimeMillis());
            System.out.printf("producer{%s}: %s\n", name, event);
            ctx.collect(event);
            Thread.sleep(1000);
        }
    }

    @Override
    public void cancel() {
        this.isRunning = false;
    }

}
