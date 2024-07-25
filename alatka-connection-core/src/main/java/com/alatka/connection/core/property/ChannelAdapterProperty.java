package com.alatka.connection.core.property;

/**
 * @author ybliu
 */
public abstract class ChannelAdapterProperty extends Property {

    private String outputChannel;

    public String getOutputChannel() {
        return outputChannel;
    }

    public void setOutputChannel(String outputChannel) {
        this.outputChannel = outputChannel;
    }
}
