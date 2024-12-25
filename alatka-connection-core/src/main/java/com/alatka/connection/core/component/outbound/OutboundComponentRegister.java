package com.alatka.connection.core.component.outbound;

import com.alatka.connection.core.component.handler.HandlerComponentRegister;
import com.alatka.connection.core.property.core.OutboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

/**
 * TODO
 *
 * @author ybliu
 */
public abstract class OutboundComponentRegister<T extends OutboundProperty> extends HandlerComponentRegister<T> {

    @Override
    protected void preDoRegister(BeanDefinitionBuilder builder, T property) {
        if (property.getOutputChannel() != null) {
            builder.addPropertyValue("outputChannelName", property.getOutputChannel());
        }
        if (property.getOrder() != null) {
            builder.addPropertyValue("order", property.getOrder());
        }
    }
}
