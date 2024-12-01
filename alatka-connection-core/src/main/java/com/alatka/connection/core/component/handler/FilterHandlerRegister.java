package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.AlatkaConnectionConstant;
import com.alatka.connection.core.property.core.HandlerProperty;
import com.alatka.connection.core.support.FilterMessageHandler;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.config.FilterFactoryBean;

/**
 * TODO
 *
 * @author ybliu
 */
public class FilterHandlerRegister extends MessageProcessorHandlerRegister {

    @Override
    protected void postDoRegister(BeanDefinitionBuilder builder, HandlerProperty property) {
        builder.addPropertyReference("discardChannel", property.identity() + AlatkaConnectionConstant.OUTBOUND_OUTPUT_CHANNEL);
    }

    @Override
    protected Class<FilterMessageHandler> handlerClass() {
        return FilterMessageHandler.class;
    }

    @Override
    protected String handlerMethodName() {
        return FilterMessageHandler.METHOD_NAME;
    }

    @Override
    protected Class<FilterFactoryBean> componentClass() {
        return FilterFactoryBean.class;
    }

    @Override
    public HandlerProperty.Type mappingKey() {
        return HandlerProperty.Type.filter;
    }
}
