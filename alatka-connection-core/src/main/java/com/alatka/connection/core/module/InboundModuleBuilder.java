package com.alatka.connection.core.module;

import com.alatka.connection.core.AlatkaConnectionConstant;
import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.component.inbound.InboundComponentRegister;
import com.alatka.connection.core.model.InboundModel;
import com.alatka.connection.core.property.InboundProperty;
import com.alatka.connection.core.property.Property;
import com.alatka.connection.core.property.channel.ChannelProperty;
import com.alatka.connection.core.util.JsonUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * inbound模块构建器<br><br>
 * alatka.connection.inbound.http<br>
 * alatka.connection.inbound.tcp_simplex<br>
 * alatka.connection.inbound.tcp_duplex<br>
 * alatka.connection.inbound.......
 *
 * @author ybliu
 */
public class InboundModuleBuilder extends EndpointModuleBuilder<Map<InboundModel, Object>, InboundProperty> {

    private static final String BEAN_NAME_PREFIX = "inbound";

    private final ChannelModuleBuilder channelModuleBuilder;

    public InboundModuleBuilder(String identity) {
        super(identity);
        this.channelModuleBuilder = new ChannelModuleBuilder(identity);
    }

    @Override
    protected void doBuild(InboundProperty property, Map<Object, ? extends ComponentRegister> mapping) {
        ComponentRegister componentRegister = super.getComponentRegister(property.getClass(), mapping);
        componentRegister.register(property, property.getId().concat(BEAN_NAME_PREFIX), false);
    }

    @Override
    protected void buildInputChannel() {
        ChannelProperty property = new ChannelProperty();
        property.setId(AlatkaConnectionConstant.INBOUND_INPUT_CHANNEL);
        property.setType(super.isDuplex() ? ChannelProperty.Type.direct : ChannelProperty.Type.null_);
        this.channelModuleBuilder.build(property);
    }

    @Override
    protected void buildOutputChannel() {
        ChannelProperty property = new ChannelProperty();
        property.setId(AlatkaConnectionConstant.INBOUND_OUTPUT_CHANNEL);
        property.setType(ChannelProperty.Type.direct);
        this.channelModuleBuilder.build(property);
    }

    @Override
    protected InboundProperty validateAndConvert(Map<InboundModel, Object> map) {
        List<InboundProperty> list = map.entrySet()
                .stream()
                .map(entry -> {
                    InboundProperty property = JsonUtil.convertToObject(entry.getValue(), entry.getKey().getType());
                    property.setInputChannel(entry.getKey().isDuplex() ? AlatkaConnectionConstant.INBOUND_INPUT_CHANNEL : null);
                    property.setOutputChannel(AlatkaConnectionConstant.INBOUND_OUTPUT_CHANNEL);
                    return property;
                }).filter(Property::isEnabled)
                .collect(Collectors.toList());

        if (list.size() != 1) {
            throw new IllegalArgumentException("count of enabled " + BEAN_NAME_PREFIX + " must be 1");
        }

        InboundProperty property = list.get(0);
        super.setDuplex(property.getInputChannel() != null);
        return property;
    }

    @Override
    protected Class<InboundComponentRegister> componentRegisterClass() {
        return InboundComponentRegister.class;
    }
}
