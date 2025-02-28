package com.alatka.connection.core.property.core;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * alatka.connection.route.outbound.custom
 *
 * @author ybliu
 */
public class CustomOutboundProperty extends OutboundProperty {

    @JsonUnwrapped
    private CustomHandlerProperty custom;

    public CustomHandlerProperty getCustom() {
        return custom;
    }

    public void setCustom(CustomHandlerProperty custom) {
        this.custom = custom;
    }
}
