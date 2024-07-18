package com.alatka.connection.core.module;

import com.alatka.connection.core.property.ProcessorProperty;

import java.util.List;

/**
 * @author ybliu
 */
public class ProcessorModuleBuilder extends AbstractModuleBuilder<List<ProcessorProperty>, List<ProcessorProperty>> {

    public ProcessorModuleBuilder(String identity) {
        super(identity);
    }

    @Override
    protected void doBuild(List<ProcessorProperty> models) {
    }

}
