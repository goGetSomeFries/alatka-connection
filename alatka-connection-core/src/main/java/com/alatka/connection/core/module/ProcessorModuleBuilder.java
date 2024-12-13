package com.alatka.connection.core.module;

import com.alatka.connection.core.AlatkaConnectionConstant;
import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.config.DefaultConfig;
import com.alatka.connection.core.model.HandlerModel;
import com.alatka.connection.core.model.ProcessorsModel;
import com.alatka.connection.core.property.core.*;
import com.alatka.connection.core.util.JsonUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * processor模块构建器<br><br>
 * alatka.connection.processors[1-n].type<br>
 * alatka.connection.processors[1-n].handler<br>
 * alatka.connection.processors[1-n].channel<br>
 * alatka.connection.processors[1-n].......
 *
 * @author ybliu
 */
@SuppressWarnings("rawtypes")
public class ProcessorModuleBuilder extends AbstractModuleBuilder<ProcessorsModel, List<ProcessorProperty>> {

    private final ProcessorProperty.Type type;

    private final ChannelModuleBuilder channelModuleBuilder;

    private final HandlerModuleBuilder handlerModuleBuilder;

    private final ConsumerModuleBuilder consumerModuleBuilder;

    public ProcessorModuleBuilder(String identity, ProcessorProperty.Type type) {
        super(identity);
        this.type = type;
        this.channelModuleBuilder = new ChannelModuleBuilder(identity);
        this.handlerModuleBuilder = new HandlerModuleBuilder(identity);
        this.consumerModuleBuilder = new ConsumerModuleBuilder(identity);
    }

    @Override
    protected void doBuild(List<ProcessorProperty> list, Map<Object, ? extends ComponentRegister> mapping) {
        AtomicInteger index = new AtomicInteger(list.size());
        List<ProcessorProperty> properties = new ArrayList<>(list);
        Collections.reverse(properties);

        if (properties.isEmpty()) {
            properties.add(new ProcessorProperty().defaultProperty());
            index.addAndGet(1);
        }

        String outputChannelBeanName = this.type == ProcessorProperty.Type.request ?
                AlatkaConnectionConstant.OUTBOUND_INPUT_CHANNEL : AlatkaConnectionConstant.INBOUND_INPUT_CHANNEL;
        AtomicReference<String> atomic = new AtomicReference<>(outputChannelBeanName);

        this.buildProcessors(properties, index, atomic);
        this.buildOutputChannelBridge(atomic.get());
    }

    private void buildProcessors(List<ProcessorProperty> list, AtomicInteger index, AtomicReference<String> atomic) {
        list.forEach(processor -> {
            String suffix = "." + index.decrementAndGet();

            // handler
            List<ChannelAdapterProperty> handlers = processor.getHandler().entrySet().stream()
                    .map(entry -> {
                        ChannelAdapterProperty handler = JsonUtil.convertToObject(entry.getValue(), entry.getKey().getType());
                        handler.setId("handler." + type.name() + "." + entry.getKey().name() + suffix);
                        handler.setOutputChannel(atomic.get());
                        return handler;
                    })
                    .filter(Property::isEnabled)
                    .collect(Collectors.toList());
            if (handlers.size() != 1) {
                throw new IllegalArgumentException("count of enabled handler must be 1");
            }
            this.handlerModuleBuilder.build(handlers.get(0));

            // channel
            ChannelProperty channel = processor.getChannel() == null || !processor.getChannel().isEnabled() ?
                    new ChannelProperty().defaultProperty() : processor.getChannel();
            channel.setId("channel." + type.name() + "." + channel.getType() + suffix);
            this.channelModuleBuilder.build(channel);
            atomic.set(this.channelModuleBuilder.getBeanName());

            // processor
            ConsumerProperty consumer = new ConsumerProperty();
            consumer.setId("processor." + type.name() + suffix);
            consumer.setInputChannel(this.channelModuleBuilder.getBeanName());
            consumer.setMessageHandler(this.handlerModuleBuilder.getBeanName());
            if (channel.getType().getKind() == ChannelProperty.Type.Kind.POLLABLE) {
                String pollerMetadata = processor.getPollerMetadata();
                if (pollerMetadata == null) {
                    pollerMetadata = DefaultConfig.FALLBACK_POLLER_METADATA;
                    this.logger.warn("{} 未配置pollerMetadata，使用默认配置：{}", consumer.getId(), pollerMetadata);
                }
                consumer.setPollerMetadata(pollerMetadata);
            }
            consumer.setTaskScheduler(processor.getTaskScheduler());
            this.consumerModuleBuilder.build(consumer);
        });
    }

    private void buildOutputChannelBridge(String channelBeanName) {
        String inputChannelBeanName = this.type == ProcessorProperty.Type.request ?
                AlatkaConnectionConstant.INBOUND_OUTPUT_CHANNEL : AlatkaConnectionConstant.OUTBOUND_OUTPUT_CHANNEL;

        ChannelAdapterProperty handler = new PassthroughHandlerProperty();
        String suffix = this.type == ProcessorProperty.Type.request ? ".inbound-processor" : ".outbound-processor";
        handler.setId("handler." + this.type.name() + "." + HandlerModel.passthrough.name() + suffix);
        handler.setOutputChannel(channelBeanName);
        this.handlerModuleBuilder.build(handler);

        ConsumerProperty consumer = new ConsumerProperty();
        consumer.setMessageHandler(this.handlerModuleBuilder.getBeanName());
        consumer.setInputChannel(inputChannelBeanName);
        consumer.setId("processor." + this.type.name() + suffix);
        this.consumerModuleBuilder.build(consumer);
    }

    @Override
    protected List<ProcessorProperty> validateAndConvert(ProcessorsModel model) {
        if (model == null || model.getProcessors() == null) {
            return Collections.emptyList();
        }
        return model.getProcessors().stream()
                .filter(Property::isEnabled)
                .filter(processor -> processor.getType() == null ||
                        processor.getType() == ProcessorProperty.Type.both ||
                        processor.getType() == this.type)
                .collect(Collectors.toList());
    }

    @Override
    protected Class<? extends ComponentRegister> componentRegisterClass() {
        return null;
    }

}
