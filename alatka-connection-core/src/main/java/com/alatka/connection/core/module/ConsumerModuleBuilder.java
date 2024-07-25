package com.alatka.connection.core.module;

import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.component.consumer.ConsumerEndpointRegister;
import com.alatka.connection.core.property.consumer.ConsumerProperty;

import java.util.Map;

/**
 * @author ybliu
 */
public class ConsumerModuleBuilder extends AbstractModuleBuilder<ConsumerProperty, ConsumerProperty> {

    public ConsumerModuleBuilder(String identity) {
        super(identity);
    }

    @Override
    protected void doBuild(ConsumerProperty property, Map<Object, ? extends ComponentRegister> mapping) {
        ConsumerEndpointRegister componentRegister = (ConsumerEndpointRegister) mapping.get(property.getClass());
        componentRegister.register(property, property.getId(), true);
    }

    @Override
    protected Class<ConsumerEndpointRegister> componentRegisterClass() {
        return ConsumerEndpointRegister.class;
    }

}
