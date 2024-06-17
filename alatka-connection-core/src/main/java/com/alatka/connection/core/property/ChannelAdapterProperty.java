package com.alatka.connection.core.property;

/**
 * @author ybliu
 */
public class ChannelAdapterProperty extends Property {

    private String inputChannel;

    private String outputChannel;

    private String source;

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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
