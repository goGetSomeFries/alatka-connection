package com.alatka.connection.core.module;

import com.alatka.connection.core.component.ChannelComponentRegister;
import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.property.ProcessorProperty;
import com.alatka.connection.core.property.channel.ChannelProperty;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ybliu
 */
public class ChannelModuleBuilder extends AbstractModuleBuilder<List<ProcessorProperty>, List<ChannelProperty>> {

    private ProcessorProperty.Type type;

    private List<String> beanNames;

    public ChannelModuleBuilder(String identity, ProcessorProperty.Type type) {
        super(identity);
        this.type = type;
    }

    @Override
    protected void doBuild(List<ChannelProperty> models, Map<Object, ? extends ComponentRegister> mapping) {
        this.beanNames = models.stream()
                .map(property -> {
                    ComponentRegister componentRegister = mapping.get(property.getClass());
                    return componentRegister.register(property, property.getId(), true);
                }).collect(Collectors.toList());
    }

    @Override
    protected List<ChannelProperty> convert(List<ProcessorProperty> list) {
        return list.stream()
                .filter(processor -> processor.getType() == ProcessorProperty.Type.all || processor.getType() == type)
                .map(ProcessorProperty::getChannel)
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
