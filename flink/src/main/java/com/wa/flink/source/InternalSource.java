package com.wa.flink.source;

import com.wa.flink.model.Event;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * InternalSource
 * 2023/4/20 5:12 下午
 *
 * @author wuao
 */

@Component
public class InternalSource implements SourceFunction<Event> {

    private BlockingQueue<Event> queue;
    private boolean isRunning = true;
    private Random random = new Random();

    @Override
    public void run(SourceContext<Event> ctx) throws Exception {
        while (isRunning) {
            Event event = new Event(String.valueOf((char) ('a' + random.nextInt(3))), System.currentTimeMillis());
            //System.out.println("-----------------produce:" + JsonUtils.toJson(event));
            ctx.collect(event);
            Thread.sleep(500);
        }
    }

    @Override
    public void cancel() {
        this.isRunning = false;
    }
}
