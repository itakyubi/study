package com.wa.flink.groovy

import com.wa.flink.model.Event
import org.apache.flink.cep.pattern.Pattern

def getP() {
    return Pattern.<Event>begin("start")
            .where(new MyCondition()).times(5);
}

