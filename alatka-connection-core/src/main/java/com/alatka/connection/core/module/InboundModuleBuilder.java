package com.alatka.connection.core.module;

import com.alatka.connection.core.AlatkaConnectionConstant;
import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.component.inbound.InboundComponentRegister;
import com.alatka.connection.core.config.DefaultConfig;
import com.alatka.connection.core.model.HandlerModel;
import com.alatka.connection.core.model.InboundModel;
import com.alatka.connection.core.property.core.*;
import com.alatka.connection.core.util.ClassUtil;
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
    protected void buildInputChannel(InboundProperty property) {
        ChannelProperty channel = new ChannelProperty();
        channel.setId(AlatkaConnectionConstant.INBOUND_INPUT_CHANNEL);
        channel.setType(property.getInputChannel() != null ? ChannelProperty.Type.direct : ChannelProperty.Type.null_);
        super.channelModuleBuilder.build(channel);
    }

    @Override
    protected void buildOutputChannel(InboundProperty property) {
        ChannelProperty channel = new ChannelProperty();
        channel.setId(AlatkaConnectionConstant.INBOUND_OUTPUT_CHANNEL);
        channel.setType(ChannelProperty.Type.direct);
        super.channelModuleBuilder.build(channel);
    }

    private void buildErrorChannel(InboundProperty property) {
        if (AlatkaConnectionConstant.ERROR_CHANNEL.equals(property.getErrorChannel())) {
            ChannelProperty channel = new ChannelProperty();
            channel.setId(AlatkaConnectionConstant.ERROR_CHANNEL);
            channel.setType(ChannelProperty.Type.publishSubscribe);
            this.channelModuleBuilder.build(channel);

            ChannelAdapterProperty handler = new PassthroughHandlerProperty();
            handler.setId("handler.all." + HandlerModel.passthrough.name() + ".error");
            handler.setOutputChannel(DefaultConfig.FALLBACK_LOGGER_ERROR_CHANNEL);
            handler.setOrder(Ordered.HIGHEST_PRECEDENCE);
            this.handlerModuleBuilder.build(handler);

            ConsumerProperty consumer = new ConsumerProperty();
            consumer.setInputChannel(AlatkaConnectionConstant.ERROR_CHANNEL);
            consumer.setMessageHandler(this.handlerModuleBuilder.getBeanName());
            consumer.setId("processor.all.error");
            this.consumerModuleBuilder.build(consumer);
        }
    }

    @Override
    protected void preDoBuild(InboundProperty property) {
        super.preDoBuild(property);
        this.buildErrorChannel(property);
    }

    @Override
    protected InboundProperty validateAndConvert(Map<InboundModel, Object> map) {
        List<InboundProperty> list = map.entrySet()
                .stream()
                .map(entry -> {
                    InboundModel inboundModel = entry.getKey();
                    InboundProperty property = entry.getValue() == null ?
                            ClassUtil.newInstance(inboundModel.getType().getName()) :
                            JsonUtil.convertToObject(entry.getValue(), inboundModel.getType());
                    property.setInputChannel(inboundModel.isDuplex() ? AlatkaConnectionConstant.INBOUND_INPUT_CHANNEL : null);
                    property.setOutputChannel(AlatkaConnectionConstant.INBOUND_OUTPUT_CHANNEL);
                    if (property.getErrorChannel() == null && property.isErrorHandled()) {
                        property.setErrorChannel(inboundModel.isDuplex() ?
                                AlatkaConnectionConstant.OUTBOUND_OUTPUT_CHANNEL : AlatkaConnectionConstant.ERROR_CHANNEL);
                    }
                    return property;
                })
                .filter(Property::isEnabled)
                .collect(Collectors.toList());

        if (list.size() != 1) {
            throw new IllegalArgumentException("count of enabled " + this.endpointName() + " must be 1");
        }

        return list.get(0);
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
