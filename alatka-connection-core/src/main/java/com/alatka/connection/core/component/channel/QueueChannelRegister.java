package com.alatka.connection.core.component.channel;

import com.alatka.connection.core.property.ChannelProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.channel.QueueChannel;

import java.util.Map;

public class QueueChannelRegister extends PollableChannelBeanDefinitionRegister<ChannelProperty> {

    private static final String KEY_CAPACITY = "capacity";

    @Override
    protected BeanDefinitionBuilder doRegister(ChannelProperty param) {
        Map<String, Object> params = param.getParams();
        int capacity = params.get(KEY_CAPACITY) == null ?
                Integer.MAX_VALUE : Integer.parseInt(params.get(KEY_CAPACITY).toString());

        return BeanDefinitionBuilder.genericBeanDefinition(beanClass(), () -> new QueueChannel(capacity))
                .addConstructorArgValue(capacity);
    }

    @Override
    protected Class<QueueChannel> beanClass() {
        return QueueChannel.class;
    }

}
