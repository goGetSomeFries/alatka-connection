package com.alatka.connection.core.model;


import com.alatka.connection.core.property.ProcessorProperty;

import java.util.List;
import java.util.Map;

public class RouteModel {

    private Map<InboundModel.Model, Object> inbound;

    private List<ProcessorProperty> processors;

    private Map<OutboundModel.Model, Object> outbound;

    public Map<InboundModel.Model, Object> getInbound() {
        return inbound;
    }

    public void setInbound(Map<InboundModel.Model, Object> inbound) {
        this.inbound = inbound;
    }

    public List<ProcessorProperty> getProcessors() {
        return processors;
    }

    public void setProcessors(List<ProcessorProperty> processors) {
        this.processors = processors;
    }

    public Map<OutboundModel.Model, Object> getOutbound() {
        return outbound;
    }

    public void setOutbound(Map<OutboundModel.Model, Object> outbound) {
        this.outbound = outbound;
    }
}
