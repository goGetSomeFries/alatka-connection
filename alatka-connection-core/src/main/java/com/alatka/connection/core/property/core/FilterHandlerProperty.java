package com.alatka.connection.core.property.core;

import com.alatka.connection.core.annotation.IdentityProperty;

/**
 * alatka.connection.flow.processors[n].handler.filter
 *
 * @author whocares
 */
public class FilterHandlerProperty extends MessageProcessorProperty {

    @IdentityProperty
    private String discardChannel;

    public String getDiscardChannel() {
        return discardChannel;
    }

    public void setDiscardChannel(String discardChannel) {
        this.discardChannel = discardChannel;
    }
}
