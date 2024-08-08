package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.property.HandlerProperty;
import com.alatka.connection.core.support.DefaultLoggerHandler;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

import java.util.Map;

/**
 * @author ybliu
 */
public class LoggerHandlerRegister extends HandlerComponentRegister<HandlerProperty> {

    private static final String KEY_LEVEL = "level";

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, HandlerProperty property) {
        Map<String, Object> params = property.getParams();
        String level = (String) params.get(KEY_LEVEL);
        builder.addConstructorArgValue(level);
    }

    @Override
    protected Class<DefaultLoggerHandler> beanClass() {
        return DefaultLoggerHandler.class;
    }

    @Override
    public HandlerProperty.Type mappingKey() {
        return HandlerProperty.Type.logger;
    }
}
