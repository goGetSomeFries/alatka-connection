package com.alatka.connection.core.module;

import com.alatka.connection.core.component.ComponentRegister;
import com.alatka.connection.core.property.ProcessorProperty;
import com.alatka.connection.core.property.Property;

import java.util.List;
import java.util.Map;

/**
 * @author ybliu
 */
public class BypassModuleBuilder extends AbstractModuleBuilder<List<ProcessorProperty>, List<ProcessorProperty>> {

    public BypassModuleBuilder(String identity) {
        super(identity);
    }

    @Override
    protected List<String> doBuild(List<ProcessorProperty> models, Map<Object, ComponentRegister<? extends Property, Object>> mapping) {
        return null;
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
