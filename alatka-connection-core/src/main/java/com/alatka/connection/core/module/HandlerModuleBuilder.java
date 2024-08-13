package com.alatka.connection.core.module;

import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.component.handler.HandlerComponentRegister;
import com.alatka.connection.core.property.core.HandlerProperty;

import java.util.Map;

/**
 * handler模块构建器
 *
 * @author ybliu
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class HandlerModuleBuilder extends AbstractModuleBuilder<HandlerProperty, HandlerProperty> implements BeanNameReference {

    private String beanName;

    public HandlerModuleBuilder(String identity) {
        super(identity);
    }

    @Override
    protected void doBuild(HandlerProperty property, Map<Object, ? extends ComponentRegister> mapping) {
        ComponentRegister componentRegister = super.getComponentRegister(property.getType(), mapping);
        this.beanName = componentRegister.register(property);
    }

    @Override
    protected HandlerProperty validateAndConvert(HandlerProperty property) {
        return property;
    }

    @Override
    protected Class<HandlerComponentRegister> componentRegisterClass() {
        return HandlerComponentRegister.class;
    }

    @Override
    public String getBeanName() {
        return beanName;
    }
}
