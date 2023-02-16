package com.wa.flink;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * UnBoundedStreamTask
 * 2023/2/16 2:10 下午
 *
 * @author wuao
 */
public class UnBoundedStreamTask {

    public static void main(String[] args) throws Exception {
        // 创建流式执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        ParameterTool parameterTool = ParameterTool.fromArgs(args);
        String hostName = parameterTool.get("host");
        String port = parameterTool.get("port");

        // 模拟无界流
        DataStreamSource<String> source = env.socketTextStream(hostName, Integer.parseInt(port));

        // 转换计算
        SingleOutputStreamOperator<Tuple2<String, Long>> wordTuple = source
                .flatMap((FlatMapFunction<String, Tuple2<String, Long>>) (line, out) -> {
                    // 每行文本分隔
                    String[] words = line.split(" ");
                    for (String word : words) {
                        out.collect(new Tuple2<>(word, 1L));
                    }
                });

        // 分组
        KeyedStream<Tuple2<String, Long>, String> wordKeyedStream = wordTuple.keyBy(value -> value.f0);

        // 求和
        SingleOutputStreamOperator<Tuple2<String, Long>> sum = wordKeyedStream.sum(1);

        sum.print();

        env.execute();
    }
}
