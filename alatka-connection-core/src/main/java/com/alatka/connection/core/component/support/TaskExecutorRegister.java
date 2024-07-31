package com.alatka.connection.core.component.support;

import com.alatka.connection.core.property.support.TaskExecutorProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池BeanDefinition注册器
 *
 * @author ybliu
 */
public class TaskExecutorRegister extends SupportComponentRegister<TaskExecutorProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, TaskExecutorProperty property) {
        builder.addPropertyValue("corePoolSize", property.getCorePoolSize())
                .addPropertyValue("maxPoolSize", property.getMaxPoolSize())
                .addPropertyValue("queueCapacity", property.getQueueCapacity())
                .addPropertyValue("keepAliveSeconds", property.getKeepAliveSeconds())
                .addPropertyValue("threadNamePrefix", property.getThreadNamePrefix());
    }

    @Override
    protected Class<ThreadPoolTaskExecutor> beanClass() {
        return ThreadPoolTaskExecutor.class;
    }

    @Override
    public Class<TaskExecutorProperty> mappingKey() {
        return TaskExecutorProperty.class;
    }
}
