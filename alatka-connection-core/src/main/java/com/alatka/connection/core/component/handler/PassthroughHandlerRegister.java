package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.property.core.HandlerProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.handler.BridgeHandler;

/**
 * TODO
 *
 * @author ybliu
 */
public class PassthroughHandlerRegister extends HandlerComponentRegister<HandlerProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, HandlerProperty property) {

    }

    @Override
    protected Class<BridgeHandler> componentClass() {
        return BridgeHandler.class;
    }

    @Override
    public HandlerProperty.Type mappingKey() {
        return HandlerProperty.Type.passthrough;
    }
}
