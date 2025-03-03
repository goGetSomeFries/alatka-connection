package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.property.core.PassthroughHandlerProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.handler.BridgeHandler;

/**
 * 透传组件注册器
 *
 * @author ybliu
 * @see BridgeHandler
 * @see com.alatka.connection.core.model.HandlerModel#passthrough
 */
public class PassthroughHandlerRegister extends HandlerComponentRegister<PassthroughHandlerProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, PassthroughHandlerProperty property) {

    }

    @Override
    protected Class<BridgeHandler> componentClass(PassthroughHandlerProperty property) {
        return BridgeHandler.class;
    }

    @Override
    public Class<PassthroughHandlerProperty> mappingKey() {
        return PassthroughHandlerProperty.class;
    }
}
