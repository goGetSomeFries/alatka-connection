package com.alatka.connection.core.component.support;

import com.alatka.connection.core.component.SupportComponentRegister;
import com.alatka.connection.core.property.support.PollerMetadataProperty;
import com.alatka.connection.core.util.JsonUtil;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.PeriodicTrigger;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author ybliu
 */
public class PollerMetadataRegister extends SupportComponentRegister<PollerMetadataProperty> {

    private Map<PollerMetadataProperty.Trigger.Type, Function<Object, Trigger>> mapping;

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, PollerMetadataProperty property) {
        builder.addPropertyReference("taskExecutor", property.getTaskExecutor())
                .addPropertyValue("maxMessagesPerPoll", property.getMaxMessagesPerPoll());

        property.getTrigger().entrySet()
                .stream()
                .findFirst()
                .ifPresent(entry -> builder.addPropertyValue("trigger", this.mapping.get(entry.getKey()).apply(entry.getValue())));
    }

    @Override
    protected Class<PollerMetadata> beanClass() {
        return PollerMetadata.class;
    }

    @Override
    protected void init() {
        this.mapping = new HashMap<>(2);

        this.mapping.put(PollerMetadataProperty.Trigger.Type.periodic, object -> {
            PollerMetadataProperty.Periodic property = JsonUtil.convertToObject(object, PollerMetadataProperty.Periodic.class);
            PeriodicTrigger trigger = new PeriodicTrigger(property.getPeriod());
            trigger.setFixedRate(property.getFixedRate());
            trigger.setInitialDelay(property.getInitialDelay());
            return trigger;
        });
        this.mapping.put(PollerMetadataProperty.Trigger.Type.cron, object -> {
            PollerMetadataProperty.Cron property = JsonUtil.convertToObject(object, PollerMetadataProperty.Cron.class);
            CronTrigger trigger = new CronTrigger(property.getExpression());
            return trigger;
        });
    }

    @Override
    public Class<PollerMetadataProperty> propertyClass() {
        return PollerMetadataProperty.class;
    }
}
