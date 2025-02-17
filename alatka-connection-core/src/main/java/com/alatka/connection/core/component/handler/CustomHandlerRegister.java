package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.property.core.CustomHandlerProperty;
import com.alatka.connection.core.support.CustomMessageHandler;
import org.springframework.integration.config.ServiceActivatorFactoryBean;

/**
 * TODO
 *
 * @author ybliu
 * @see CustomMessageHandler
 */
public class CustomHandlerRegister extends MessageProcessorRegister<CustomHandlerProperty> {

    @Override
    protected Class<CustomMessageHandler> handlerClass() {
        return CustomMessageHandler.class;
    }

    @Override
    protected String handlerMethodName() {
        return CustomMessageHandler.METHOD_NAME;
    }

    @Override
    protected Class<ServiceActivatorFactoryBean> componentClass(CustomHandlerProperty property) {
        return ServiceActivatorFactoryBean.class;
    }

    @Override
    public Class<CustomHandlerProperty> mappingKey() {
        return CustomHandlerProperty.class;
    }
}
