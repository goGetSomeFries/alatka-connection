package com.alatka.connection.core.property.core;

public class FilterHandlerProperty extends MessageProcessorProperty {

    private String discardChannel;

    public String getDiscardChannel() {
        return discardChannel;
    }

    public void setDiscardChannel(String discardChannel) {
        this.discardChannel = discardChannel;
    }
}
