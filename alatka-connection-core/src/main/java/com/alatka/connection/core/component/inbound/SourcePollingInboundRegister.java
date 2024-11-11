package com.alatka.connection.core.component.inbound;

import com.alatka.connection.core.config.DefaultConfig;
import com.alatka.connection.core.property.core.SourcePollingInboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.endpoint.SourcePollingChannelAdapter;
import org.springframework.integration.scheduling.PollerMetadata;

public abstract class SourcePollingInboundRegister<T extends SourcePollingInboundProperty> extends InboundComponentRegister<T> {

    @Override
    protected final void doRegister(BeanDefinitionBuilder builder, T property) {
        String pollerBeanName = property.getPoller() == null ? DefaultConfig.FALLBACK_POLLER_METADATA : property.getPoller();
        PollerMetadata pollerMetadata = this.getBeanFactory().getBean(pollerBeanName, PollerMetadata.class);

        String taskSchedulerBeanName = property.getTaskScheduler() == null ? DefaultConfig.FALLBACK_TASK_SCHEDULER : property.getTaskScheduler();
        builder.addPropertyReference("taskScheduler", taskSchedulerBeanName);
        builder.addPropertyValue("taskExecutor", pollerMetadata.getTaskExecutor());
        builder.addPropertyValue("trigger", pollerMetadata.getTrigger());
        builder.addPropertyValue("maxMessagesPerPoll", pollerMetadata.getMaxMessagesPerPoll());
        // TODO
        // pollerMetadata.getAdviceChain();
        // pollerMetadata.getErrorHandler();
        // pollerMetadata.getTransactionSynchronizationFactory();
        builder.addPropertyReference("source", this.registerMessageSource(property));
    }

    @Override
    protected final Class<SourcePollingChannelAdapter> componentClass() {
        return SourcePollingChannelAdapter.class;
    }

    private String registerMessageSource(T property) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(this.messageSourceClass());
        this.doRegisterMessageSource(builder, property);
        String beanName = property.getId() + "." + this.beanNameSuffix() + ".messageSource";
        this.getBeanFactory().registerBeanDefinition(beanName, builder.getBeanDefinition());
        return beanName;
    }

    protected abstract void doRegisterMessageSource(BeanDefinitionBuilder builder, T property);

    protected abstract Class<?> messageSourceClass();
}