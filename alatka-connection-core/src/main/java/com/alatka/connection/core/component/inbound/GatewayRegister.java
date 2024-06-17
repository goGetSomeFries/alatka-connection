package com.alatka.connection.core.component.inbound;

import com.alatka.connection.core.component.AbstractComponentRegister;
import com.alatka.connection.core.property.ChannelAdapterProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

/**
 * @author ybliu
 */
public abstract class GatewayRegister<T extends ChannelAdapterProperty> extends AbstractComponentRegister<T> {

    private static final String REQUEST_CHANNEL = "requestChannelName";
    private static final String REPLY_CHANNEL = "replyChannelName";

    @Override
    protected void postRegister(BeanDefinitionBuilder builder, ChannelAdapterProperty property) {
        builder.addPropertyValue(REQUEST_CHANNEL, property.getOutputChannel());
        builder.addPropertyValue(REPLY_CHANNEL, property.getInputChannel());
    }
}
