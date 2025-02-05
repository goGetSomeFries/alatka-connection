package com.alatka.connection.core.component.inbound;

import com.alatka.connection.core.config.DefaultConfig;
import com.alatka.connection.core.property.core.SourcePollingInboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.expression.Expression;
import org.springframework.integration.config.SourcePollingChannelAdapterFactoryBean;
import org.springframework.integration.context.IntegrationContextUtils;
import org.springframework.integration.endpoint.AbstractMessageSource;
import org.springframework.integration.expression.ValueExpression;

import java.util.HashMap;
import java.util.Map;

public abstract class SourcePollingInboundRegister<T extends SourcePollingInboundProperty> extends InboundComponentRegister<T> {

    @Override
    protected final void doRegister(BeanDefinitionBuilder builder, T property) {
        String pollerMetadata = property.getPoller() == null ? DefaultConfig.FALLBACK_POLLER_METADATA : property.getPoller();
        builder.addPropertyReference("pollerMetadata", pollerMetadata);
        builder.addPropertyReference("source", this.registerMessageSource(property));
    }

    @Override
    protected final Class<SourcePollingChannelAdapterFactoryBean> componentClass() {
        return SourcePollingChannelAdapterFactoryBean.class;
    }

    @Override
    protected void buildErrorChannel(BeanDefinitionBuilder builder, String errorChannelName) {
        // do nothing
    }

    private String registerMessageSource(T property) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(this.messageSourceClass());

        Map<String, Expression> headerExpressions = new HashMap<>();
        if (property.getHeaderExpressions() != null) {
            property.getHeaderExpressions().forEach((key, value) -> headerExpressions.put(key, new ValueExpression<>(value)));
        }
        if (property.getErrorChannel() != null) {
            headerExpressions.put(IntegrationContextUtils.ERROR_CHANNEL_BEAN_NAME, new ValueExpression<>(property.getErrorChannel()));
        }
        builder.addPropertyValue("headerExpressions", headerExpressions);

        this.doRegisterMessageSource(builder, property);

        String beanName = property.getId() + "." + this.beanNameSuffix() + ".messageSource";
        this.getBeanFactory().registerBeanDefinition(beanName, builder.getBeanDefinition());
        return beanName;
    }

    protected abstract void doRegisterMessageSource(BeanDefinitionBuilder builder, T property);

    protected abstract Class<? extends AbstractMessageSource<?>> messageSourceClass();
}