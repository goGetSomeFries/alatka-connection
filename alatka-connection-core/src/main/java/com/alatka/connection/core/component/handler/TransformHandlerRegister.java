package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.property.core.HandlerProperty;
import com.alatka.connection.core.support.TransformMessageHandler;
import org.springframework.integration.config.TransformerFactoryBean;

/**
 * TODO
 *
 * @author ybliu
 * @see TransformMessageHandler
 */
public class TransformHandlerRegister extends MessageProcessorHandlerRegister {

    @Override
    protected Class<TransformMessageHandler> handlerClass() {
        return TransformMessageHandler.class;
    }

    @Override
    protected String handlerMethodName() {
        return TransformMessageHandler.METHOD_NAME;
    }

    @Override
    protected Class<TransformerFactoryBean> componentClass() {
        return TransformerFactoryBean.class;
    }

    @Override
    public HandlerProperty.Type mappingKey() {
        return HandlerProperty.Type.transformer;
    }
}
