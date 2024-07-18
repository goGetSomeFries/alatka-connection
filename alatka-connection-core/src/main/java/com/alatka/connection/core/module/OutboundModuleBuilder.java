package com.alatka.connection.core.module;

import com.alatka.connection.core.model.OutboundModel;
import com.alatka.connection.core.property.ChannelAdapterProperty;

import java.util.List;
import java.util.Map;

/**
 * @author ybliu
 */
public class OutboundModuleBuilder extends AbstractModuleBuilder<Map<OutboundModel, Object>, List<? extends ChannelAdapterProperty>> {

    private String inputChannel;
    private String outputChannel;

    public OutboundModuleBuilder(String identity) {
        super(identity);
    }

    @Override
    protected void doBuild(List<? extends ChannelAdapterProperty> models) {
    }

    @Override
    protected List<? extends ChannelAdapterProperty> convert(Map<OutboundModel, Object> map) {
        return null;
    }

    public OutboundModuleBuilder inputChannel(String inputChannel) {
        this.inputChannel = inputChannel;
        return this;
    }

    public OutboundModuleBuilder outputChannel(String outputChannel) {
        this.outputChannel = outputChannel;
        return this;
    }
}
