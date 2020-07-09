package com.wa.demo.utils;

import java.util.UUID;

/**
 * Utils
 * 2020-07-09 14:39
 *
 * @author wuao
 */
public class Utils {

    public String genUUID() {
        return UUID.randomUUID().toString();
    }

    public String genUUIDWithoutHyphen() {
        return genUUID().replaceAll("-", "");
    }
}
