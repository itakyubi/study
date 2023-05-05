package com.wa.flink.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * JsonUtil
 * 2023/4/27 10:56 上午
 *
 * @author wuao
 */
@Slf4j
public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> T fromJson(String content, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.readValue(content, valueType);
        } catch (IOException e) {
            log.error("json utils from json failed, content = {}, type = {}, exception message = {}",
                    content, valueType.getName(), e.getMessage());
            return null;
        }
    }

    public static <T> T fromJsonNode(JsonNode node, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.treeToValue(node, valueType);
        } catch (JsonProcessingException e) {
            log.error("json utils from json node failed, json node = {}, type = {}, exception message = {}",
                    node, valueType.getName(), e.getMessage());
            return null;
        }
    }

    public static String toJson(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (IOException e) {
            log.error(e.getMessage());
            return "";
        }
    }

    public static JsonNode parseJsonNodeFromString(String content) {
        try {
            return OBJECT_MAPPER.readTree(content);
        } catch (IOException e) {
            log.error("json utils parse jsonNode failed, content = {}, exception message = {}",
                    content, e.getMessage());
            return null;
        }
    }
}

