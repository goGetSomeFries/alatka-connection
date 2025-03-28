package com.alatka.connection.core.property.core;

import com.alatka.connection.core.annotation.IdentityProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * alatka.connection.definition.pollerMetadata
 *
 * @author ybliu
 * @see org.springframework.integration.scheduling.PollerMetadata
 */
public class PollerMetadataProperty extends SupportProperty {

    /**
     * {@link org.springframework.core.task.TaskExecutor} bean id
     */
    @NotNull
    @IdentityProperty
    private String taskExecutor;
    @NotNull
    private Long maxMessagesPerPoll;
    @Valid
    @NotNull
    private TriggerProperty trigger;

    public String getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(String taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public Long getMaxMessagesPerPoll() {
        return maxMessagesPerPoll;
    }

    public void setMaxMessagesPerPoll(Long maxMessagesPerPoll) {
        this.maxMessagesPerPoll = maxMessagesPerPoll;
    }

    public TriggerProperty getTrigger() {
        return trigger;
    }

    public void setTrigger(TriggerProperty trigger) {
        this.trigger = trigger;
    }
}