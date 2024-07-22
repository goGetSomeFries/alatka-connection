package com.alatka.connection.core.module;

import com.alatka.connection.core.ConnectionConstant;
import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.component.OutboundComponentRegister;
import com.alatka.connection.core.model.OutboundModel;
import com.alatka.connection.core.property.ChannelAdapterProperty;
import com.alatka.connection.core.property.Property;
import com.alatka.connection.core.util.JsonUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ybliu
 */
public class OutboundModuleBuilder extends AbstractModuleBuilder<Map<OutboundModel, Object>, List<? extends ChannelAdapterProperty>> {

    public OutboundModuleBuilder(String identity) {
        super(identity);
    }

    @Override
    protected List<String> doBuild(List<? extends ChannelAdapterProperty> models, Map<Object, ComponentRegister<? extends Property, Object>> mapping) {
        return null;
    }

    @Override
    protected List<? extends ChannelAdapterProperty> convert(Map<OutboundModel, Object> map) {
        return map.entrySet()
                .stream()
                .map(entry -> JsonUtil.convertToObject(entry.getValue(), entry.getKey().getType()))
                .map(entity -> (ChannelAdapterProperty) entity)
                .peek(property -> property.setInputChannel(ConnectionConstant.OUTBOUND_INPUT_CHANNEL))
                .peek(property -> property.setOutputChannel(ConnectionConstant.INBOUND_OUTPUT_CHANNEL))
                .collect(Collectors.toList());
    }

    @Override
    protected Class<OutboundComponentRegister> componentRegisterClass() {
        return OutboundComponentRegister.class;
    }

}
