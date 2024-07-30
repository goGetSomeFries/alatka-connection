package com.alatka.connection.core.component;

import com.alatka.connection.core.property.ChannelAdapterProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

/**
 * @author ybliu
 */
public abstract class OutboundComponentRegister<T extends ChannelAdapterProperty> extends AbstractComponentRegister<T, Class<T>> {

    @Override
    protected void postDoRegister(BeanDefinitionBuilder builder, ChannelAdapterProperty property) {
        if (property.getOutputChannel() != null) {
            builder.addPropertyValue("outputChannelName", property.getOutputChannel());
        }
        if (property.getOrder() != null) {
            builder.addPropertyValue("order", property.getOrder());
        }
    }
}
