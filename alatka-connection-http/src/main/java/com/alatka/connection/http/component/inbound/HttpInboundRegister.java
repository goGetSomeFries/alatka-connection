package com.alatka.connection.http.component.inbound;

import com.alatka.connection.core.component.inbound.InboundComponentRegister;
import com.alatka.connection.core.model.InboundModel;
import com.alatka.connection.core.property.http.HttpInboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.http.HttpMethod;
import org.springframework.integration.http.inbound.HttpRequestHandlingMessagingGateway;
import org.springframework.integration.http.inbound.RequestMapping;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ybliu
 */
public class HttpInboundRegister extends InboundComponentRegister<HttpInboundProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, HttpInboundProperty property) {
        RequestMapping requestMapping = new RequestMapping();
        requestMapping.setPathPatterns(property.getPaths());
        if (property.getMethods() != null) {
            HttpMethod[] methods = Stream.of(property.getMethods()).map(HttpMethod::resolve).toArray(HttpMethod[]::new);
            requestMapping.setMethods(methods);
        }

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        builder.addPropertyValue("requestMapping", requestMapping)
                .addPropertyValue("validator", new SpringValidatorAdapter(validator));
        if (property.getRequestType() != null) {
            builder.addPropertyValue("requestPayloadTypeClass", property.getRequestType());
        }
        if (property.getHeaderExpressions() != null) {
            Map<String, Expression> expressions = property.getHeaderExpressions().entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, entry -> new SpelExpressionParser().parseExpression(entry.getValue())));
            builder.addPropertyValue("headerExpressions", expressions);
        }
    }

    @Override
    protected Class<HttpRequestHandlingMessagingGateway> componentClass(HttpInboundProperty property) {
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
