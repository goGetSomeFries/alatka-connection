package com.alatka.connection.core.component.channel;

import com.alatka.connection.core.component.AbstractComponentRegister;
import com.alatka.connection.core.property.ChannelProperty;
import com.alatka.connection.core.property.ReferencePropertyClass;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

/**
 * @author ybliu
 */
public abstract class ChannelComponentRegister<T extends ChannelProperty> extends AbstractComponentRegister<T> implements ReferencePropertyClass<T> {

    @Override
    protected void postDoRegister(BeanDefinitionBuilder builder, T property) {
        if (property.getDataTypes() != null && property.getDataTypes().length != 0) {
            builder.addPropertyValue("dataTypes", property.getDataTypes());
        }
    }
}
