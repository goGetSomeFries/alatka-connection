package com.alatka.connection.core.module;

import com.alatka.connection.core.component.ChannelComponentRegister;
import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.property.HandlerProperty;

import java.util.Map;

/**
 * @author ybliu
 */
public class HandlerModuleBuilder extends AbstractModuleBuilder<HandlerProperty, HandlerProperty> {

    private String beanName;

    public HandlerModuleBuilder(String identity) {
        super(identity);
    }

    @Override
    protected void doBuild(HandlerProperty property, Map<Object, ? extends ComponentRegister> mapping) {
        ComponentRegister componentRegister = mapping.get(property.getClass());
        this.beanName = componentRegister.register(property, property.getId(), true);
    }

    @Override
    protected Class<ChannelComponentRegister> componentRegisterClass() {
        return ChannelComponentRegister.class;
    }

    public String getBeanName() {
        return beanName;
    }
}
