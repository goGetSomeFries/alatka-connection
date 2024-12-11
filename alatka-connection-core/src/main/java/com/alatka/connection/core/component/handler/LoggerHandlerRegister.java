package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.extend.DefaultLoggerHandler;
import com.alatka.connection.core.property.core.LoggerHandlerProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

/**
 * TODO
 *
 * @author ybliu
 */
public class LoggerHandlerRegister extends HandlerComponentRegister<LoggerHandlerProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, LoggerHandlerProperty property) {
        builder.addConstructorArgValue(property.getLevel());
    }

    @Override
    protected Class<DefaultLoggerHandler> componentClass() {
        return DefaultLoggerHandler.class;
    }

    @Override
    public Class<LoggerHandlerProperty> mappingKey() {
        return LoggerHandlerProperty.class;
    }
}
