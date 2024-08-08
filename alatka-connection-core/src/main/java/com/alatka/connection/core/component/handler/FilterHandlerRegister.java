package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.property.HandlerProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.filter.ExpressionEvaluatingSelector;
import org.springframework.integration.filter.MessageFilter;

import java.util.Map;

/**
 * @author ybliu
 */
public class FilterHandlerRegister extends HandlerComponentRegister<HandlerProperty> {

    private static final String KEY_EXPRESSION = "expression";

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, HandlerProperty property) {
        Map<String, Object> params = property.getParams();
        String expression = (String) params.get(KEY_EXPRESSION);
        ExpressionEvaluatingSelector selector = new ExpressionEvaluatingSelector(expression);

        builder.addConstructorArgValue(selector);
    }

    @Override
    protected Class<MessageFilter> beanClass() {
        return MessageFilter.class;
    }

    @Override
    public HandlerProperty.Type mappingKey() {
        return HandlerProperty.Type.filter;
    }
}
