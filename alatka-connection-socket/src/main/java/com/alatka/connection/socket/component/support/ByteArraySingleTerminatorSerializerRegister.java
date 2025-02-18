package com.alatka.connection.socket.component.support;

import com.alatka.connection.core.component.support.SupportComponentRegister;
import com.alatka.connection.core.property.socket.SerializerProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.ip.tcp.serializer.ByteArraySingleTerminatorSerializer;

import java.util.Map;

/**
 * @author ybliu
 */
public class ByteArraySingleTerminatorSerializerRegister extends SupportComponentRegister<SerializerProperty> {

    private static final String KEY_DELIMITER = "delimiter";

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, SerializerProperty property) {
        Map<String, Object> params = property.getParams();
        builder.addConstructorArgValue(params.get(KEY_DELIMITER));
    }

    @Override
    protected Class<?> componentClass(SerializerProperty property) {
        return ByteArraySingleTerminatorSerializer.class;
    }

    @Override
    public Class<SerializerProperty> mappingKey() {
        return SerializerProperty.class;
    }
}
