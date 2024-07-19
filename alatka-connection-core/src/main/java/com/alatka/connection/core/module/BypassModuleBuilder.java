package com.alatka.connection.core.module;

import com.alatka.connection.core.property.ProcessorProperty;

import java.util.List;

/**
 * @author ybliu
 */
public class BypassModuleBuilder extends AbstractModuleBuilder<List<ProcessorProperty>, List<ProcessorProperty>> {

    public BypassModuleBuilder(String identity) {
        super(identity);
    }

    @Override
    protected void doBuild(List<ProcessorProperty> models) {
    }

    @Override
    protected List<ProcessorProperty> convert(List<ProcessorProperty> models) {
        if (models == null) {

        }
        return null;
    }
}
