package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.property.core.HandlerProperty;
import com.alatka.connection.core.support.FilterMessageHandler;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.filter.ExpressionEvaluatingSelector;
import org.springframework.integration.filter.MessageFilter;

/**
 * TODO
 *
 * @author ybliu
 */
public class FilterHandlerRegister extends MessageProcessorHandlerRegister {

    private static final String KEY_EXPRESSION = "expression";

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, HandlerProperty property) {
        String expression = this.getParamsValue(property.getParams(), KEY_EXPRESSION);
        if (expression != null) {
            ExpressionEvaluatingSelector selector = new ExpressionEvaluatingSelector(expression);
            builder.addConstructorArgValue(selector);
        } else {
            super.doRegister(builder, property);
        }
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
    protected Class<MessageFilter> componentClass() {
        return MessageFilter.class;
    }

    @Override
    public HandlerProperty.Type mappingKey() {
        return HandlerProperty.Type.filter;
    }
}
