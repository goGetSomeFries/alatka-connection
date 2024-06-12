package com.alatka.connection.core.component.support;

import com.alatka.connection.config.property.TaskSchedulerProperty;
import com.alatka.connection.core.component.AbstractComponentRegister;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author ybliu
 */
public class TaskSchedulerRegister extends AbstractComponentRegister<TaskSchedulerProperty> {

    @Override
    protected BeanDefinitionBuilder doRegister(TaskSchedulerProperty property) {
        return BeanDefinitionBuilder
                .genericBeanDefinition(beanClass(), ThreadPoolTaskScheduler::new)
                .addPropertyValue("poolSize", property.getPoolSize())
                .addPropertyValue("threadNamePrefix", property.getThreadNamePrefix());
    }

    @Override
    protected Class<ThreadPoolTaskScheduler> beanClass() {
        return ThreadPoolTaskScheduler.class;
    }
}
