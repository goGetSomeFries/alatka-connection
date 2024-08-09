package com.alatka.connection.core.module;

import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.component.consumer.ConsumerEndpointRegister;
import com.alatka.connection.core.property.core.ConsumerProperty;

import java.util.Map;

/**
 * consumer模块构建器
 *
 * @author ybliu
 */
@SuppressWarnings({"rawtypes"})
public class ConsumerModuleBuilder extends AbstractModuleBuilder<ConsumerProperty, ConsumerProperty> {

    public ConsumerModuleBuilder(String identity) {
        super(identity);
    }

    @Override
    protected void doBuild(ConsumerProperty property, Map<Object, ? extends ComponentRegister> mapping) {
        ConsumerEndpointRegister componentRegister =
                (ConsumerEndpointRegister) super.getComponentRegister(property.getClass(), mapping);
        componentRegister.register(property);
    }

    @Override
    protected Class<ConsumerEndpointRegister> componentRegisterClass() {
        return ConsumerEndpointRegister.class;
    }

}
