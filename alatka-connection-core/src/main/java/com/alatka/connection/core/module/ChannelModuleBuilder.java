package com.alatka.connection.core.module;

import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.component.channel.ChannelComponentRegister;
import com.alatka.connection.core.property.channel.ChannelProperty;

import java.util.Map;

/**
 * @author ybliu
 */
public class ChannelModuleBuilder extends BeanNameReferenceModuleBuilder<ChannelProperty, ChannelProperty> {

    public ChannelModuleBuilder(String identity) {
        super(identity);
    }

    @Override
    protected void doBuild(ChannelProperty property, Map<Object, ? extends ComponentRegister> mapping) {
        ComponentRegister componentRegister = super.getComponentRegister(property.getType(), mapping);
        String beanName = componentRegister.register(property, property.getId(), true);
        super.setBeanName(beanName);
    }

    @Override
    protected Class<ChannelComponentRegister> componentRegisterClass() {
        return ChannelComponentRegister.class;
    }

}
