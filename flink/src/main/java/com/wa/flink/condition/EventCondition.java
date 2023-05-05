package com.wa.flink.condition;

import com.googlecode.aviator.AviatorEvaluator;
import com.wa.flink.model.Event;
import org.apache.flink.cep.pattern.conditions.SimpleCondition;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * EventCondition
 * 2023/4/20 7:10 下午
 *
 * @author wuao
 */
public class EventCondition extends SimpleCondition<Event> implements Serializable {
    private String script;

   /* static {
        AviatorEvaluator.addFunction(new GetFieldFunction());
    }*/

    public EventCondition(String script) {
        this.script = script;
    }

    @Override
    public boolean filter(Event event) throws Exception {
        Map<String, Object> stringObjectMap = obj2Map(event);
        //计算表达式的值


        boolean result = (Boolean) AviatorEvaluator.execute(script, stringObjectMap);
        return result;
    }

    public static Map<String, Object> obj2Map(Object obj) {
        Map<String, Object> map = new HashMap<>();
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(obj));
            } catch (Exception e) {
            }
        }
        return map;
    }
}
