package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.property.core.ConsumerHandlerProperty;
import com.alatka.connection.core.support.ConsumerMessageHandler;
import com.alatka.connection.core.support.CustomMessageHandler;
import org.springframework.integration.config.ServiceActivatorFactoryBean;

/**
 * consumer组件注册器
 *
 * @author ybliu
 * @see ConsumerMessageHandler
 * @see com.alatka.connection.core.model.HandlerModel#consumer
 */
@SuppressWarnings("rawtypes")
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
    protected Class<ServiceActivatorFactoryBean> componentClass(ConsumerHandlerProperty property) {
        return ServiceActivatorFactoryBean.class;
    }

    @Override
    public Class<ConsumerHandlerProperty> mappingKey() {
        return ConsumerHandlerProperty.class;
    }
}
