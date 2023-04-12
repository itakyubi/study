package com.wa.flink.source;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.Calendar;
import java.util.Random;

/**
 * CustomSource
 * 2023/2/16 2:39 下午
 *
 * @author wuao
 */
public class CustomSource {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.addSource(new RandomSource()).print();
        env.execute();
    }

    // 自定义的source
    // SourceFunction<OUT> 接口不支持并行操作
    // ParallelSourceFunction<OUT> 接口支持并行操作
    public static class RandomSource implements SourceFunction<Sensor> {

        private boolean running = true;

        @Override
        public void run(SourceContext<Sensor> sourceContext) throws Exception {
            Random random = new Random();
            while (running) {
                sourceContext.collect(new Sensor(
                        "sensor" + random.nextInt(50),
                        Calendar.getInstance().getTimeInMillis(),
                        random.nextInt(100)));
                Thread.sleep(1000);
            }
        }

        @Override
        public void cancel() {
            running = false;
        }
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Sensor {
    private String id;
    private Long ts;
    private Integer val;
}
