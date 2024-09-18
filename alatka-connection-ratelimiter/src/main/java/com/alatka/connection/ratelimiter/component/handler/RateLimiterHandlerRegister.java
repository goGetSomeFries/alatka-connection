package com.alatka.connection.ratelimiter.component.handler;

import com.alatka.connection.core.component.handler.HandlerComponentRegister;
import com.alatka.connection.core.property.core.HandlerProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.handler.MessageProcessor;
import org.springframework.integration.handler.ServiceActivatingHandler;

/**
 * TODO
 *
 * @author ybliu
 */
public class RateLimiterHandlerRegister extends HandlerComponentRegister<HandlerProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, HandlerProperty property) {
        MessageProcessor<Object> messageProcessor = message -> null;
        builder.addConstructorArgValue(messageProcessor);
    }

    @Override
    protected Class<ServiceActivatingHandler> componentClass() {
        return ServiceActivatingHandler.class;
    }

    @Override
    public HandlerProperty.Type mappingKey() {
        return HandlerProperty.Type.rateLimiter;
    }
}
