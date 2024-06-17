package com.alatka.connection.socket.component.support;

import com.alatka.connection.core.component.AbstractComponentRegister;
import com.alatka.connection.core.property.SerializerProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.ip.tcp.serializer.ByteArraySingleTerminatorSerializer;

import java.util.Map;

/**
 * @author ybliu
 */
public class ByteArraySingleTerminatorSerializerRegister extends AbstractComponentRegister<SerializerProperty> {

    private static final String KEY_DELIMITER = "delimiter";

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, SerializerProperty property) {
        Map<String, Object> params = property.getParams();
        builder.addConstructorArgValue(params.get(KEY_DELIMITER));
    }

    @Override
    protected Class<?> beanClass() {
        return ByteArraySingleTerminatorSerializer.class;
    }
}
