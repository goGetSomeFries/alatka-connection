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

    @Override
    protected List<ProcessorProperty> convert(List<ProcessorProperty> models) {
        if (models == null) {

        }
        return null;
    }

    private List<String> registerChannels(List<ProcessorProperty> models, ProcessorProperty.Type type) {
/*
        models.stream()
                .filter(processor -> processor.getType() == ProcessorProperty.Type.all || processor.getType() == type)
                .map(ProcessorProperty::getChannel)
*/
        return null;
    }
}
