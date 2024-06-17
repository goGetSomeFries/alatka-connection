package com.alatka.connection.core.component.channel;

import com.alatka.connection.core.property.ChannelProperty;
import com.alatka.connection.core.component.AbstractComponentRegister;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

/**
 * @author ybliu
 */
public abstract class ChannelRegister<T extends ChannelProperty> extends AbstractComponentRegister<T> {

    @Override
    protected void postRegister(BeanDefinitionBuilder builder, T property) {
        if (property.getDataTypes() != null && property.getDataTypes().length != 0) {
            builder.addPropertyValue("dataTypes", property.getDataTypes());
        }
    }
}
