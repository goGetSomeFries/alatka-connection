package com.alatka.connection.core.property.core;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author ybliu
 */
public class ProcessorProperty extends Property {

    private Type type = Type.all;
    @Valid
    private ChannelProperty channel;
    @NotNull
    @Valid
    private HandlerProperty handler;
    private String pollerMetadata;
    private String taskScheduler;

    public enum Type {
        all, request, reply
    }

    @Override
    public ProcessorProperty defaultProperty() {
        this.type = Type.all;
        this.channel = new ChannelProperty().defaultProperty();
        this.handler = new HandlerProperty().defaultProperty();
        return this;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public ChannelProperty getChannel() {
        return channel;
    }

    public void setChannel(ChannelProperty channel) {
        this.channel = channel;
    }

    public HandlerProperty getHandler() {
        return handler;
    }

    public void setHandler(HandlerProperty handler) {
        this.handler = handler;
    }

    public String getPollerMetadata() {
        return pollerMetadata;
    }

    public void setPollerMetadata(String pollerMetadata) {
        this.pollerMetadata = pollerMetadata;
    }

    public String getTaskScheduler() {
        return taskScheduler;
    }

    public void setTaskScheduler(String taskScheduler) {
        this.taskScheduler = taskScheduler;
    }
}
