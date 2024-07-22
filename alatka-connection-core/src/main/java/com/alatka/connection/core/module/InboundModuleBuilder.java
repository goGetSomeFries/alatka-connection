package com.alatka.connection.core.module;

import com.alatka.connection.core.ConnectionConstant;
import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.component.InboundComponentRegister;
import com.alatka.connection.core.model.InboundModel;
import com.alatka.connection.core.property.ChannelAdapterProperty;
import com.alatka.connection.core.property.Property;
import com.alatka.connection.core.util.JsonUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ybliu
 */
public class InboundModuleBuilder extends AbstractModuleBuilder<Map<InboundModel, Object>, List<? extends ChannelAdapterProperty>> {

    public InboundModuleBuilder(String identity) {
        super(identity);
    }

    @Override
    protected List<String> doBuild(List<? extends ChannelAdapterProperty> models, Map<Object, ComponentRegister<? extends Property, Object>> mapping) {
        return models.stream().map(property -> {
            ComponentRegister componentRegister = mapping.get(property.getClass());
            return componentRegister.register(property, property.getId(), true);
        }).collect(Collectors.toList());
    }

    @Override
    protected List<? extends ChannelAdapterProperty> convert(Map<InboundModel, Object> map) {
        return map.entrySet()
                .stream()
                .map(entry -> JsonUtil.convertToObject(entry.getValue(), entry.getKey().getType()))
                .map(entity -> (ChannelAdapterProperty) entity)
                .peek(property -> property.setInputChannel(ConnectionConstant.INBOUND_INPUT_CHANNEL))
                .peek(property -> property.setOutputChannel(ConnectionConstant.INBOUND_OUTPUT_CHANNEL))
                .collect(Collectors.toList());
    }

    @Override
    protected Class<InboundComponentRegister> componentRegisterClass() {
        return InboundComponentRegister.class;
    }

}
