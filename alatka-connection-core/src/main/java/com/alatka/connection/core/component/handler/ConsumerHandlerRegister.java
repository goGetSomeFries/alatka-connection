package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.property.core.ConsumerHandlerProperty;
import com.alatka.connection.core.support.ConsumerMessageHandler;
import com.alatka.connection.core.support.CustomMessageHandler;
import org.springframework.integration.config.ServiceActivatorFactoryBean;

/**
 * TODO
 *
 * @author ybliu
 * @see ConsumerMessageHandler
 */
public class ConsumerHandlerRegister extends MessageProcessorRegister<ConsumerHandlerProperty> {

    @Override
    protected Class<ConsumerMessageHandler> handlerClass() {
        return ConsumerMessageHandler.class;
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
    public Class<ConsumerHandlerProperty> mappingKey() {
        return ConsumerHandlerProperty.class;
    }
}
