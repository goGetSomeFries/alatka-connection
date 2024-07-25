package com.alatka.connection.http.component.inbound;

import com.alatka.connection.core.component.InboundComponentRegister;
import com.alatka.connection.core.property.http.HttpInboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.http.inbound.HttpRequestHandlingMessagingGateway;

/**
 * @author ybliu
 */
public class HttpInboundRegister extends InboundComponentRegister<HttpInboundProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, HttpInboundProperty property) {
    }

    @Override
    protected Class<HttpRequestHandlingMessagingGateway> beanClass() {
        return HttpRequestHandlingMessagingGateway.class;
    }

    @Override
    public Class<HttpInboundProperty> reference() {
        return HttpInboundProperty.class;
    }
}
