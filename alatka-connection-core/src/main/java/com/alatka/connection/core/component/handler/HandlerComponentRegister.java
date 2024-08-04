package com.alatka.connection.core.component.handler;

import com.alatka.connection.core.component.AbstractComponentRegister;
import com.alatka.connection.core.property.HandlerProperty;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

import java.util.Collections;

/**
 * @author ybliu
 */
public abstract class HandlerComponentRegister<T extends HandlerProperty> extends AbstractComponentRegister<T, HandlerProperty.Type> {

    @Override
    protected void preDoRegister(BeanDefinitionBuilder builder, T property) {
        builder.addPropertyValue("outputChannelName", property.getOutputChannel());
        if (property.getOrder() != null) {
            builder.addPropertyValue("order", property.getOrder());
        }
        if (property.getParams() == null) {
            property.setParams(Collections.emptyMap());
        }
    }
}
