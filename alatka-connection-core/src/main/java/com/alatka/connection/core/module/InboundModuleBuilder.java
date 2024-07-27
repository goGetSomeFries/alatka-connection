package com.alatka.connection.core.module;

import com.alatka.connection.core.ConnectionConstant;
import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.component.InboundComponentRegister;
import com.alatka.connection.core.model.InboundModel;
import com.alatka.connection.core.property.InboundProperty;
import com.alatka.connection.core.property.Property;
import com.alatka.connection.core.property.channel.ChannelProperty;
import com.alatka.connection.core.util.JsonUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ybliu
 */
public class InboundModuleBuilder extends EndpointModuleBuilder<Map<InboundModel, Object>, InboundProperty> {

    private static final String PREFIX = "inbound";

    private final ChannelModuleBuilder channelModuleBuilder;

    public InboundModuleBuilder(String identity) {
        super(identity);
        this.channelModuleBuilder = new ChannelModuleBuilder(identity);
    }

    @Override
    protected void doBuild(InboundProperty property, Map<Object, ? extends ComponentRegister> mapping) {
        super.setDuplex(property.getInputChannel() != null);

        // channel
        ChannelProperty outputChannelProperty = new ChannelProperty();
        outputChannelProperty.setId(ConnectionConstant.INBOUND_OUTPUT_CHANNEL);
        this.channelModuleBuilder.build(outputChannelProperty);
        if (property.getInputChannel() != null) {
            ChannelProperty inputChannelProperty = new ChannelProperty();
            inputChannelProperty.setId(ConnectionConstant.INBOUND_INPUT_CHANNEL);
            this.channelModuleBuilder.build(inputChannelProperty);
        }

        // inbound
        ComponentRegister componentRegister = mapping.get(property.getClass());
        componentRegister.register(property, property.getId().concat(".").concat(PREFIX), false);
    }

    @Override
    protected InboundProperty convert(Map<InboundModel, Object> map) {
        List<InboundProperty> list = map.entrySet()
                .stream()
                .map(entry -> {
                    InboundProperty property = JsonUtil.convertToObject(entry.getValue(), entry.getKey().getType());
                    property.setInputChannel(entry.getKey().isDuplex() ? ConnectionConstant.INBOUND_INPUT_CHANNEL : null);
                    property.setOutputChannel(ConnectionConstant.INBOUND_OUTPUT_CHANNEL);
                    return property;
                }).filter(Property::isEnabled)
                .collect(Collectors.toList());

        if (list.size() != 1) {
            throw new IllegalArgumentException("count of enabled " + PREFIX + " must be 1");
        }
        return list.get(0);
    }

    @Override
    protected Class<InboundComponentRegister> componentRegisterClass() {
        return InboundComponentRegister.class;
    }
}
