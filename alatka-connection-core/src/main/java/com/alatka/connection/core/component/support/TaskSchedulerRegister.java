package com.alatka.connection.core.component.support;

import com.alatka.connection.core.property.core.TaskSchedulerProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * {@link ThreadPoolTaskScheduler}组件注册器
 *
 * @author ybliu
 * @see ThreadPoolTaskScheduler
 */
public class TaskSchedulerRegister extends SupportComponentRegister<TaskSchedulerProperty, Class<TaskSchedulerProperty>> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, TaskSchedulerProperty property) {
        builder.addPropertyValue("poolSize", property.getPoolSize())
                .addPropertyValue("threadNamePrefix", property.getThreadNamePrefix());
    }

    @Override
    protected Class<ThreadPoolTaskScheduler> componentClass() {
        return ThreadPoolTaskScheduler.class;
    }

    @Override
    public Class<TaskSchedulerProperty> mappingKey() {
        return TaskSchedulerProperty.class;
    }
}
