package com.alatka.connection.test.component.inbound;

import com.alatka.connection.core.component.inbound.InboundComponentRegister;
import com.alatka.connection.core.config.DefaultConfig;
import com.alatka.connection.core.model.InboundModel;
import com.alatka.connection.core.property.test.MockerInboundProperty;
import com.alatka.connection.core.util.ClassUtil;
import com.alatka.connection.test.support.InboundMocker;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.endpoint.MethodInvokingMessageSource;
import org.springframework.integration.endpoint.SourcePollingChannelAdapter;
import org.springframework.integration.scheduling.PollerMetadata;

/**
 * TODO
 *
 * @author ybliu
 * @see InboundMocker#mockInbound()
 */
public class MockerInboundRegister extends InboundComponentRegister<MockerInboundProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, MockerInboundProperty property) {
        Object instance = ClassUtil.newInstance(property.getClassName());
        if (!InboundMocker.class.isAssignableFrom(instance.getClass())) {
            throw new IllegalArgumentException(instance.getClass().getName() + " must be an instance of " + InboundMocker.class.getName());
        }
        MethodInvokingMessageSource messageSource = new MethodInvokingMessageSource();
        messageSource.setObject(instance);
        messageSource.setMethodName(InboundMocker.METHOD_NAME);
        messageSource.setBeanFactory(this.getBeanFactory());
        messageSource.afterPropertiesSet();

        builder.addPropertyValue("source", messageSource);

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

    }

    @Override
    protected Class<SourcePollingChannelAdapter> componentClass() {
        return SourcePollingChannelAdapter.class;
    }

    @Override
    public Class<MockerInboundProperty> mappingKey() {
        return MockerInboundProperty.class;
    }

    @Override
    protected String beanNameSuffix() {
        return InboundModel.mocker.name();
    }
}
