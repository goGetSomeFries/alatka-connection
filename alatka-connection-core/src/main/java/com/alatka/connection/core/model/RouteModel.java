package com.alatka.connection.core.model;


import com.alatka.connection.core.property.ProcessorProperty;

import java.util.List;
import java.util.Map;

public class RouteModel {

    private Map<InboundModel, Object> inbound;

    private List<ProcessorProperty> processors;

    private Map<OutboundModel, Object> outbound;

    public Map<InboundModel, Object> getInbound() {
        return inbound;
    }

    public void setInbound(Map<InboundModel, Object> inbound) {
        this.inbound = inbound;
    }

    public List<ProcessorProperty> getProcessors() {
        return processors;
    }

    public void setProcessors(List<ProcessorProperty> processors) {
        this.processors = processors;
    }

    public Map<OutboundModel, Object> getOutbound() {
        return outbound;
    }

    public void setOutbound(Map<OutboundModel, Object> outbound) {
        this.outbound = outbound;
    }
}
