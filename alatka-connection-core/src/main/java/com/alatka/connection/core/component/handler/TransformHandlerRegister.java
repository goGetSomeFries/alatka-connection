package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.property.core.TransformerHandlerProperty;
import com.alatka.connection.core.support.TransformMessageHandler;
import org.springframework.integration.config.TransformerFactoryBean;

/**
 * 转换器组件注册器
 *
 * @author ybliu
 * @see TransformMessageHandler
 * @see com.alatka.connection.core.model.HandlerModel#transformer
 */
@SuppressWarnings("rawtypes")
public class TransformHandlerRegister extends MessageProcessorRegister<TransformerHandlerProperty> {

    @Override
    protected Class<TransformMessageHandler> handlerClass() {
        return TransformMessageHandler.class;
    }

    @Override
    protected String handlerMethodName() {
        return TransformMessageHandler.METHOD_NAME;
    }

    @Override
    protected Class<TransformerFactoryBean> componentClass(TransformerHandlerProperty property) {
        return TransformerFactoryBean.class;
    }

    @Override
    public Class<TransformerHandlerProperty> mappingKey() {
        return TransformerHandlerProperty.class;
    }
}
