package com.alatka.connection.core.module;

import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.component.handler.HandlerComponentRegister;
import com.alatka.connection.core.property.HandlerProperty;

import java.util.Map;

/**
 * @author ybliu
 */
public class HandlerModuleBuilder extends BeanNameReferenceModuleBuilder<HandlerProperty, HandlerProperty> {

    public HandlerModuleBuilder(String identity) {
        super(identity);
    }

    @Override
    protected void doBuild(HandlerProperty property, Map<Object, ? extends ComponentRegister> mapping) {
        ComponentRegister componentRegister = super.getComponentRegister(property.getType(), mapping);
        String beanName = componentRegister.register(property, property.getId(), true);
        super.setBeanName(beanName);
    }

    @Override
    protected Class<HandlerComponentRegister> componentRegisterClass() {
        return HandlerComponentRegister.class;
    }

}
