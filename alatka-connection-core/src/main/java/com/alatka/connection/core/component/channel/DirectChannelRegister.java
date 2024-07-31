package com.alatka.connection.core.component.channel;

import com.alatka.connection.core.property.channel.ChannelProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.channel.DirectChannel;

/**
 * {@link DirectChannel}组件注册器
 *
 * @author ybliu
 */
public class DirectChannelRegister extends ChannelComponentRegister<ChannelProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, ChannelProperty property) {
    }

    @Override
    protected Class<DirectChannel> beanClass() {
        return DirectChannel.class;
    }

    @Override
    public ChannelProperty.Type mappingKey() {
        return ChannelProperty.Type.direct;
    }
}
