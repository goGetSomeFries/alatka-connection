package com.alatka.connection.core.model;

import com.alatka.connection.core.property.core.ProcessorProperty;

import java.util.List;

/**
 * alatka.connection.flow.processors
 *
 * @author ybliu
 */
public class ProcessorsModel {

    private List<ProcessorProperty> processors;

    public List<ProcessorProperty> getProcessors() {
        return processors;
    }

    public void setProcessors(List<ProcessorProperty> processors) {
        this.processors = processors;
    }
}
