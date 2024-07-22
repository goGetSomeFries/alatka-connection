package com.alatka.connection.core.component;

import com.alatka.connection.core.component.AbstractComponentRegister;
import com.alatka.connection.core.property.channel.ChannelProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

/**
 * @author ybliu
 */
public abstract class ChannelComponentRegister<T extends ChannelProperty> extends AbstractComponentRegister<T, ChannelProperty.Type> {

    @Override
    protected void postDoRegister(BeanDefinitionBuilder builder, T property) {
        if (property.getDataTypes() != null && property.getDataTypes().length != 0) {
            builder.addPropertyValue("dataTypes", property.getDataTypes());
        }
    }
}
