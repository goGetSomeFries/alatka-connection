package com.alatka.connection.core.module;

import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.model.OutboundModel;
import com.alatka.connection.core.property.ChannelAdapterProperty;

import java.util.List;
import java.util.Map;

/**
 * @author ybliu
 */
public class OutboundModuleBuilder extends AbstractModuleBuilder<Map<OutboundModel, Object>, List<? extends ChannelAdapterProperty>> {

    public OutboundModuleBuilder(String identity) {
        super(identity);
    }

    @Override
    protected void doBuild(List<? extends ChannelAdapterProperty> models, Map<Object, ComponentRegister> mapping) {
    }

    @Override
    protected List<? extends ChannelAdapterProperty> convert(Map<OutboundModel, Object> map) {
        return null;
    }

    @Override
    protected Class<? extends ComponentRegister> componentRegisterClass() {
        return null;
    }

}
