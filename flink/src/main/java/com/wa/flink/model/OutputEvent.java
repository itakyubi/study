package com.wa.flink.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * OutputEvent
 * 2023/4/27 2:24 下午
 *
 * @author wuao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputEvent extends Event {
    private String condition;
}
