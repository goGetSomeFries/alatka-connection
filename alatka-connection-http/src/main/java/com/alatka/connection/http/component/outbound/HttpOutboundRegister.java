package com.alatka.connection.http.component.outbound;

import com.alatka.connection.core.component.OutboundComponentRegister;
import com.alatka.connection.core.property.http.HttpOutboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.expression.ValueExpression;
import org.springframework.integration.http.outbound.HttpRequestExecutingMessageHandler;

/**
 * @author ybliu
 */
public class HttpOutboundRegister extends OutboundComponentRegister<HttpOutboundProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, HttpOutboundProperty property) {
        builder.addConstructorArgValue(property.getUrl())
                .addPropertyValue("httpMethodExpression", new ValueExpression<>(property.getMethod()))
                .addPropertyValue("expectedResponseType", property.getResponseType());
    }

    @Override
    protected Class<HttpRequestExecutingMessageHandler> beanClass() {
        return HttpRequestExecutingMessageHandler.class;
    }

    @Override
    public Class<HttpOutboundProperty> reference() {
        return HttpOutboundProperty.class;
    }
}