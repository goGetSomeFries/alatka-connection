package com.alatka.connection.http.component.inbound;

import com.alatka.connection.core.component.InboundComponentRegister;
import com.alatka.connection.core.property.http.HttpInboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpMethod;
import org.springframework.integration.http.inbound.HttpRequestHandlingMessagingGateway;
import org.springframework.integration.http.inbound.RequestMapping;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import javax.validation.Validation;

/**
 * @author ybliu
 */
public class HttpInboundRegister extends InboundComponentRegister<HttpInboundProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, HttpInboundProperty property) {
        RequestMapping requestMapping = new RequestMapping();
        requestMapping.setPathPatterns(property.getPathPatterns());
        requestMapping.setMethods(HttpMethod.resolve(property.getMethod()));

        builder.addPropertyValue("requestMapping", requestMapping)
                .addPropertyValue("requestPayloadType", ResolvableType.forClass(property.getRequestType()))
                .addPropertyValue("validator", new SpringValidatorAdapter(Validation.buildDefaultValidatorFactory().getValidator()));
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
