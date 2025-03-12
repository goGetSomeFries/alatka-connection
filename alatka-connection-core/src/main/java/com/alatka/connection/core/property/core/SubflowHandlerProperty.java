package com.alatka.connection.core.property.core;

import javax.validation.constraints.NotEmpty;

/**
 * alatka.connection.flow.processors[n].handler.subflow
 *
 * @author whocares
 */
public class SubflowHandlerProperty extends ChannelAdapterProperty {

    @NotEmpty
    private String identity;

    private String requestChannel;

    private String replyChannel;

    private String errorChannel;

    private Long requestTimeout;

    private Long replyTimeout;

    private String resultHandler;

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getRequestChannel() {
        return requestChannel;
    }

    public void setRequestChannel(String requestChannel) {
        this.requestChannel = requestChannel;
    }

    public String getReplyChannel() {
        return replyChannel;
    }

    public void setReplyChannel(String replyChannel) {
        this.replyChannel = replyChannel;
    }

    public String getErrorChannel() {
        return errorChannel;
    }

    public void setErrorChannel(String errorChannel) {
        this.errorChannel = errorChannel;
    }

    public Long getRequestTimeout() {
        return requestTimeout;
    }

    public void setRequestTimeout(Long requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public Long getReplyTimeout() {
        return replyTimeout;
    }

    public void setReplyTimeout(Long replyTimeout) {
        this.replyTimeout = replyTimeout;
    }

    public String getResultHandler() {
        return resultHandler;
    }

    public void setResultHandler(String resultHandler) {
        this.resultHandler = resultHandler;
    }
}
