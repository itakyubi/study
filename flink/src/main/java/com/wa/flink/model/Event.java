package com.wa.flink.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Event
 * 2023/4/20 4:53 下午
 *
 * @author wuao
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event{
    private String name;
    private long ts;

}
