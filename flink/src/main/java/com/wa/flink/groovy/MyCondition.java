package com.wa.flink.groovy;

import com.wa.flink.model.Event;
import org.apache.flink.cep.pattern.conditions.SimpleCondition;

/**
 * MyCondition
 * 2023/4/26 11:39 上午
 *
 * @author wuao
 */
public class MyCondition extends SimpleCondition<Event> {

    @Override
    public boolean filter(Event event) throws Exception {
        return event.getName().equals("a");
    }
}
