package com.alatka.connection.core.module;

import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.component.channel.ChannelComponentRegister;
import com.alatka.connection.core.property.channel.ChannelProperty;

import java.util.Map;

/**
 * channel模块构建器
 *
 * @author ybliu
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class ChannelModuleBuilder extends AbstractModuleBuilder<ChannelProperty, ChannelProperty> implements BeanNameReference {

    private String beanName;

    public ChannelModuleBuilder(String identity) {
        super(identity);
    }

    @Override
    protected void doBuild(ChannelProperty property, Map<Object, ? extends ComponentRegister> mapping) {
        ComponentRegister componentRegister = super.getComponentRegister(property.getType(), mapping);
        this.beanName = componentRegister.register(property, property.getId(), true);
    }

    @Override
    protected Class<ChannelComponentRegister> componentRegisterClass() {
        return ChannelComponentRegister.class;
    }

    @Override
    public String getBeanName() {
        return beanName;
    }
}
