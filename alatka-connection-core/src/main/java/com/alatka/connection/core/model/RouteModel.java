package com.alatka.connection.core.model;


import com.alatka.connection.core.property.core.ProcessorProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;

/**
 * alatka.connection.route
 *
 * @author ybliu
 */
public class RouteModel {

    /**
     * alatka.connection.route.inbound
     */
    @Size(min = 1)
    @NotNull
    private Map<InboundModel, Object> inbound;

    /**
     * alatka.connection.route.processors
     */
    private List<ProcessorProperty> processors;

    /**
     * alatka.connection.route.outbound
     */
    @Size(min = 1)
    @NotNull
    private Map<OutboundModel, Object> outbound;

    /**
     * alatka.connection.route.bypass
     */
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
