package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.property.core.HandlerProperty;
import com.alatka.connection.core.support.CustomMessageHandler;
import org.springframework.integration.config.ServiceActivatorFactoryBean;

/**
 * TODO
 *
 * @author ybliu
 * @see CustomMessageHandler
 */
public class CustomHandlerRegister extends MessageProcessorHandlerRegister {

    @Override
    protected Class<CustomMessageHandler> handlerClass() {
        return CustomMessageHandler.class;
    }

    @Override
    protected String handlerMethodName() {
        return CustomMessageHandler.METHOD_NAME;
    }

    @Override
    protected Class<ServiceActivatorFactoryBean> componentClass() {
        return ServiceActivatorFactoryBean.class;
    }

    @Override
    public HandlerProperty.Type mappingKey() {
        return HandlerProperty.Type.custom;
    }
}
