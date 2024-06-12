package com.alatka.connection.core.component.channel;

import com.alatka.connection.config.property.ChannelProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.channel.DirectChannel;

public class DirectChannelRegister extends SubscribableChannelRegister<ChannelProperty> {

    @Override
    protected BeanDefinitionBuilder doRegister(ChannelProperty property) {
        return BeanDefinitionBuilder.genericBeanDefinition(beanClass(), DirectChannel::new);
    }

    @Override
    protected Class<DirectChannel> beanClass() {
        return DirectChannel.class;
    }

}
