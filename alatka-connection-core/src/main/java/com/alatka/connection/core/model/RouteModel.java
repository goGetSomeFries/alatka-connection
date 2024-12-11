package com.alatka.connection.core.model;


import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.validation.constraints.NotEmpty;
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
    @NotEmpty
    private Map<InboundModel, Object> inbound;

    /**
     * alatka.connection.route.processors
     */
    @JsonUnwrapped
    private ProcessorsModel processors;

    /**
     * alatka.connection.route.outbound
     */
    @NotEmpty
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

    public ProcessorsModel getProcessors() {
        return processors;
    }

    public void setProcessors(ProcessorsModel processors) {
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
