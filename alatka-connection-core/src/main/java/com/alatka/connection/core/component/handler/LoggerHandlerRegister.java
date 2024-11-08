package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.property.core.HandlerProperty;
import com.alatka.connection.core.support.DefaultLoggerHandler;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

import java.util.Map;

/**
 * TODO
 *
 * @author ybliu
 */
public class LoggerHandlerRegister extends HandlerComponentRegister<HandlerProperty> {

    private static final String KEY_LEVEL = "level";

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, HandlerProperty property) {
        Map<String, Object> params = property.getParams();
        String level = getParamsValueOrThrow(params, KEY_LEVEL);
        builder.addConstructorArgValue(level);
    }

    @Override
    protected Class<DefaultLoggerHandler> componentClass() {
        return DefaultLoggerHandler.class;
    }

    @Override
    public HandlerProperty.Type mappingKey() {
        return HandlerProperty.Type.logger;
    }
}
