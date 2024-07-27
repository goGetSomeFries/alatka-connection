package com.alatka.connection.core.module;

import com.alatka.connection.core.ConnectionConstant;
import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.component.OutboundComponentRegister;
import com.alatka.connection.core.model.OutboundModel;
import com.alatka.connection.core.property.ChannelAdapterProperty;
import com.alatka.connection.core.property.Property;
import com.alatka.connection.core.property.channel.ChannelProperty;
import com.alatka.connection.core.property.consumer.ConsumerProperty;
import com.alatka.connection.core.util.JsonUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ybliu
 */
public class OutboundModuleBuilder extends EndpointModuleBuilder<Map<OutboundModel, Object>, ChannelAdapterProperty> {

    private final ChannelModuleBuilder channelModuleBuilder;

    private final ConsumerModuleBuilder consumerModuleBuilder;

    public OutboundModuleBuilder(String identity) {
        super(identity);
        this.channelModuleBuilder = new ChannelModuleBuilder(identity);
        this.consumerModuleBuilder = new ConsumerModuleBuilder(identity);
    }

    @Override
    protected void doBuild(ChannelAdapterProperty property, Map<Object, ? extends ComponentRegister> mapping) {
        super.setDuplex(property.getOutputChannel() != null);

        // channel
        ChannelProperty inputChannelProperty = new ChannelProperty();
        inputChannelProperty.setId(this.inputChannel());
        this.channelModuleBuilder.build(inputChannelProperty);
        if (property.getOutputChannel() != null) {
            ChannelProperty outputChannelProperty = new ChannelProperty();
            outputChannelProperty.setId(this.outputChannel());
            this.channelModuleBuilder.build(outputChannelProperty);
        }

        // outbound
        ComponentRegister componentRegister = mapping.get(property.getClass());
        String beanName = componentRegister.register(property, property.getId().concat(".").concat(this.beanNamePrefix()), false);

        // consumer
        ConsumerProperty consumerProperty = new ConsumerProperty();
        consumerProperty.setInputChannel(this.inputChannel());
        consumerProperty.setMessageHandler(beanName);
        consumerProperty.setId(this.beanNamePrefix().concat(".consumer"));
        this.consumerModuleBuilder.build(consumerProperty);
    }

    @Override
    protected ChannelAdapterProperty convert(Map<OutboundModel, Object> map) {
        List<ChannelAdapterProperty> list = map.entrySet()
                .stream()
                .map(entry -> {
                    ChannelAdapterProperty property = JsonUtil.convertToObject(entry.getValue(), entry.getKey().getType());
                    property.setOutputChannel(entry.getKey().isDuplex() ? this.outputChannel() : null);
                    return property;
                }).filter(Property::isEnabled)
                .collect(Collectors.toList());

        if (list.size() != 1) {
            throw new IllegalArgumentException("count of enabled " + this.beanNamePrefix() + " must be 1");
        }
        return list.get(0);
    }

    @Override
    protected Class<OutboundComponentRegister> componentRegisterClass() {
        return OutboundComponentRegister.class;
    }

    protected String beanNamePrefix() {
        return "outbound";
    }

    protected String inputChannel() {
        return ConnectionConstant.OUTBOUND_INPUT_CHANNEL;
    }

    protected String outputChannel() {
        return ConnectionConstant.OUTBOUND_OUTPUT_CHANNEL;
    }

    protected void test(String outputChannel) {
        ChannelProperty property = new ChannelProperty();
        property.setId(this.inputChannel());
        this.channelModuleBuilder.build(property);
    }

}
