package com.alatka.connection.core.module;

import com.alatka.connection.core.component.ChannelComponentRegister;
import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.property.HandlerProperty;
import com.alatka.connection.core.property.ProcessorProperty;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ybliu
 */
public class HandlerModuleBuilder extends AbstractModuleBuilder<List<ProcessorProperty>, List<HandlerProperty>> {

    private ProcessorProperty.Type type;

    private List<String> beanNames;

    public HandlerModuleBuilder(String identity, ProcessorProperty.Type type) {
        super(identity);
        this.type = type;
    }

    @Override
    protected void doBuild(List<HandlerProperty> models, Map<Object, ? extends ComponentRegister> mapping) {
        this.beanNames = models.stream()
                .map(property -> {
                    ComponentRegister componentRegister = mapping.get(property.getClass());
                    return componentRegister.register(property, property.getId(), true);
                }).collect(Collectors.toList());
    }

    @Override
    protected List<HandlerProperty> convert(List<ProcessorProperty> list) {
        return list.stream()
                .filter(processor -> processor.getType() == ProcessorProperty.Type.all || processor.getType() == type)
                .map(ProcessorProperty::getHandler)
                .collect(Collectors.toList());
    }

    @Override
    protected Class<ChannelComponentRegister> componentRegisterClass() {
        return ChannelComponentRegister.class;
    }

    public List<String> getBeanNames() {
        return beanNames;
    }
}
