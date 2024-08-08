package com.alatka.connection.core.model;


import com.alatka.connection.core.property.ProcessorProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;

/**
 * @author ybliu
 */
public class RouteModel {

    @Size(min = 1)
    @NotNull
    private Map<InboundModel, Object> inbound;

    private List<ProcessorProperty> processors;

    @Size(min = 1)
    @NotNull
    private Map<OutboundModel, Object> outbound;

    private Map<OutboundModel, Object> bypass;

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

    public Map<OutboundModel, Object> getBypass() {
        return bypass;
    }

    public void setBypass(Map<OutboundModel, Object> bypass) {
        this.bypass = bypass;
    }
}
