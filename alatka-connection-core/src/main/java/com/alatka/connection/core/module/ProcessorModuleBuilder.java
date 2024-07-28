package com.alatka.connection.core.module;

import com.alatka.connection.core.ConnectionConstant;
import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.property.HandlerProperty;
import com.alatka.connection.core.property.ProcessorProperty;
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
                ConnectionConstant.OUTBOUND_INPUT_CHANNEL : ConnectionConstant.INBOUND_INPUT_CHANNEL;
        AtomicReference<String> reference = new AtomicReference<>(outputChannelBeanName);

        list.forEach(processor -> {
            String suffix = type.name() + index.getAndDecrement();

            // handler
            HandlerProperty handler = processor.getHandler();
            handler.setId("handler." + suffix);
            handler.setOutputChannel(reference.get());
            this.handlerModuleBuilder.build(handler);
            String handlerBeanName = this.handlerModuleBuilder.getBeanName();

            // channel
            ChannelProperty channel = processor.getChannel();
            channel.setId("channel." + suffix);
            this.channelModuleBuilder.build(channel);
            String channelBeanName = this.channelModuleBuilder.getBeanName();
            reference.set(channelBeanName);

            // processor
            ConsumerProperty consumer = new ConsumerProperty();
            consumer.setInputChannel(channelBeanName);
            consumer.setMessageHandler(handlerBeanName);
            consumer.setPollerMetadata(null);
            consumer.setTaskScheduler(null);
            consumer.setId("processor." + suffix);
            this.consumerModuleBuilder.build(consumer);
        });

        HandlerProperty handler = null;
        handler.setId("");
        handler.setOutputChannel(reference.get());

        ConsumerProperty consumer = new ConsumerProperty();
        consumer.setMessageHandler(null);
        String inputChannelBeanName = this.type == ProcessorProperty.Type.request ?
                ConnectionConstant.INBOUND_OUTPUT_CHANNEL : ConnectionConstant.OUTBOUND_OUTPUT_CHANNEL;
        consumer.setInputChannel(inputChannelBeanName);
        consumer.setId("");
        this.consumerModuleBuilder.build(consumer);
    }

    @Override
    protected List<ProcessorProperty> validAndConvert(List<ProcessorProperty> models) {
        return models == null ? Collections.emptyList() : models;
    }

    @Override
    protected Class<? extends ComponentRegister> componentRegisterClass() {
        return null;
    }

}
