package com.alatka.connection.core.module;

import com.alatka.connection.core.component.ChannelComponentRegister;
import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.property.ProcessorProperty;
import com.alatka.connection.core.property.Property;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ybliu
 */
public class ChannelModuleBuilder extends AbstractModuleBuilder<List<ProcessorProperty>, List<ProcessorProperty>> {

    private ProcessorProperty.Type type;

    public ChannelModuleBuilder(String identity) {
        super(identity);
    }

    @Override
    protected List<String> doBuild(List<ProcessorProperty> models, Map<Object, ComponentRegister<? extends Property, Object>> mapping) {
        return models.stream()
                .filter(processor -> processor.getType() == ProcessorProperty.Type.all || processor.getType() == type)
                .map(ProcessorProperty::getChannel)
                .map(property -> {
                    ComponentRegister componentRegister = mapping.get(property.getClass());
                    return componentRegister.register(property, property.getId(), true);
                }).collect(Collectors.toList());
    }

    @Override
    protected List<ProcessorProperty> convert(List<ProcessorProperty> list) {
        return null;
    }

    @Override
    protected Class<ChannelComponentRegister> componentRegisterClass() {
        return ChannelComponentRegister.class;
    }

    public ChannelModuleBuilder type(ProcessorProperty.Type type) {
        this.type = type;
        return this;
    }
}
