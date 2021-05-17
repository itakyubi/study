package com.wa.source.code;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMapTest
 * 2021-05-17 16:58
 *
 * @author wuao
 */
public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        // cause dead lock
        Map<String, Integer> map = new ConcurrentHashMap<>(16);
        map.computeIfAbsent(
                "AaAa",
                key -> {
                    return map.computeIfAbsent(
                            "BBBB",
                            key2 -> 42);
                }
        );
        System.out.println("======  end  =============");
    }
}
