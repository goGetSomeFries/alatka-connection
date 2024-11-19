package com.alatka.connection.core.module;

import com.alatka.connection.core.AlatkaConnectionConstant;
import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.component.outbound.OutboundComponentRegister;
import com.alatka.connection.core.config.DefaultConfig;
import com.alatka.connection.core.model.OutboundModel;
import com.alatka.connection.core.property.core.*;
import com.alatka.connection.core.util.JsonUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * outbound模块构建器<br><br>
 * alatka.connection.outbound.http<br>
 * alatka.connection.outbound.tcp_simplex<br>
 * alatka.connection.outbound.tcp_duplex<br>
 * alatka.connection.outbound.......
 *
 * @author ybliu
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class OutboundModuleBuilder extends EndpointModuleBuilder<Map<OutboundModel, Object>, List<OutboundProperty>> {

    public OutboundModuleBuilder(String identity) {
        super(identity);
    }

    @Override
    protected void doBuild(List<OutboundProperty> list, Map<Object, ? extends ComponentRegister> mapping) {
        AtomicInteger index = new AtomicInteger(0);
        list.forEach(property -> {
            // outbound
            property.setOrder(this.getOrder() + index.get());
            property.setId(property.getId() + this.endpointName() + "-" + index.getAndIncrement());
            ComponentRegister componentRegister = super.getComponentRegister(property.getClass(), mapping);
            String beanName = componentRegister.register(property);

            // consumer
            ConsumerProperty consumerProperty = new ConsumerProperty();
            consumerProperty.setInputChannel(this.inputChannel());
            consumerProperty.setMessageHandler(beanName);
            consumerProperty.setId(beanName.concat(".consumer"));
            this.consumerModuleBuilder.build(consumerProperty);
        });
    }

    @Override
    protected void buildInputChannel() {
        ChannelProperty channel = new ChannelProperty();
        channel.setId(this.inputChannel());
        channel.setType(ChannelProperty.Type.publishSubscribe);
        this.channelModuleBuilder.build(channel);
    }

    @Override
    protected void buildOutputChannel() {
        ChannelProperty channel = new ChannelProperty();
        channel.setId(this.outputChannel());
        channel.setType(ChannelProperty.Type.publishSubscribe);
        this.channelModuleBuilder.build(channel);

        if (!super.isDuplex()) {
            HandlerProperty handler = new HandlerProperty();
            handler.setId(HandlerProperty.Type.passthrough.name().concat(".outbound.input-outbound.output"));
            handler.setType(HandlerProperty.Type.passthrough);
            handler.setOutputChannel(this.outputChannel());
            handler.setOrder(this.getOrder() + 100);
            this.handlerModuleBuilder.build(handler);

            ConsumerProperty consumer = new ConsumerProperty();
            consumer.setInputChannel(this.inputChannel());
            consumer.setMessageHandler(this.handlerModuleBuilder.getBeanName());
            consumer.setId(this.handlerModuleBuilder.getBeanName().concat(".consumer"));
            this.consumerModuleBuilder.build(consumer);
        }
    }

    @Override
    protected List<OutboundProperty> validateAndConvert(Map<OutboundModel, Object> map) {
        List<OutboundProperty> list = this.doConvert(map, false);
        if (list.size() == 0) {
            throw new IllegalArgumentException("count of enabled " + this.endpointName() + " must not be 0");
        } else if (list.size() == 1) {
            super.setDuplex(list.get(0).getOutputChannel() != null);
        } else {
            super.setDuplex(false);
        }
        return list;
    }

    private List<OutboundProperty> doConvert(Map<OutboundModel, Object> map, boolean composited) {
        AtomicInteger counter = new AtomicInteger(0);
        return map.entrySet().stream()
                .map(entry -> {
                    OutboundProperty property = JsonUtil.convertToObject(entry.getValue(), entry.getKey().getType());
                    if (composited) {
                        property.setOutputChannel(entry.getKey().isDuplex() ? DefaultConfig.FALLBACK_NULL_CHANNEL : null);
                    } else {
                        property.setOutputChannel(entry.getKey().isDuplex() ? this.outputChannel() : null);
                    }
                    return property;
                })
                .filter(Property::isEnabled)
                .peek(property -> {
                    if (counter.incrementAndGet() > 1) {
                        throw new IllegalArgumentException("count of enabled " + this.endpointName() + " must be 1");
                    }
                })
                .flatMap(property -> {
                    if (property.getClass() == CompositeOutboundProperty.class) {
                        return ((CompositeOutboundProperty) property).getItems()
                                .stream().flatMap(m -> this.doConvert(m, true).stream());
                    }
                    return Stream.of(property);
                })
                .collect(Collectors.toList());
    }

    @Override
    protected Class<OutboundComponentRegister> componentRegisterClass() {
        return OutboundComponentRegister.class;
    }

    @Override
    protected String endpointName() {
        return "outbound";
    }

    /**
     * {@link AlatkaConnectionConstant#OUTBOUND_INPUT_CHANNEL}消息处理优先级<br><br>
     * 1.outbound<br>
     * 2.{@link AlatkaConnectionConstant#OUTBOUND_OUTPUT_CHANNEL}（如果outbound为单工）<br>
     * 3.bypass（如果存在）
     *
     * @return 优先级序号
     */
    protected int getOrder() {
        return 0;
    }

    protected String inputChannel() {
        return AlatkaConnectionConstant.OUTBOUND_INPUT_CHANNEL;
    }

    protected String outputChannel() {
        return AlatkaConnectionConstant.OUTBOUND_OUTPUT_CHANNEL;
    }

}
