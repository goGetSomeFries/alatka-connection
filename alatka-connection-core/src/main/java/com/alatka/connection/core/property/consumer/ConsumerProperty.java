package com.alatka.connection.core.property.consumer;

import com.alatka.connection.core.annotation.BeanProperty;
import com.alatka.connection.core.property.Property;

import javax.validation.constraints.NotBlank;

/**
 * @author ybliu
 */
public class ConsumerProperty extends Property {

    @NotBlank
    @BeanProperty
    private String inputChannel;

    @NotBlank
    @BeanProperty
    private String messageHandler;

    @BeanProperty
    private String pollerMetadata;

    @BeanProperty
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
