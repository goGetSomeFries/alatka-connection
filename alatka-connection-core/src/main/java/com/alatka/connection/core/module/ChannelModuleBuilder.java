package com.alatka.connection.core.module;

import com.alatka.connection.core.component.ChannelComponentRegister;
import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.property.channel.ChannelProperty;

import java.util.Map;

/**
 * @author ybliu
 */
public class ChannelModuleBuilder extends AbstractModuleBuilder<ChannelProperty, ChannelProperty> {

    private String beanName;

    public ChannelModuleBuilder(String identity) {
        super(identity);
    }

    @Override
    protected void doBuild(ChannelProperty property, Map<Object, ? extends ComponentRegister> mapping) {
        ComponentRegister componentRegister = mapping.get(property.getClass());
        this.beanName = componentRegister.register(property, property.getId(), true);
    }

    @Override
    protected Class<ChannelComponentRegister> componentRegisterClass() {
        return ChannelComponentRegister.class;
    }

    public String getBeanName() {
        return this.beanName;
    }
}
