package com.alatka.connection.core.model;

import com.alatka.connection.core.property.ProcessorProperty;

import java.util.List;
import java.util.Map;

public class RouteModel {

    private Map<String, Object> inbound;

    private List<ProcessorProperty> processors;

    private Map<String, Object> outbound;

    public Map<String, Object> getInbound() {
        return inbound;
    }

    public void setInbound(Map<String, Object> inbound) {
        this.inbound = inbound;
    }

    public List<ProcessorProperty> getProcessors() {
        return processors;
    }

    public void setProcessors(List<ProcessorProperty> processors) {
        this.processors = processors;
    }

    public Map<String, Object> getOutbound() {
        return outbound;
    }

    public void setOutbound(Map<String, Object> outbound) {
        this.outbound = outbound;
    }
}
