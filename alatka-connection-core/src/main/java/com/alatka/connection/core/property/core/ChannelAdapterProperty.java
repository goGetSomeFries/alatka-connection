package com.alatka.connection.core.property.core;

import com.alatka.connection.core.annotation.IdentityProperty;

/**
 * @author ybliu
 */
public abstract class ChannelAdapterProperty extends Property {

    @IdentityProperty
    private String outputChannel;

    private Integer order;

    public String getOutputChannel() {
        return outputChannel;
    }

    public void setOutputChannel(String outputChannel) {
        this.outputChannel = outputChannel;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
