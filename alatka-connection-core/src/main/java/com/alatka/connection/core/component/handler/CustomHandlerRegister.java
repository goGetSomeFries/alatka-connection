package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.property.core.HandlerProperty;
import com.alatka.connection.core.support.CustomHandler;
import com.alatka.connection.core.util.ClassUtil;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.handler.ServiceActivatingHandler;

/**
 * TODO
 *
 * @author ybliu
 * @see CustomHandler
 */
public class CustomHandlerRegister extends HandlerComponentRegister<HandlerProperty> {

    private static final String KEY_CLASS_NAME = "className";

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, HandlerProperty property) {
        String className = this.getParamsValueOrThrow(property.getParams(), KEY_CLASS_NAME);
        Object instance = ClassUtil.newInstance(className);
        if (!CustomHandler.class.isAssignableFrom(instance.getClass())) {
            throw new IllegalArgumentException(instance.getClass().getName() + " must be an instance of " + CustomHandler.class.getName());
        }
        builder.addConstructorArgValue(instance)
                .addConstructorArgValue(CustomHandler.METHOD_NAME);
    }

    @Override
    protected Class<ServiceActivatingHandler> componentClass() {
        return ServiceActivatingHandler.class;
    }

    @Override
    public HandlerProperty.Type mappingKey() {
        return HandlerProperty.Type.custom;
    }
}
