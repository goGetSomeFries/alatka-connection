package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.component.HandlerComponentRegister;
import com.alatka.connection.core.property.HandlerProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.handler.BridgeHandler;

public class BridgeHandlerRegister extends HandlerComponentRegister<HandlerProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, HandlerProperty property) {

    }

    @Override
    protected Class<BridgeHandler> beanClass() {
        return BridgeHandler.class;
    }

    @Override
    public HandlerProperty.Type mappingKey() {
        return HandlerProperty.Type.passthrough;
    }
}
