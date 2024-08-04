package com.alatka.connection.core.module;

import com.alatka.connection.core.AlatkaConnectionConstant;
import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.config.DefaultConfig;
import com.alatka.connection.core.property.HandlerProperty;
import com.alatka.connection.core.property.ProcessorProperty;
import com.alatka.connection.core.property.Property;
import com.alatka.connection.core.property.channel.ChannelProperty;
import com.alatka.connection.core.property.consumer.ConsumerProperty;

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
public class ProcessorModuleBuilder extends AbstractModuleBuilder<List<ProcessorProperty>, List<ProcessorProperty>> {

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
    protected void doBuild(List<ProcessorProperty> models, Map<Object, ? extends ComponentRegister> mapping) {
        AtomicInteger index = new AtomicInteger(0);
        List<ProcessorProperty> list = models.stream()
                .filter(processor -> processor.getType() == ProcessorProperty.Type.all || processor.getType() == this.type)
                .peek(processor -> index.incrementAndGet())
                .collect(Collectors.toList());
        Collections.reverse(list);

        if (list.isEmpty()) {
            list.add(new ProcessorProperty().defaultProperty());
        }

        String outputChannelBeanName = this.type == ProcessorProperty.Type.request ?
                AlatkaConnectionConstant.OUTBOUND_INPUT_CHANNEL : AlatkaConnectionConstant.INBOUND_INPUT_CHANNEL;
        AtomicReference<String> reference = new AtomicReference<>(outputChannelBeanName);

        this.buildProcessors(list, index, reference);
        this.buildOutputChannelBridge(reference);
    }

    private void buildProcessors(List<ProcessorProperty> list, AtomicInteger index, AtomicReference<String> reference) {
        list.forEach(processor -> {
            String suffix = "." + index.decrementAndGet();

            // handler
            HandlerProperty handler = processor.getHandler().isEnabled() ?
                    processor.getHandler() : new HandlerProperty().defaultProperty();
            handler.setId("handler." + type.name() + "." + handler.getType() + suffix);
            handler.setOutputChannel(reference.get());
            this.handlerModuleBuilder.build(handler);
            String handlerBeanName = this.handlerModuleBuilder.getBeanName();

            // channel
            ChannelProperty channel = processor.getChannel() == null || !processor.getChannel().isEnabled() ?
                    new ChannelProperty().defaultProperty() : processor.getChannel();
            channel.setId("channel." + type.name() + "." + channel.getType() + suffix);
            this.channelModuleBuilder.build(channel);
            String channelBeanName = this.channelModuleBuilder.getBeanName();
            reference.set(channelBeanName);

            // processor
            ConsumerProperty consumer = new ConsumerProperty();
            consumer.setId("processor." + type.name() + suffix);
            consumer.setInputChannel(channelBeanName);
            consumer.setMessageHandler(handlerBeanName);
            if (channel.getType().getKind() == ChannelProperty.Type.Kind.POLLABLE) {
                String pollerMetadata = processor.getPollerMetadata();
                if (pollerMetadata == null) {
                    pollerMetadata = DefaultConfig.FALLBACK_POLLER_METADATA;
                    this.logger.warn(consumer.getId() + " 未配置pollerMetadata，使用默认配置：" + pollerMetadata);
                }
                consumer.setPollerMetadata(pollerMetadata);
            }
            consumer.setTaskScheduler(processor.getTaskScheduler());
            this.consumerModuleBuilder.build(consumer);
        });
    }

    private void buildOutputChannelBridge(AtomicReference<String> reference) {
        String inputChannelBeanName = this.type == ProcessorProperty.Type.request ?
                AlatkaConnectionConstant.INBOUND_OUTPUT_CHANNEL : AlatkaConnectionConstant.OUTBOUND_OUTPUT_CHANNEL;

        HandlerProperty handler = new HandlerProperty();
        handler.setType(HandlerProperty.Type.passthrough);
        handler.setOutputChannel(reference.get());
        String suffix = this.type == ProcessorProperty.Type.request ? "inbound.output-processor" : "outbound.output-processor";
        handler.setId(HandlerProperty.Type.passthrough.name().concat(".").concat(suffix));
        this.handlerModuleBuilder.build(handler);

        ConsumerProperty consumer = new ConsumerProperty();
        consumer.setMessageHandler(this.handlerModuleBuilder.getBeanName());
        consumer.setInputChannel(inputChannelBeanName);
        consumer.setId("consumer.".concat(suffix));
        this.consumerModuleBuilder.build(consumer);
    }

    @Override
    protected List<ProcessorProperty> validateAndConvert(List<ProcessorProperty> models) {
        if (models == null) {
            return Collections.emptyList();
        }
        return models.stream()
                .filter(Property::isEnabled)
                .peek(processor -> {
                    if (processor.getType() == null) {
                        processor.setType(ProcessorProperty.Type.all);
                    }
                }).collect(Collectors.toList());
    }

    @Override
    protected Class<? extends ComponentRegister> componentRegisterClass() {
        return null;
    }

}
