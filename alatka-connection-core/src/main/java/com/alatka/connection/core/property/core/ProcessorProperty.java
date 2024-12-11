package com.alatka.connection.core.property.core;

import com.alatka.connection.core.annotation.IdentityProperty;
import com.alatka.connection.core.model.HandlerModel;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Collections;
import java.util.Map;

/**
 * @author ybliu
 */
public class ProcessorProperty extends Property {

    private Type type;

    @NotBlank
    private String desc;

    @Valid
    private ChannelProperty channel;

    @NotEmpty
    private Map<HandlerModel, Object> handler;

    @IdentityProperty
    private String pollerMetadata;

    @IdentityProperty
    private String taskScheduler;

    public enum Type {
        all, request, reply
    }

    @Override
    public ProcessorProperty defaultProperty() {
        this.type = Type.all;
        this.channel = new ChannelProperty().defaultProperty();
        this.handler = Collections.singletonMap(HandlerModel.passthrough, new PassthroughHandlerProperty());
        return this;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ChannelProperty getChannel() {
        return channel;
    }

    public void setChannel(ChannelProperty channel) {
        this.channel = channel;
    }

    public Map<HandlerModel, Object> getHandler() {
        return handler;
    }

    public void setHandler(Map<HandlerModel, Object> handler) {
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
