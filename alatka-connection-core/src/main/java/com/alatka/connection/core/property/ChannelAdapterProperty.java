package com.alatka.connection.core.property;

/**
 * @author ybliu
 */
public class ChannelAdapterProperty extends Property {

    private String inputChannel;

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
