package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.component.AbstractComponentRegister;
import com.alatka.connection.core.property.core.ChannelAdapterProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

/**
 * TODO
 *
 * @author ybliu
 */
public abstract class HandlerComponentRegister<T extends ChannelAdapterProperty> extends AbstractComponentRegister<T, Class<T>> {

    @Override
    protected void preDoRegister(BeanDefinitionBuilder builder, T property) {
        builder.addPropertyValue("outputChannelName", property.getOutputChannel());
        if (property.getOrder() != null) {
            builder.addPropertyValue("order", property.getOrder());
        }
    }
}
