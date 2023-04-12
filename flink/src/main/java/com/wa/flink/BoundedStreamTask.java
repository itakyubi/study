package com.wa.flink;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * BoundedStreamTask
 * 2023/2/16 1:55 下午
 *
 * @author wuao
 */
public class BoundedStreamTask {
    public static void main(String[] args) throws Exception {
        // 创建流式执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 模拟有界流数据，从文件读取
        DataStreamSource<String> source = env.readTextFile("src/main/java/com/wa/flink/input/words.txt");

        // 转换计算
        SingleOutputStreamOperator<Tuple2<String, Long>> wordTuple = source
                .flatMap((FlatMapFunction<String, Tuple2<String, Long>>) (line, out) -> {
                    // 每行文本分隔
                    String[] words = line.split(" ");
                    for (String word : words) {
                        out.collect(new Tuple2<>(word, 1L));
                    }
                })
                .returns(Types.TUPLE(Types.STRING, Types.LONG));

        // 分组
        KeyedStream<Tuple2<String, Long>, String> wordKeyedStream = wordTuple.keyBy((KeySelector<Tuple2<String, Long>, String>) value -> value.f0);

        // 求和
        SingleOutputStreamOperator<Tuple2<String, Long>> sum = wordKeyedStream.sum(1);

        sum.print();

        env.execute();
    }
}
