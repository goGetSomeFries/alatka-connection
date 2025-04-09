package com.alatka.connection.core.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;

public class YamlUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper(new YAMLFactory());

    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> T getObject(byte[] bytes, String rootName, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readerFor(clazz).withRootName(rootName).readValue(bytes);
        } catch (IOException e) {
            throw new RuntimeException("获取yaml文件错误", e);
        }
    }

}
