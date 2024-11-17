package com.alatka.connection.core.module;

import com.alatka.connection.core.AlatkaConnectionConstant;
import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.component.inbound.InboundComponentRegister;
import com.alatka.connection.core.config.DefaultConfig;
import com.alatka.connection.core.model.InboundModel;
import com.alatka.connection.core.property.core.*;
import com.alatka.connection.core.util.JsonUtil;
import org.springframework.core.Ordered;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * inbound模块构建器<br><br>
 * alatka.connection.inbound.http<br>
 * alatka.connection.inbound.tcp_simplex<br>
 * alatka.connection.inbound.tcp_duplex<br>
 * alatka.connection.inbound.rabbitmq<br>
 * alatka.connection.inbound.......
 *
 * @author ybliu
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class InboundModuleBuilder extends EndpointModuleBuilder<Map<InboundModel, Object>, InboundProperty> {

    public InboundModuleBuilder(String identity) {
        super(identity);
    }

    @Override
    protected void doBuild(InboundProperty property, Map<Object, ? extends ComponentRegister> mapping) {
        property.setId(property.getId().concat(this.endpointName()));
        ComponentRegister componentRegister = super.getComponentRegister(property.getClass(), mapping);
        componentRegister.register(property);
    }

    @Override
    protected void buildInputChannel() {
        ChannelProperty property = new ChannelProperty();
        property.setId(AlatkaConnectionConstant.INBOUND_INPUT_CHANNEL);
        property.setType(super.isDuplex() ? ChannelProperty.Type.direct : ChannelProperty.Type.null_);
        super.channelModuleBuilder.build(property);
    }

    @Override
    protected void buildOutputChannel() {
        ChannelProperty property = new ChannelProperty();
        property.setId(AlatkaConnectionConstant.INBOUND_OUTPUT_CHANNEL);
        property.setType(ChannelProperty.Type.direct);
        super.channelModuleBuilder.build(property);
    }

    private void buildErrorChannel() {
        ChannelProperty channel = new ChannelProperty();
        channel.setId(AlatkaConnectionConstant.INBOUND_ERROR_CHANNEL);
        channel.setType(ChannelProperty.Type.publishSubscribe);
        this.channelModuleBuilder.build(channel);

        HandlerProperty handler = new HandlerProperty();
        handler.setId(HandlerProperty.Type.passthrough.name().concat(".inbound.error"));
        handler.setType(HandlerProperty.Type.passthrough);
        handler.setOutputChannel(DefaultConfig.FALLBACK_LOGGER_ERROR_CHANNEL);
        handler.setOrder(Ordered.HIGHEST_PRECEDENCE);
        this.handlerModuleBuilder.build(handler);

        ConsumerProperty consumer = new ConsumerProperty();
        consumer.setInputChannel(AlatkaConnectionConstant.INBOUND_ERROR_CHANNEL);
        consumer.setMessageHandler(this.handlerModuleBuilder.getBeanName());
        consumer.setId(this.handlerModuleBuilder.getBeanName().concat(".consumer"));
        this.consumerModuleBuilder.build(consumer);
    }

    @Override
    protected void preDoBuild(Object object) {
        super.preDoBuild(object);
        this.buildErrorChannel();
    }

    @Override
    protected InboundProperty validateAndConvert(Map<InboundModel, Object> map) {
        List<InboundProperty> list = map.entrySet()
                .stream()
                .map(entry -> {
                    InboundProperty property = JsonUtil.convertToObject(entry.getValue(), entry.getKey().getType());
                    property.setInputChannel(entry.getKey().isDuplex() ? AlatkaConnectionConstant.INBOUND_INPUT_CHANNEL : null);
                    property.setOutputChannel(AlatkaConnectionConstant.INBOUND_OUTPUT_CHANNEL);
                    property.setErrorChannel(AlatkaConnectionConstant.INBOUND_ERROR_CHANNEL);
                    return property;
                }).filter(Property::isEnabled)
                .collect(Collectors.toList());

        if (list.size() != 1) {
            throw new IllegalArgumentException("count of enabled " + this.endpointName() + " must be 1");
        }

        InboundProperty property = list.get(0);
        super.setDuplex(property.getInputChannel() != null);
        return property;
    }

    @Override
    protected Class<InboundComponentRegister> componentRegisterClass() {
        return InboundComponentRegister.class;
    }

    @Override
    protected String endpointName() {
        return "inbound";
    }
}
