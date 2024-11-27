package com.alatka.connection.core.component.inbound;

import com.alatka.connection.core.component.AbstractComponentRegister;
import com.alatka.connection.core.property.core.InboundProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

/**
 * TODO
 *
 * @author ybliu
 */
public abstract class InboundComponentRegister<T extends InboundProperty> extends AbstractComponentRegister<T, Class<T>> {

    @Override
    protected void postDoRegister(BeanDefinitionBuilder builder, InboundProperty property) {
        if (property.getInputChannel() == null) {
            builder.addPropertyValue("outputChannelName", property.getOutputChannel());
        } else {
            builder.addPropertyValue("requestChannelName", property.getOutputChannel());
            builder.addPropertyValue("replyChannelName", property.getInputChannel());
        }

        if (property.getErrorChannel() != null) {
            builder.addPropertyValue("errorChannelName", property.getErrorChannel());
        }
    }

}
