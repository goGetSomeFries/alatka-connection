package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.property.HandlerProperty;
import com.alatka.connection.core.support.Custom;
import com.alatka.connection.core.util.ClassUtil;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.handler.ServiceActivatingHandler;

import java.util.Map;

/**
 * @author ybliu
 */
public class CustomRegister extends HandlerComponentRegister<HandlerProperty> {

    private static final String KEY_CLASS_NAME = "className";

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, HandlerProperty property) {
        Map<String, Object> params = property.getParams();
        String className = (String) params.get(KEY_CLASS_NAME);
        Object instance = ClassUtil.newInstance(className);
        if (!(instance instanceof Custom)) {
            throw new IllegalArgumentException(instance + "必须实现" + Custom.class);
        }
        builder.addConstructorArgValue(instance).addConstructorArgValue("execute");
    }

    @Override
    protected Class<ServiceActivatingHandler> beanClass() {
        return ServiceActivatingHandler.class;
    }

    @Override
    public HandlerProperty.Type mappingKey() {
        return HandlerProperty.Type.custom;
    }
}
