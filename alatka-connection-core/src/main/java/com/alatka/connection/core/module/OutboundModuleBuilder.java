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
    protected void doBuild(ChannelAdapterProperty property, Map<Object, ? extends ComponentRegister> mapping) {
        ComponentRegister componentRegister = mapping.get(property.getClass());
        String beanName = componentRegister.register(property, property.getId().concat(".").concat(PREFIX), false);

        ConsumerProperty consumerProperty = new ConsumerProperty();
        consumerProperty.setInputChannel(ConnectionConstant.OUTBOUND_INPUT_CHANNEL);
        consumerProperty.setMessageHandler(beanName);
        consumerProperty.setId(PREFIX.concat(".consumer"));
        this.moduleBuilder.build(consumerProperty);
    }

    @Override
    protected ChannelAdapterProperty convert(Map<OutboundModel, Object> map) {
        List<ChannelAdapterProperty> list = map.entrySet()
                .stream()
                .map(entry -> {
                    ChannelAdapterProperty property = JsonUtil.convertToObject(entry.getValue(), entry.getKey().getType());
                    property.setOutputChannel(entry.getKey().isDuplex() ? ConnectionConstant.OUTBOUND_OUTPUT_CHANNEL : null);
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
