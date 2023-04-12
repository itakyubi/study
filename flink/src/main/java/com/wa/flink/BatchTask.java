package com.wa.flink;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.FlatMapOperator;
import org.apache.flink.api.java.operators.UnsortedGrouping;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * BatchTask
 * 2023/2/16 10:45 上午
 *
 * @author wuao
 */

public class BatchTask {

    /*如果本地跑flink，需要把ch.qos.logback排除掉
    具体原因未知，但是可能是因为flink没有绑定数据源导致springboot认为flink已经停止*/
    public static void main(String[] args) throws Exception {
        // 创建执行环境
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        // 模拟批量数据，从文件读取
        DataSource<String> source = env.readTextFile("src/main/java/com/wa/flink/input/words.txt");

        // 数据打平
        FlatMapOperator<String, Tuple2<String, Long>> wordTuple = source
                .flatMap((String line, Collector<Tuple2<String, Long>> out) -> {
                    // 每行文本分隔
                    String[] words = line.split(" ");
                    for (String word : words) {
                        out.collect(new Tuple2<>(word, 1L));
                    }
                })
                .returns(Types.TUPLE(Types.STRING, Types.LONG));

        // 分组
        UnsortedGrouping<Tuple2<String, Long>> wordGroup = wordTuple.groupBy(0);

        // 聚合
        AggregateOperator<Tuple2<String, Long>> agg = wordGroup.sum(1);

        agg.print();
    }

}
