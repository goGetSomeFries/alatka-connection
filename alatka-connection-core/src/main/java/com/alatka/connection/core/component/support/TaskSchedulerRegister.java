package com.alatka.connection.core.component.support;

import com.alatka.connection.core.property.support.TaskSchedulerProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author ybliu
 */
public class TaskSchedulerRegister extends SupportComponentRegister<TaskSchedulerProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, TaskSchedulerProperty property) {
        builder.addPropertyValue("poolSize", property.getPoolSize())
                .addPropertyValue("threadNamePrefix", property.getThreadNamePrefix());
    }

    @Override
    protected Class<ThreadPoolTaskScheduler> beanClass() {
        return ThreadPoolTaskScheduler.class;
    }

    @Override
    public Class<TaskSchedulerProperty> propertyClass() {
        return TaskSchedulerProperty.class;
    }
}
