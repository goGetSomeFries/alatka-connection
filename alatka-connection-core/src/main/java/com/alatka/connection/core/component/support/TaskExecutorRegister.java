package com.alatka.connection.core.component.support;

import com.alatka.connection.core.property.TaskExecutorProperty;
import com.alatka.connection.core.component.AbstractComponentRegister;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池BeanDefinition注册器
 *
 * @author ybliu
 */
public class TaskExecutorRegister extends AbstractComponentRegister<TaskExecutorProperty> {

    @Override
    protected BeanDefinitionBuilder doRegister(TaskExecutorProperty property) {
        return BeanDefinitionBuilder
                .genericBeanDefinition(beanClass(), ThreadPoolTaskExecutor::new)
                .addPropertyValue("corePoolSize", property.getCorePoolSize())
                .addPropertyValue("maxPoolSize", property.getMaxPoolSize())
                .addPropertyValue("queueCapacity", property.getQueueCapacity())
                .addPropertyValue("keepAliveSeconds", property.getKeepAliveSeconds())
                .addPropertyValue("threadNamePrefix", property.getThreadNamePrefix());
    }

    @Override
    protected Class<ThreadPoolTaskExecutor> beanClass() {
        return ThreadPoolTaskExecutor.class;
    }
}
