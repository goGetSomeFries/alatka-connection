package com.alatka.connection.core.component.inbound;

import com.alatka.connection.core.config.DefaultConfig;
import com.alatka.connection.core.property.core.SourcePollingInboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.endpoint.SourcePollingChannelAdapter;
import org.springframework.integration.scheduling.PollerMetadata;

public abstract class SourcePollingInboundRegister<T extends SourcePollingInboundProperty> extends InboundComponentRegister<T> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, T property) {
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
        builder.addPropertyValue("source", this.messageSource(property));
    }

    @Override
    protected Class<SourcePollingChannelAdapter> componentClass() {
        return SourcePollingChannelAdapter.class;
    }

    protected abstract MessageSource messageSource(T property);
}
