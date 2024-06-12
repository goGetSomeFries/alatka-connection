package com.alatka.connection.config.property;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author ybliu
 */
public class PollerMetadataProperty extends Property {

    @NotNull
    private String taskExecutor;
    @NotNull
    private Long maxMessagesPerPoll;
    @NotBlank
    private Trigger trigger;
    @NotNull
    private Long period;
    private Boolean fixedRate = false;
    private Long initialDelay = 0L;

    public enum Trigger {
        periodic
    }

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

    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    public Long getPeriod() {
        return period;
    }

    public void setPeriod(Long period) {
        this.period = period;
    }

    public Boolean getFixedRate() {
        return fixedRate;
    }

    public void setFixedRate(Boolean fixedRate) {
        this.fixedRate = fixedRate;
    }

    public Long getInitialDelay() {
        return initialDelay;
    }

    public void setInitialDelay(Long initialDelay) {
        this.initialDelay = initialDelay;
    }
}