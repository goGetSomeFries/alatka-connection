package com.alatka.connection.core.module;

import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.component.consumer.ConsumerEndpointRegister;
import com.alatka.connection.core.property.Property;
import com.alatka.connection.core.property.consumer.ConsumerProperty;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ConsumerModuleBuilder extends AbstractModuleBuilder<ConsumerProperty, ConsumerProperty> {

    public ConsumerModuleBuilder(String identity) {
        super(identity);
    }

    @Override
    protected List<String> doBuild(ConsumerProperty property, Map<Object, ComponentRegister<? extends Property, Object>> mapping) {
        ConsumerEndpointRegister componentRegister = new ConsumerEndpointRegister();
        componentRegister.register(property, property.getId(), false);
        return null;
    }

    @Override
    protected Map<Object, ComponentRegister<? extends Property, Object>> mappingComponentRegister() {
        return Collections.emptyMap();
    }

    @Override
    protected Class<ConsumerEndpointRegister> componentRegisterClass() {
        return ConsumerEndpointRegister.class;
    }

}
