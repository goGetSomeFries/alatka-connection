package com.alatka.connection.core.component.outbound;

import com.alatka.connection.core.component.AbstractComponentRegister;
import com.alatka.connection.core.property.core.OutboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

/**
 * TODO
 *
 * @author ybliu
 */
public abstract class OutboundComponentRegister<T extends OutboundProperty> extends AbstractComponentRegister<T, Class<T>> {

    @Override
    protected void postDoRegister(BeanDefinitionBuilder builder, OutboundProperty property) {
        if (property.getOutputChannel() != null) {
            builder.addPropertyValue("outputChannelName", property.getOutputChannel());
        }
        if (property.getOrder() != null) {
            builder.addPropertyValue("order", property.getOrder());
        }
    }
}
