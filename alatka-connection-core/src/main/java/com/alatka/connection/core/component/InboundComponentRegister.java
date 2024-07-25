package com.alatka.connection.core.component;

import com.alatka.connection.core.property.InboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

public abstract class InboundComponentRegister<T extends InboundProperty> extends AbstractComponentRegister<T, Class<T>> {

    private static final String REQUEST_CHANNEL = "requestChannelName";
    private static final String REPLY_CHANNEL = "replyChannelName";

    @Override
    protected void postDoRegister(BeanDefinitionBuilder builder, InboundProperty property) {
        builder.addPropertyValue(REQUEST_CHANNEL, property.getOutputChannel());
        builder.addPropertyValue(REPLY_CHANNEL, property.getInputChannel());
    }
}
