package com.alatka.connection.core.component.consumer;

import com.alatka.connection.core.component.AbstractComponentRegister;
import com.alatka.connection.core.property.consumer.ConsumerProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.config.ConsumerEndpointFactoryBean;

/**
 * @author ybliu
 */
public class ConsumerEndpointRegister extends AbstractComponentRegister<ConsumerProperty, Class<ConsumerProperty>> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, ConsumerProperty property) {
        builder.addPropertyValue("inputChannelName", property.getInputChannel())
                .addPropertyReference("handler", property.getMessageHandler())
                .addPropertyReference("taskScheduler", property.getTaskScheduler())
                .addPropertyReference("pollerMetadata", property.getPollerMetadata());
    }

    @Override
    protected Class<ConsumerEndpointFactoryBean> beanClass() {
        return ConsumerEndpointFactoryBean.class;
    }

    @Override
    public Class<ConsumerProperty> mappingKey() {
        return ConsumerProperty.class;
    }
}
