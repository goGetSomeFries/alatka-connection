package com.alatka.connection.core.component.inbound;

import com.alatka.connection.core.component.AbstractComponentRegister;
import com.alatka.connection.core.property.core.InboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

import java.util.Optional;

/**
 * Inbound组件注册器
 *
 * @author ybliu
 */
public abstract class InboundComponentRegister<T extends InboundProperty> extends AbstractComponentRegister<T, Class<T>> {

    @Override
    protected void postDoRegister(BeanDefinitionBuilder builder, InboundProperty property) {
        if (property.getInputChannel() == null) {
            builder.addPropertyValue("outputChannelName", property.getOutputChannel());
            builder.addPropertyValue("sendTimeout", Optional.ofNullable(property.getSendTimeout()).orElse(-1L));
        } else {
            builder.addPropertyValue("requestChannelName", property.getOutputChannel());
            builder.addPropertyValue("replyChannelName", property.getInputChannel());
            builder.addPropertyValue("requestTimeout", Optional.ofNullable(property.getSendTimeout()).orElse(-1L));
            builder.addPropertyValue("replyTimeout", Optional.ofNullable(property.getReceiveTimeout()).orElse(-1L));
        }

        this.buildErrorChannel(builder, property.getErrorChannel());
    }

    protected void buildErrorChannel(BeanDefinitionBuilder builder, String errorChannelName) {
        if (errorChannelName != null) {
            builder.addPropertyValue("errorChannelName", errorChannelName);
        }
    }

}
