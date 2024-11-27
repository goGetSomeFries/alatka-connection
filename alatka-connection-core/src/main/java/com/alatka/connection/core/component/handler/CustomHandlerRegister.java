package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.property.core.HandlerProperty;
import com.alatka.connection.core.support.CustomMessageHandler;
import org.springframework.integration.handler.ServiceActivatingHandler;

/**
 * TODO
 *
 * @author ybliu
 * @see CustomMessageHandler
 */
public class CustomHandlerRegister extends MessageProcessorHandlerRegister {

    @Override
    protected Class<?> handlerClass() {
        return CustomMessageHandler.class;
    }

    @Override
    protected String handlerMethodName() {
        return CustomMessageHandler.METHOD_NAME;
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
