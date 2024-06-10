package com.alatka.connection.core.component.support;

import com.alatka.connection.core.component.AbstractComponentRegister;
import com.alatka.connection.core.property.PollerMetadataProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.PeriodicTrigger;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PollerMetadataRegister extends AbstractComponentRegister<PollerMetadataProperty> {

    private final Map<PollerMetadataProperty.Trigger, Function<PollerMetadataProperty, Trigger>> map = new HashMap<>();


    @Override
    protected BeanDefinitionBuilder doRegister(PollerMetadataProperty property) {
        return BeanDefinitionBuilder
                .genericBeanDefinition(beanClass(), PollerMetadata::new)
                .addPropertyReference("taskExecutor", property.getTaskExecutor())
                .addPropertyValue("maxMessagesPerPoll", property.getMaxMessagesPerPoll())
                .addPropertyValue("trigger", this.map.get(property.getTrigger()).apply(property));
    }

    @Override
    protected Class<PollerMetadata> beanClass() {
        return PollerMetadata.class;
    }

    @Override
    protected void init() {
        this.map.put(PollerMetadataProperty.Trigger.periodic, property -> {
            PeriodicTrigger trigger = new PeriodicTrigger(property.getPeriod());
            trigger.setFixedRate(property.getFixedRate());
            trigger.setInitialDelay(property.getInitialDelay());
            return trigger;
        });
    }
}
