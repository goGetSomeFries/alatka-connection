package com.alatka.connection.core.property.core;

import com.alatka.connection.core.annotation.IdentityProperty;

/**
 * TODO
 *
 * @author ybliu
 */
public class SourcePollingInboundProperty extends InboundProperty {

    @IdentityProperty
    private String poller;
    @IdentityProperty
    private String taskScheduler;

    public String getPoller() {
        return poller;
    }

    public void setPoller(String poller) {
        this.poller = poller;
    }

    public String getTaskScheduler() {
        return taskScheduler;
    }

    public void setTaskScheduler(String taskScheduler) {
        this.taskScheduler = taskScheduler;
    }
}
