package com.alatka.connection.core.component.channel;

import com.alatka.connection.core.property.ChannelProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.channel.PublishSubscribeChannel;

/**
 * {@link PublishSubscribeChannel}组件注册器
 *
 * @author ybliu
 */
public class PublishSubscribeChannelRegister extends ChannelComponentRegister<ChannelProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, ChannelProperty property) {
    }

    @Override
    protected Class<PublishSubscribeChannel> beanClass() {
        return PublishSubscribeChannel.class;
    }

    @Override
    public ChannelProperty.Type mappingKey() {
        return ChannelProperty.Type.publishSubscribe;
    }
}
