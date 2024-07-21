package com.alatka.connection.core.module;

import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.component.ReferenceProperty;
import com.alatka.connection.core.component.channel.ChannelComponentRegister;
import com.alatka.connection.core.property.ProcessorProperty;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ybliu
 */
public class ProcessorModuleBuilder extends AbstractModuleBuilder<List<ProcessorProperty>, List<ProcessorProperty>> {

    public ProcessorModuleBuilder(String identity) {
        super(identity);
    }

    @Override
    protected void doBuild(List<ProcessorProperty> models, Map<Object, ComponentRegister> mapping) {
        List<String> requestChannels = this.registerChannels(models, ProcessorProperty.Type.request);
        List<String> replyChannels = this.registerChannels(models, ProcessorProperty.Type.reply);
    }

    @Override
    protected List<ProcessorProperty> convert(List<ProcessorProperty> models) {
        if (models == null) {

        }
        return null;
    }

    @Override
    protected Class<? extends ComponentRegister> componentRegisterClass() {
        return null;
    }

    private List<String> registerChannels(List<ProcessorProperty> models, ProcessorProperty.Type type) {
        Map<Object, ChannelComponentRegister> mapping = SpringFactoriesLoader.loadFactories(ChannelComponentRegister.class, null).stream()
                .collect(Collectors.toMap(ReferenceProperty::reference, Function.identity()));

        return models.stream()
                .filter(processor -> processor.getType() == ProcessorProperty.Type.all || processor.getType() == type)
                .map(ProcessorProperty::getChannel)
                .map(property -> {
                    ComponentRegister componentRegister = mapping.get(property.getClass());
                    return componentRegister.register(property, property.getId(), true);
                }).collect(Collectors.toList());
    }
}
