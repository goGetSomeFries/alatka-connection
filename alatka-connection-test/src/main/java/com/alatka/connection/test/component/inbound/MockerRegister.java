package com.alatka.connection.test.component.inbound;

import com.alatka.connection.core.component.InboundComponentRegister;
import com.alatka.connection.core.property.test.MockerProperty;
import com.alatka.connection.core.util.ClassUtil;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.endpoint.MethodInvokingMessageSource;
import org.springframework.integration.endpoint.SourcePollingChannelAdapter;
import org.springframework.integration.scheduling.PollerMetadata;

/**
 * @author ybliu
 */
public class MockerRegister extends InboundComponentRegister<MockerProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, MockerProperty property) {
        Object instance = ClassUtil.newInstance(property.getClassName(), null, null);
        MethodInvokingMessageSource messageSource = new MethodInvokingMessageSource();
        messageSource.setObject(instance);
        messageSource.setMethodName(property.getMethodName());

        PollerMetadata pollerMetadata = this.getBeanFactory().getBean(property.getPoller(), PollerMetadata.class);

        builder.addPropertyValue("taskExecutor", pollerMetadata.getTaskExecutor());
        builder.addPropertyValue("trigger", pollerMetadata.getTrigger());
        builder.addPropertyValue("maxMessagesPerPoll", pollerMetadata.getMaxMessagesPerPoll());
        // TODO
        // pollerMetadata.getAdviceChain();
        // pollerMetadata.getErrorHandler();
        // pollerMetadata.getTransactionSynchronizationFactory();

        builder.addPropertyValue("source", messageSource);
        builder.addPropertyValue("outputChannelName", property.getOutputChannel());
    }

    @Override
    protected Class<SourcePollingChannelAdapter> beanClass() {
        return SourcePollingChannelAdapter.class;
    }

    @Override
    public Class<MockerProperty> propertyClass() {
        return MockerProperty.class;
    }
}
