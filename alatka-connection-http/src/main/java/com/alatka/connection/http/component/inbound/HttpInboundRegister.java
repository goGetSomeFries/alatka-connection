package com.alatka.connection.http.component.inbound;

import com.alatka.connection.core.component.inbound.InboundComponentRegister;
import com.alatka.connection.core.model.InboundModel;
import com.alatka.connection.core.property.http.HttpInboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.integration.http.inbound.HttpRequestHandlingMessagingGateway;
import org.springframework.integration.http.inbound.RequestMapping;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import javax.validation.Validation;
import java.util.stream.Stream;

/**
 * @author ybliu
 */
public class HttpInboundRegister extends InboundComponentRegister<HttpInboundProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, HttpInboundProperty property) {
        RequestMapping requestMapping = new RequestMapping();
        requestMapping.setPathPatterns(property.getPathPatterns());
        if (property.getMethods() != null) {
            Stream.of(property.getMethods()).map(HttpMethod::resolve).forEach(requestMapping::setMethods);
        }

        builder.addPropertyValue("requestMapping", requestMapping)
                .addPropertyValue("validator", new SpringValidatorAdapter(Validation.buildDefaultValidatorFactory().getValidator()));
        if (property.getRequestType() != null) {
            builder.addPropertyValue("requestPayloadTypeClass", property.getRequestType());
        }
    }

    @Override
    protected Class<HttpRequestHandlingMessagingGateway> beanClass() {
        return HttpRequestHandlingMessagingGateway.class;
    }

    @Override
    public Class<HttpInboundProperty> mappingKey() {
        return HttpInboundProperty.class;
    }

    @Override
    protected String beanNameSuffix() {
        return InboundModel.http.name();
    }
}
