package com.alatka.connection.core.component.consumer;

import com.alatka.connection.core.component.AbstractComponentRegister;
import com.alatka.connection.core.property.core.ConsumerProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.config.ConsumerEndpointFactoryBean;

/**
 * @author ybliu
 */
public class ConsumerEndpointRegister extends AbstractComponentRegister<ConsumerProperty, Class<ConsumerProperty>> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, ConsumerProperty property) {
        builder.addPropertyValue("inputChannelName", property.getInputChannel());
        builder.addPropertyReference("handler", property.getMessageHandler());
        if (property.getTaskScheduler() != null) {
            builder.addPropertyReference("taskScheduler", property.getTaskScheduler());
        }
        if (property.getPollerMetadata() != null) {
            builder.addPropertyReference("pollerMetadata", property.getPollerMetadata());
        }
    }

    @Override
    protected Class<ConsumerEndpointFactoryBean> componentClass() {
        return ConsumerEndpointFactoryBean.class;
    }

    @Override
    public Class<ConsumerProperty> mappingKey() {
        return ConsumerProperty.class;
    }
}
