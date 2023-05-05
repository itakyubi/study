package com.wa.flink.sink;

import com.wa.flink.model.OutputEvent;
import com.wa.flink.utils.JsonUtils;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * MySink
 * 2023/4/27 12:08 下午
 *
 * @author wuao
 */
@Component
public class MySink implements SinkFunction<OutputEvent>, Serializable {

    @Override
    public void invoke(OutputEvent outputEvent, Context context) throws Exception {
        System.out.printf("sink: outputEvent:『%s』, ts: 『%s』\n ", JsonUtils.toJson(outputEvent), System.currentTimeMillis());
    }
}
