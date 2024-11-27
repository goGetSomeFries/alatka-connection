package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.property.core.HandlerProperty;
import com.alatka.connection.core.support.TransformMessageHandler;
import org.springframework.integration.transformer.MessageTransformingHandler;

/**
 * TODO
 *
 * @author ybliu
 * @see TransformMessageHandler
 */
public class TransformerHandlerRegister extends MessageProcessorHandlerRegister {

    @Override
    protected Class<?> handlerClass() {
        return TransformMessageHandler.class;
    }

    @Override
    protected String handlerMethodName() {
        return TransformMessageHandler.METHOD_NAME;
    }

    @Override
    protected Class<MessageTransformingHandler> componentClass() {
        return MessageTransformingHandler.class;
    }

    @Override
    public HandlerProperty.Type mappingKey() {
        return HandlerProperty.Type.transformer;
    }
}