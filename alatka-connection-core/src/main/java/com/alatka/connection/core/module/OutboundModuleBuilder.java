package com.alatka.connection.core.module;

import com.alatka.connection.core.ConnectionConstant;
import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.component.OutboundComponentRegister;
import com.alatka.connection.core.model.OutboundModel;
import com.alatka.connection.core.property.ChannelAdapterProperty;
import com.alatka.connection.core.property.Property;
import com.alatka.connection.core.property.consumer.ConsumerProperty;
import com.alatka.connection.core.util.JsonUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ybliu
 */
public class OutboundModuleBuilder extends AbstractModuleBuilder<Map<OutboundModel, Object>, ChannelAdapterProperty> {

    private ConsumerModuleBuilder moduleBuilder;

    private static final String PREFIX = "outbound";

    public OutboundModuleBuilder(String identity) {
        super(identity);
        this.moduleBuilder = new ConsumerModuleBuilder(identity);
    }

    @Override
    protected List<String> doBuild(ChannelAdapterProperty property, Map<Object, ComponentRegister<? extends Property, Object>> mapping) {
        ComponentRegister<? extends Property, Object> componentRegister = mapping.get(property.getClass());
//        String beanName = componentRegister.register(property, property.getId().concat(".").concat(PREFIX), false);

        ConsumerProperty consumerProperty = new ConsumerProperty();
        this.moduleBuilder.build(consumerProperty);
        return null;
    }

    @Override
    protected ChannelAdapterProperty convert(Map<OutboundModel, Object> map) {
        List<ChannelAdapterProperty> list = map.entrySet()
                .stream()
                .map(entry -> {
                    ChannelAdapterProperty property = JsonUtil.convertToObject(entry.getValue(), entry.getKey().getType());
                    property.setInputChannel(ConnectionConstant.OUTBOUND_INPUT_CHANNEL);
                    property.setOutputChannel(entry.getKey().isDuplex() ? ConnectionConstant.INBOUND_OUTPUT_CHANNEL : null);
                    return property;
                }).filter(Property::isEnabled)
                .collect(Collectors.toList());

        if (list.size() != 1) {
            throw new IllegalArgumentException("count of enabled outbound must be 1");
        }
        return list.get(0);
    }

    @Override
    protected Class<OutboundComponentRegister> componentRegisterClass() {
        return OutboundComponentRegister.class;
    }

}
