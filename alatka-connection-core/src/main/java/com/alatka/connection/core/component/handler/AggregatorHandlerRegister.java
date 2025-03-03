package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.property.core.AggregatorHandlerProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.aggregator.AggregatingMessageHandler;

/**
 * 聚合组件注册器
 *
 * @author ybliu
 * @see AggregatingMessageHandler
 * @see com.alatka.connection.core.model.HandlerModel#aggregator
 */
public class AggregatorHandlerRegister extends HandlerComponentRegister<AggregatorHandlerProperty> {

    @Override
    public void doRegister(BeanDefinitionBuilder builder, AggregatorHandlerProperty property) {
        builder.addConstructorArgReference(property.getMessageGroupProcessor());

        if (property.getMessageGroupStore() != null) {
            builder.addPropertyReference("messageStore", property.getMessageGroupStore());
        }
        if (property.getCorrelationStrategy() != null) {
            builder.addPropertyReference("correlationStrategy", property.getCorrelationStrategy());
        }
        if (property.getReleaseStrategy() != null) {
            builder.addPropertyReference("releaseStrategy", property.getReleaseStrategy());
        }
        if (property.getLockRegistry() != null) {
            builder.addPropertyReference("lockRegistry", property.getLockRegistry());
        }
        if (property.getDiscardChannel() != null) {
            builder.addPropertyValue("discardChannelName", property.getDiscardChannel());
        }
        builder.addPropertyValue("releaseLockBeforeSend", property.isReleaseLockBeforeSend());
        builder.addPropertyValue("expireGroupsUponCompletion", property.isExpireGroupsUponCompletion());
    }

    @Override
    protected Class<AggregatingMessageHandler> componentClass(AggregatorHandlerProperty property) {
        return AggregatingMessageHandler.class;
    }

    @Override
    public Class<AggregatorHandlerProperty> mappingKey() {
        return AggregatorHandlerProperty.class;
    }
}
