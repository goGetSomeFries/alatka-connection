package com.alatka.connection.core.component.support;

import com.alatka.connection.core.property.core.PollerMetadataProperty;
import com.alatka.connection.core.property.core.TriggerProperty;
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
 * {@link PollerMetadata}组件注册器
 *
 * @author ybliu
 * @see PollerMetadata
 */
public class PollerMetadataRegister extends SupportComponentRegister<PollerMetadataProperty> {

    private Map<TriggerProperty.Type, Function<Object, Trigger>> mapping;

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, PollerMetadataProperty property) {
        builder.addPropertyReference("taskExecutor", property.getTaskExecutor())
                .addPropertyValue("maxMessagesPerPoll", property.getMaxMessagesPerPoll());

        TriggerProperty.Type type = property.getTrigger().getType();
        Object params = JsonUtil.convertToObject(property.getTrigger().getParams(), type.getClazz());
        Trigger trigger = this.mapping.get(type).apply(params);
        builder.addPropertyValue("trigger", trigger);
    }

    @Override
    protected Class<PollerMetadata> componentClass(PollerMetadataProperty property) {
        return PollerMetadata.class;
    }

    @Override
    protected void initialize() {
        this.mapping = new HashMap<>(2);

        this.mapping.put(TriggerProperty.Type.periodic, object -> {
            TriggerProperty.PeriodicProperty property = JsonUtil.convertToObject(object, TriggerProperty.PeriodicProperty.class);
            PeriodicTrigger trigger = new PeriodicTrigger(property.getPeriod());
            trigger.setFixedRate(property.getFixedRate());
            trigger.setInitialDelay(property.getInitialDelay());
            return trigger;
        });
        this.mapping.put(TriggerProperty.Type.cron, object -> {
            TriggerProperty.CronProperty property = JsonUtil.convertToObject(object, TriggerProperty.CronProperty.class);
            return new CronTrigger(property.getExpression());
        });
    }

    @Override
    public Class<PollerMetadataProperty> mappingKey() {
        return PollerMetadataProperty.class;
    }
}
