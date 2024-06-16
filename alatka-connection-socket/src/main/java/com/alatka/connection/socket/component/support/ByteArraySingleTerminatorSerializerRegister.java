package com.alatka.connection.socket.component.support;

import com.alatka.connection.core.property.SerializerProperty;
import com.alatka.connection.core.component.AbstractComponentRegister;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

public class ByteArraySingleTerminatorSerializerRegister extends AbstractComponentRegister<SerializerProperty> {
    @Override
    protected BeanDefinitionBuilder doRegister(SerializerProperty param) {
        return null;
    }

    @Override
    protected Class<?> beanClass() {
        return null;
    }
}
