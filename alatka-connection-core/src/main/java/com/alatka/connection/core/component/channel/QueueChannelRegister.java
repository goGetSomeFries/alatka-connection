package com.alatka.connection.core.component.channel;

import com.alatka.connection.core.component.ChannelComponentRegister;
import com.alatka.connection.core.property.channel.ChannelProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.channel.QueueChannel;

import java.util.Map;

/**
 * {@link QueueChannel}组件注册器
 *
 * @author ybliu
 */
public class QueueChannelRegister extends ChannelComponentRegister<ChannelProperty> {

    private static final String KEY_CAPACITY = "capacity";

    @Override
    protected void doRegister(BeanDefinitionBuilder builder, ChannelProperty property) {
        Map<String, Object> params = property.getParams();
        int capacity = params.get(KEY_CAPACITY) == null ?
                Integer.MAX_VALUE : Integer.parseInt(params.get(KEY_CAPACITY).toString());

        builder.addConstructorArgValue(capacity);
    }

    @Override
    protected Class<QueueChannel> beanClass() {
        return QueueChannel.class;
    }

    @Override
    public ChannelProperty.Type mappingKey() {
        return ChannelProperty.Type.queue;
    }
}
