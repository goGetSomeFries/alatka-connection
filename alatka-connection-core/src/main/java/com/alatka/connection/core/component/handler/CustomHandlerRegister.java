package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.property.core.HandlerProperty;
import com.alatka.connection.core.support.CustomHandler;
import com.alatka.connection.core.util.ClassUtil;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.handler.ServiceActivatingHandler;

import java.util.Map;

/**
 * @author ybliu
 * @see CustomHandler
 */
public class CustomHandlerRegister extends HandlerComponentRegister<HandlerProperty> {

    private static final String KEY_CLASS_NAME = "className";

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, HandlerProperty property) {
        Map<String, Object> params = property.getParams();
        String className = (String) params.get(KEY_CLASS_NAME);
        Object instance = ClassUtil.newInstance(className);
        if (!(instance instanceof CustomHandler)) {
            throw new IllegalArgumentException(instance + "必须实现" + CustomHandler.class);
        }
        builder.addConstructorArgValue(instance).addConstructorArgValue("execute");
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
