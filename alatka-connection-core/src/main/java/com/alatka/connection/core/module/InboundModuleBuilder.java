package com.alatka.connection.core.module;

import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.component.InboundComponentRegister;
import com.alatka.connection.core.model.InboundModel;
import com.alatka.connection.core.property.ChannelAdapterProperty;
import com.alatka.connection.core.util.JsonUtil;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ybliu
 */
public class InboundModuleBuilder extends AbstractModuleBuilder<Map<InboundModel, Object>, List<? extends ChannelAdapterProperty>> {

    private String inputChannel;
    private String outputChannel;

    public InboundModuleBuilder(String identity) {
        super(identity);
    }

    @Override
    protected void doBuild(List<? extends ChannelAdapterProperty> models) {
        Map<Class<ChannelAdapterProperty>, ComponentRegister> mapping =
                SpringFactoriesLoader.loadFactories(InboundComponentRegister.class, null)
                        .stream()
                        .collect(Collectors.toMap(InboundComponentRegister::propertyClass, Function.identity()));

        models.forEach(property -> {
            ComponentRegister componentRegister = mapping.get(property.getClass());
            componentRegister.register(property, property.getId(), true);
        });
    }

    @Override
    protected List<? extends ChannelAdapterProperty> convert(Map<InboundModel, Object> map) {
        return map.entrySet()
                .stream()
                .map(entry -> JsonUtil.convertToObject(entry.getValue(), entry.getKey().getType()))
                .map(entity -> (ChannelAdapterProperty) entity)
                .peek(property -> property.setInputChannel(this.inputChannel))
                .peek(property -> property.setOutputChannel(this.outputChannel))
                .collect(Collectors.toList());
    }

    public InboundModuleBuilder inputChannel(String inputChannel) {
        this.inputChannel = inputChannel;
        return this;
    }

    public InboundModuleBuilder outputChannel(String outputChannel) {
        this.outputChannel = outputChannel;
        return this;
    }
}
