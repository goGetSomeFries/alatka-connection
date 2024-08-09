package com.alatka.connection.core.property.core;

import com.alatka.connection.core.annotation.IdentityProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author ybliu
 */
public class ConsumerProperty extends Property {

    @NotBlank
    @IdentityProperty
    private String inputChannel;

    @NotBlank
    @IdentityProperty
    private String messageHandler;

    @IdentityProperty
    private String pollerMetadata;

    @IdentityProperty
    private String taskScheduler;

    public String getInputChannel() {
        return inputChannel;
    }

    public void setInputChannel(String inputChannel) {
        this.inputChannel = inputChannel;
    }

    public String getMessageHandler() {
        return messageHandler;
    }

    public void setMessageHandler(String messageHandler) {
        this.messageHandler = messageHandler;
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
