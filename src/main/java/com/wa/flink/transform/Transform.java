package com.wa.flink.transform;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.ConnectedStreams;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.util.Arrays;

/**
 * Transform
 * 2023/2/16 3:30 下午
 *
 * @author wuao
 */
public class Transform {

    public void mapTransform() throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);
        DataStreamSource<Integer> source = env.fromElements(1, 2, 3, 4);
        source.map(new MapFunction<Integer, Integer>() {
            @Override
            public Integer map(Integer integer) throws Exception {
                return integer * integer;
            }
        }).print();
        env.execute();
    }

    public void filterTransform() throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);
        DataStreamSource<Integer> source = env.fromElements(1, 2, 3, 4);
        source.filter(new FilterFunction<Integer>() {
            @Override
            public boolean filter(Integer integer) throws Exception {
                return (integer & 1) == 0;
            }
        }).print();
        env.execute();
    }

    public void flatMapTransform() throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);
        DataStreamSource<int[]> source = env.fromElements(new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 9});
        source.flatMap(new FlatMapFunction<int[], Integer>() {
            @Override
            public void flatMap(int[] integers, Collector<Integer> collector) throws Exception {
                for (int i : integers) {
                    collector.collect(i * i);
                }
            }
        }).print();
        env.execute();
    }

    public void shuffleTransform() {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);
        DataStreamSource<Integer> source = env.fromElements(1, 2, 3, 4);
        DataStream<Integer> streamShuffle = source.shuffle();
    }

    public void connectTransform() {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);
        DataStreamSource<Integer> source = env.fromCollection(Arrays.asList(1, 2, 3, 4));
        DataStreamSource<Integer> source2 = env.fromElements(1, 2, 3, 4);
        ConnectedStreams<Integer, Integer> resultCS = source.connect(source2);
    }

    public void unionTransform() {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);
        DataStreamSource<Integer> source1 = env.fromCollection(Arrays.asList(1, 2, 3, 4));
        DataStreamSource<Integer> source2 = env.fromCollection(Arrays.asList(5, 6, 7, 8));
        DataStreamSource<Integer> source3 = env.fromCollection(Arrays.asList(9, 10));

        DataStream<Integer> unionStream = source1
                .union(source2)
                .union(source3);

        unionStream.print();
    }


}
