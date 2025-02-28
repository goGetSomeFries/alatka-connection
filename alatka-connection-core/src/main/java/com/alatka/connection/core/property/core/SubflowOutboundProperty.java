package com.alatka.connection.core.property.core;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * alatka.connection.route.outbound.subflow
 *
 * @author ybliu
 */
public class SubflowOutboundProperty extends OutboundProperty {

    @JsonUnwrapped
    private SubflowHandlerProperty subflow;

    public SubflowHandlerProperty getSubflow() {
        return subflow;
    }

    public void setSubflow(SubflowHandlerProperty subflow) {
        this.subflow = subflow;
    }
}
