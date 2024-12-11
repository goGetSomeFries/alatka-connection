package com.alatka.connection.core.property.core;

import com.alatka.connection.core.annotation.IdentityProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * alatka.connection.route.inbound
 *
 * @author ybliu
 */
public abstract class InboundProperty extends Property {

    @JsonIgnore
    @IdentityProperty
    private String inputChannel;

    @JsonIgnore
    @IdentityProperty
    private String outputChannel;

    @JsonIgnore
    @IdentityProperty
    private String errorChannel;

    private boolean errorHandled;

    private Long sendTimeout;

    private Long receiveTimeout;

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

    public String getErrorChannel() {
        return errorChannel;
    }

    public void setErrorChannel(String errorChannel) {
        this.errorChannel = errorChannel;
    }

    public boolean isErrorHandled() {
        return errorHandled;
    }

    public void setErrorHandled(boolean errorHandled) {
        this.errorHandled = errorHandled;
    }

    public Long getSendTimeout() {
        return sendTimeout;
    }

    public void setSendTimeout(Long sendTimeout) {
        this.sendTimeout = sendTimeout;
    }

    public Long getReceiveTimeout() {
        return receiveTimeout;
    }

    public void setReceiveTimeout(Long receiveTimeout) {
        this.receiveTimeout = receiveTimeout;
    }
}
