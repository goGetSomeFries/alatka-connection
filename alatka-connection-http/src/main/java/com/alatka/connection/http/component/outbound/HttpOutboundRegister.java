package com.alatka.connection.http.component.outbound;

import com.alatka.connection.core.component.outbound.OutboundComponentRegister;
import com.alatka.connection.core.property.http.HttpOutboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.expression.ValueExpression;
import org.springframework.integration.http.outbound.HttpRequestExecutingMessageHandler;

/**
 * {@link HttpRequestExecutingMessageHandler}组件注册器
 *
 * @author ybliu
 * @see HttpRequestExecutingMessageHandler
 * @see com.alatka.connection.core.model.OutboundModel#http
 */
public class HttpOutboundRegister extends OutboundComponentRegister<HttpOutboundProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, HttpOutboundProperty property) {
        builder.addConstructorArgValue(property.getUrl())
                .addPropertyValue("httpMethodExpression", new ValueExpression<>(property.getMethod()));
        if (property.getResponseType() != null) {
            builder.addPropertyValue("expectedResponseType", property.getResponseType());
        }
    }

    @Override
    protected Class<HttpRequestExecutingMessageHandler> componentClass(HttpOutboundProperty property) {
        return HttpRequestExecutingMessageHandler.class;
    }

    @Override
    public Class<HttpOutboundProperty> mappingKey() {
        return HttpOutboundProperty.class;
    }

}
