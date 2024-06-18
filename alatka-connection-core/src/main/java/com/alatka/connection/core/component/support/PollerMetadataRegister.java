package com.alatka.connection.core.component.support;

import com.alatka.connection.core.component.AbstractComponentRegister;
import com.alatka.connection.core.property.support.PollerMetadataProperty;
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
public class PollerMetadataRegister extends AbstractComponentRegister<PollerMetadataProperty> {

    private Map<PollerMetadataProperty.Trigger.Type, Function<? extends PollerMetadataProperty.Trigger, Trigger>> map = new HashMap<>();


    @Override
    protected void doRegister(BeanDefinitionBuilder builder, PollerMetadataProperty property) {
        builder.addPropertyReference("taskExecutor", property.getTaskExecutor())
                .addPropertyValue("maxMessagesPerPoll", property.getMaxMessagesPerPoll());
//                .addPropertyValue("trigger", this.map.get(property.).apply(property));
    }

    @Override
    protected Class<PollerMetadata> beanClass() {
        return PollerMetadata.class;
    }

    @Override
    protected void init() {
        this.map.put(PollerMetadataProperty.Trigger.Type.periodic, (PollerMetadataProperty.Periodic property) -> {
            PeriodicTrigger trigger = new PeriodicTrigger(property.getPeriod());
            trigger.setFixedRate(property.getFixedRate());
            trigger.setInitialDelay(property.getInitialDelay());
            return trigger;
        });
        this.map.put(PollerMetadataProperty.Trigger.Type.cron, (PollerMetadataProperty.Cron property) -> {
            CronTrigger trigger = new CronTrigger(property.getExpression());
            return trigger;
        });
    }
}
