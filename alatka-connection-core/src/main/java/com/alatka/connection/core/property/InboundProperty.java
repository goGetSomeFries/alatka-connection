package com.alatka.connection.core.property;

import com.alatka.connection.core.annotation.IdentityProperty;

/**
 * @author ybliu
 */
public abstract class InboundProperty extends Property {

    @IdentityProperty
    private String inputChannel;

    @IdentityProperty
    private String outputChannel;

    public String getInputChannel() {
        return inputChannel;
    }

    public void setInputChannel(String inputChannel) {
        this.inputChannel = inputChannel;
    }

    public String getOutputChannel() {
        return outputChannel;
    }

    public void setOutputChannel(String outputChannel) {
        this.outputChannel = outputChannel;
    }
}
