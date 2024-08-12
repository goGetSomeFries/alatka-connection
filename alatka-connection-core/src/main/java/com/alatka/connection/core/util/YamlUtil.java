package com.alatka.connection.core.util;

import com.alatka.connection.core.model.RootModel;
import com.alatka.connection.core.module.DefinitionModuleBuilder;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

public class YamlUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper(new YAMLFactory());

    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> T getObject(File file, String rootName, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readerFor(clazz).withRootName(rootName).readValue(file);
        } catch (IOException e) {
            throw new RuntimeException("获取yaml文件错误", e);
        }
    }

}
