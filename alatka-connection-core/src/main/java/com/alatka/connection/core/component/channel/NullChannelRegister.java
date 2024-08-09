package com.alatka.connection.core.component.channel;

import com.alatka.connection.core.property.core.ChannelProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.channel.NullChannel;

/**
 * {@link NullChannel}组件注册器
 *
 * @author ybliu
 */
public class NullChannelRegister extends ChannelComponentRegister<ChannelProperty> {

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, ChannelProperty property) {
    }

    @Override
    protected Class<NullChannel> componentClass() {
        return NullChannel.class;
    }

    @Override
    public ChannelProperty.Type mappingKey() {
        return ChannelProperty.Type.null_;
    }
}
