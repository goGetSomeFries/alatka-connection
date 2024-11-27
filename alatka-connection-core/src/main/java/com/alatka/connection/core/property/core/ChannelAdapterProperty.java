package com.alatka.connection.core.property.core;

import com.alatka.connection.core.annotation.IdentityProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author ybliu
 */
public abstract class ChannelAdapterProperty extends Property {

    @JsonIgnore
    @IdentityProperty
    private String outputChannel;

    @JsonIgnore
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
