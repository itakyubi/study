package com.wa.flink.transform.function;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.configuration.Configuration;

/**
 * MyRichMapFunction
 * 2023/2/16 3:42 下午
 *
 * @author wuao
 */
public class MyRichMapFunction extends RichMapFunction<String, String> {

    @Override
    public String map(String s) throws Exception {
        return s + s;
    }

    @Override
    public void open(Configuration configuration) throws Exception {
        System.out.println("open");
    }

    @Override
    public void close() throws Exception {
        System.out.println("close");
    }

}
