package com.alatka.connection.core.module;

import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.component.HandlerComponentRegister;
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
        ComponentRegister componentRegister = super.getComponentRegister(property.getClass(), mapping);
        this.beanName = componentRegister.register(property, property.getId(), true);
    }

    @Override
    protected Class<HandlerComponentRegister> componentRegisterClass() {
        return HandlerComponentRegister.class;
    }

    public String getBeanName() {
        return beanName;
    }
}
