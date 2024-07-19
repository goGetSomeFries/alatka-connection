package com.alatka.connection.core.component.channel;

import com.alatka.connection.core.property.ChannelProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.channel.DirectChannel;

public class DirectChannelRegister extends SubscribableChannelRegister<ChannelProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, ChannelProperty property) {
    }

    @Override
    protected Class<DirectChannel> beanClass() {
        return DirectChannel.class;
    }

    @Override
    public Class<ChannelProperty> propertyClass() {
        return null;
    }
}
