package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.property.core.HandlerProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.filter.ExpressionEvaluatingSelector;
import org.springframework.integration.filter.MessageFilter;

/**
 * TODO
 *
 * @author ybliu
 */
public class FilterHandlerRegister extends HandlerComponentRegister<HandlerProperty> {

    private static final String KEY_EXPRESSION = "expression";

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, HandlerProperty property) {
        String expression = this.getParamsValueOrThrow(property.getParams(), KEY_EXPRESSION);
        ExpressionEvaluatingSelector selector = new ExpressionEvaluatingSelector(expression);

        builder.addConstructorArgValue(selector);
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
