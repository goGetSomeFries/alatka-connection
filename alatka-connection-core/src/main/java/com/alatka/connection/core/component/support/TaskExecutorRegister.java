package com.alatka.connection.core.component.support;

import com.alatka.connection.core.property.core.TaskExecutorProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * {@link ThreadPoolTaskExecutor}组件注册器
 *
 * @author ybliu
 * @see ThreadPoolTaskExecutor
 */
public class TaskExecutorRegister extends SupportComponentRegister<TaskExecutorProperty, Class<TaskExecutorProperty>> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, TaskExecutorProperty property) {
        builder.addPropertyValue("corePoolSize", property.getCorePoolSize())
                .addPropertyValue("maxPoolSize", property.getMaxPoolSize())
                .addPropertyValue("queueCapacity", property.getQueueCapacity())
                .addPropertyValue("keepAliveSeconds", property.getKeepAliveSeconds())
                .addPropertyValue("threadNamePrefix", property.getThreadNamePrefix());
    }

    @Override
    protected Class<ThreadPoolTaskExecutor> componentClass() {
        return ThreadPoolTaskExecutor.class;
    }

    @Override
    public Class<TaskExecutorProperty> mappingKey() {
        return TaskExecutorProperty.class;
    }
}
