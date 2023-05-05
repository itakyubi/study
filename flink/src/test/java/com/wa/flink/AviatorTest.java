package com.wa.flink;

import com.googlecode.aviator.AviatorEvaluator;
import com.wa.flink.model.Event;
import org.apache.flink.cep.pattern.Pattern;
import org.junit.Test;

/**
 * AviatorTest
 * 2023/4/20 7:23 下午
 *
 * @author wuao
 */
public class AviatorTest {

    @Test
    public void testAviatorEvaluatorCompile() {
        Object object = AviatorEvaluator.execute("");
        System.out.println(object);


        Pattern pattern = Pattern.<Event>begin("start");
    }
}
