package com.alatka.connection.core.module;

import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.property.ProcessorProperty;

import java.util.List;
import java.util.Map;

/**
 * @author ybliu
 */
public class ProcessorModuleBuilder extends AbstractModuleBuilder<List<ProcessorProperty>, List<ProcessorProperty>> {

    private final ChannelModuleBuilder requestChannelModuleBuilder;

    private final ChannelModuleBuilder replyChannelModuleBuilder;

    private ConsumerModuleBuilder consumerModuleBuilder;

    public ProcessorModuleBuilder(String identity) {
        super(identity);
        this.requestChannelModuleBuilder = new ChannelModuleBuilder(identity, ProcessorProperty.Type.request);
        this.replyChannelModuleBuilder = new ChannelModuleBuilder(identity, ProcessorProperty.Type.reply);
        this.consumerModuleBuilder = new ConsumerModuleBuilder(identity);
    }

    @Override
    protected void doBuild(List<ProcessorProperty> models, Map<Object, ? extends ComponentRegister> mapping) {
        requestChannelModuleBuilder.build(models);
        List<String> requestChannels = requestChannelModuleBuilder.getBeanNames();

        replyChannelModuleBuilder.build(models);
        List<String> replyChannels = replyChannelModuleBuilder.getBeanNames();
    }

    @Override
    protected List<ProcessorProperty> convert(List<ProcessorProperty> models) {
        if (models == null) {

        }
        return null;
    }

    @Override
    protected Class<? extends ComponentRegister> componentRegisterClass() {
        return null;
    }

}
